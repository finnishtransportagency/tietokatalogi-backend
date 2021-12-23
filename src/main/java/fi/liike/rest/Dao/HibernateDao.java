package fi.liike.rest.Dao;

import fi.liike.rest.Dao.Hibernate.KatalogiComparator;
import fi.liike.rest.Model.*;
import fi.liike.rest.api.DaoContent;
import fi.liike.rest.api.HistoryType;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.dto.ExternalSovellusCSVDto;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.*;

import static java.lang.String.format;

public class HibernateDao extends HibernateSession {
	private final Logger LOG = LoggerFactory.getLogger(HibernateDao.class);

	public HibernateDao() {
	}

	public Criteria luoHaku(Class<? extends Haettava> clazz) {
		getSession().beginTransaction();
		Criteria crit = getSession().createCriteria(clazz);
		return crit;
	}


	public List<Haettava> getAll(Class<? extends Haettava> className) {
		Criteria crit = luoHaku(className);
		List<Haettava> entries = list(crit);
		closeSession();
		return entries;
	}

	public Haettava get(Class<? extends Haettava> className, int id) {
		return get(className, id, Collections.<Criterion>emptyList());
	}

	public Haettava get(Class<? extends Haettava> className, int id, List<Criterion> criterionList) {
		Criteria crit = luoHaku(className);
		crit.add(Restrictions.eq("tunnus", id));
		for (Criterion criterion : criterionList) {
			crit.add(criterion);
		}
		List<Haettava> entries = list(crit);
		closeSession();
		return getLatest(entries);
	}

	public Haettava getByExternalSovellusCSVDto(Class<? extends Haettava> className, ExternalSovellusCSVDto externalCSVsovellus) {
		Criteria crit = luoHaku(className);
		crit.add(Restrictions.eq("nimi", externalCSVsovellus.getAdGroup()));
		crit.add(Restrictions.eq("tuotekoodi", externalCSVsovellus.getSignature0()));
		crit.add(Restrictions.eq("versio", externalCSVsovellus.getVersion()));

		Haettava haettava = (Haettava) crit.uniqueResult();
		closeSession();
		return haettava;
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> list(Criteria criteria) {
		return criteria.list();
	}

	/*
	 * In theory there should never be more than one active version of entry. If
	 * for some reason there is more then use the latest version.
	 */
	protected Haettava getLatest(List<Haettava> entries) {
		if (entries.isEmpty())
			return null;
		Collections.sort(entries, new KatalogiComparator());
		return entries.get(0);
	}

	protected void setCreationTimestamp(Haettava content) {
		content.setRiviluotupvm(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	}

	protected void setModificationInformation(Haettava content, HaettavaHistory history) {
		java.sql.Timestamp timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		content.setRivimuokattupvm(timestamp);
		history.setRiviluotupvm(timestamp);
		history.setRivimuokkaajatunnus(content.getRivimuokkaajatunnus());
	}

	protected <T extends Haettava> Haettava save(Session passedSession, DaoContent saveContent, String sequence, HaettavaHistory history)
			throws SQLException {
		LOG.info("Saving content {} with name: {}", saveContent.getContent().getClass(), saveContent.getContent().getNimi());

		Haettava content = saveContent.getContent();
		if (content.getTunnus() != null) {
			throw new SQLException("Uusi rivi voidaan luoda vain jos id:ta ei ole maaritelty.");
		}
		Session session = passedSession;
		if (session == null) {
			session = getSession();
		}

		// Update sequence.
		// If db imported data happens to have same value with sequence, search
		// next free number.
		Integer id = getFreeId(content, session, sequence);
		content.setTunnus(id);
		setCreationTimestamp(content);
		if (!createHistoryEntry(content, history, HistoryType.ADD)) {
			return null;
		}

		if (passedSession == null) {
			Transaction transaction = null;
			try {
				transaction = session.beginTransaction();
				session.save(content);
				session.save(history);
				for (JoinPublicDao joinDao : saveContent.getJoinDaos()) {
					joinDao.save(session, content.getTunnus());
				}
				transaction.commit();
			} catch (RuntimeException e) {
				this.logDbException(e);
				try {
					content = null;
					transaction.rollback();
				} catch (RuntimeException ex) {
					LOG.error("Cannot roll back transaction! Error Message: " + ex.getMessage());
				}
			} finally {
				if (session != null)
					session.close();
			}
		} else {
			session.save(content);
			session.save(history);
			for (JoinPublicDao joinDao : saveContent.getJoinDaos()) {
				joinDao.save(session, content.getTunnus());
			}
		}

		return content;
	}

	private Integer getFreeId(Haettava content, Session session, String sequence) {
		int id = 0;
		boolean freeIdFound = false;

		while (!freeIdFound) {
			SQLQuery increaseSeqQuery = session.createSQLQuery("select nextval('" + sequence + "')");
			Object newValue = increaseSeqQuery.uniqueResult();
			// h2 database returns seq vals in different data type than pg
			if (newValue.getClass().equals(BigDecimal.class)) {
				id = ((BigDecimal) newValue).intValue();
			} else {
				id = ((BigInteger) newValue).intValue();
			}

			Criteria criteria = session.createCriteria(content.getClass());
			criteria.add(Restrictions.eq("tunnus", id));
			List<Haettava> list = list(criteria);
			if (list.isEmpty())
				freeIdFound = true;

		}
		return id;
	}

	protected void testSimpleSQLquery() {
		//Just testing connection
		getSession().createSQLQuery("SELECT 1").uniqueResult();
		closeSession();
	}

	protected boolean createHistoryEntry(Haettava content, HaettavaHistory history, HistoryType historyType) {
		PropertyUtilsBean beanUtil = new PropertyUtilsBean();
		try {
			beanUtil.copyProperties(history, content);
			if (content.getRivimuokattupvm() != null)
				history.setRiviluotupvm(content.getRivimuokattupvm());
			history.setHistoriatyyppi(historyType);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			LOG.error("Could not copy properties to BeanUtils! Error Message: " + e.getMessage());
			return false;
		}
		return true;
	}

	public boolean setUpHistoryEntry(JoinHenkiloRooliTable content, JoinHenkiloRooliTableHistory history,
									  HistoryType historyType) {
		PropertyUtilsBean beanUtil = new PropertyUtilsBean();
		try {
			beanUtil.copyProperties(history, content);
			history.setHistoriatyyppi(historyType);
			history.setRiviluotupvm(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
			history.setRivimuokkaajatunnus(content.getRivimuokkaajatunnus());
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			LOG.error("Could not copy properties to BeanUtils! Error Message: " + e.getMessage());
			return false;
		}
		return true;
	}

	protected boolean copyEditedContentToExistingEntry(Haettava content, Haettava existing) {
		content.setRiviluotupvm(existing.getRiviluotupvm());
		content.setRivimuokattupvm(existing.getRivimuokattupvm());
		PropertyUtilsBean beanUtil = new PropertyUtilsBean();
		try {
			beanUtil.copyProperties(existing, content);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			LOG.error("Could not copy properties to BeanUtils! Error Message: " + e.getMessage());
			return false;
		}
		return true;
	}

	public Haettava update(Session passedSession, DaoContent saveContent, HaettavaHistory history) {
		Haettava content = saveContent.getContent();
		Session session = passedSession;
		if (session == null) {
			session = getSession();
		}
		Criteria crit = session.createCriteria(saveContent.getContent().getClass());
		crit.add(Restrictions.eq("tunnus", saveContent.getContent().getTunnus()));

		List<Haettava> list = list(crit);
		Haettava existing = getLatest(list);
		if (existing == null) {
			return null;
		}

		copyEditedContentToExistingEntry(content, existing);
		setModificationInformation(existing, history);
		if (!createHistoryEntry(existing, history, HistoryType.MOD)) {
			return null;
		}

		if (passedSession == null) {
			Transaction transaction = null;
			try {
				transaction = session.beginTransaction();
				session.saveOrUpdate(existing);
				session.save(history);
				for (JoinPublicDao joinDao : saveContent.getJoinDaos()) {
					joinDao.update(session, content.getTunnus());
				}
				transaction.commit();
			}
			catch (RuntimeException e) {
				this.logDbException(e);
				try {
					existing = null;
					transaction.rollback();
				} catch (RuntimeException ex) {
					LOG.error("Cannot roll back transaction! Error Message: " + ex.getMessage());
				}
			}
			finally {
				if (session != null) {
					session.close();
				}
			}
		} else {
			session.saveOrUpdate(existing);
			session.save(history);
			for (JoinPublicDao joinDao : saveContent.getJoinDaos()) {
				joinDao.update(session, content.getTunnus());
			}
		}

		return existing;
	}

	private void logDbException(RuntimeException e) {
		if (e instanceof JDBCException) {
			JDBCException dbe = (JDBCException) e;
			LOG.error("Unable to do a transaction. Error message: " + dbe.getMessage());
			LOG.error("SQL exception:" + dbe.getSQLException().toString());
			LOG.error("Cause: " + dbe.getSQL());
		} else {
			LOG.error("Unable to do a transaction. Error message: " + e.getMessage());
		}
	}

	public void delete(Class<? extends Haettava> className, Class<? extends HaettavaHistory> historyClassName, int id,
			HaettavaHistory history, DaoContent deleteContent, String remoteUser) throws SQLException {
		Session session = getSession();
		Criteria criteria = session.createCriteria(className);
		criteria.add(Restrictions.eq("tunnus", id));

		List<Haettava> list = list(criteria);
		Haettava entry = getLatest(list);
		if (entry == null)
			throw new SQLException("Poistettavaa ei loydy");
		setModificationInformation(entry, history);
		createHistoryEntry(entry, history, HistoryType.DEL);

		Transaction transaction = null;
		try {
			// TKYP-158 - Delete the child rows before the parent row
			// Otherwise a tietojärjestelmäpalvelu cannot be deleted if it has a tietolaji/ryhmä
			transaction = session.beginTransaction();
			if (deleteContent != null)
				for (JoinPublicDao joinDao : deleteContent.getJoinDaos()) {
					joinDao.delete(session, id);
				}
			session.delete(entry);
			session.save(history);
			transaction.commit();
		} catch (RuntimeException e) {
			this.logDbException(e);
			try {
				transaction.rollback();
			} catch (RuntimeException ex) {
				LOG.error("Cannot roll back transaction! Error Message: " + ex.getMessage());
			}
		} finally {
			if (session != null)
				session.close();
		}
	}

	public List<KasiteArvoContent> getResources(Class<? extends KasiteArvo> className) {
		Criteria crit = getSession().createCriteria(className);
		List<KasiteArvo> resources = list(crit);
		closeSession();
		return createKasiteArvoContent(resources);
	}

	private List<KasiteArvoContent> createKasiteArvoContent(List<KasiteArvo> resources) {
		ArrayList<KasiteArvoContent> content = new ArrayList<KasiteArvoContent>();
		for (KasiteArvo kasiteArvo : resources) {
			content.add(new KasiteArvoContent(kasiteArvo.getId(), kasiteArvo.getKasite(), kasiteArvo.getArvo()));
		}
		return content;
	}

	public void saveTempTableAndMergeTables(
			List<Object> objList, String tempTable, String targetTable, String targetTableIdSequence, String idColumn,
			List<String> matchingColumns, String updateColumn, Integer updateValue,
			List<String> updateColumns, List<String> insertColumns) {
		Session session = getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			//Clear temp table
			String clearTempTableSql = format("DELETE FROM %s", tempTable);
			executeBasicSqlUpdate(clearTempTableSql, session);
			session.flush();
			for (Object object : objList) {
				//Populate Sovellus Temp table
				session.save(object);
			}
			session.flush();

			mergeTables(targetTable, targetTableIdSequence, idColumn, tempTable,
					matchingColumns, updateColumns, insertColumns, updateColumn, updateValue, session);
			transaction.commit();
		} catch (RuntimeException e) {
			LOG.error(format("Could not merge tables %s %s", targetTable, tempTable));
			this.logDbException(e);
			try {
				transaction.rollback();
			} catch (HibernateException ex) {
				LOG.error("Cannot roll back transaction! Error Message: " + ex.getMessage());
			}
			throw new RuntimeException();
		} finally {
			if (session != null)
				session.close();
		}
	}

	private void mergeTables(String targetTable, String targetTableIdSequence, String idColumn, String sourceTable,
							List<String> matchingColumns, List<String> updateColumns, List<String> insertColumns,
							String mergeUnmatchUpdateColumn, Object mergeUnmatchUpdateValue, Session session) {
		String mergeSql = getMergeTablesSql(targetTable, targetTableIdSequence, idColumn, sourceTable, matchingColumns,
				updateColumns, insertColumns);
		executeBasicSqlUpdate(mergeSql, session);
		session.flush();
		updateTargetTableAfterMerge(targetTable, sourceTable, mergeUnmatchUpdateColumn, mergeUnmatchUpdateValue,
				matchingColumns, session);
		session.flush();
	}

	private int updateTargetTableAfterMerge(String targetTable, String sourceTable, String mergeUnmatchUpdateColumn,
											Object mergeUnmatchUpdateValue, List<String> matchingColumns, Session session) {
		StringBuilder matchingSql = new StringBuilder();
		String matchingColumnSeparator = ", ";
		for (String matchingCol : matchingColumns) {
			matchingSql.append(format("%s%s", matchingCol, matchingColumnSeparator));
		}
		matchingSql.replace(matchingSql.length() - matchingColumnSeparator.length(), matchingSql.length(), "");
		String matchingStr = matchingSql.toString();

		String sql = format("UPDATE %s SET %s = %s WHERE (%s) NOT IN (SELECT %s FROM %s) ",
				targetTable, mergeUnmatchUpdateColumn, mergeUnmatchUpdateValue,
				matchingStr, matchingStr, sourceTable);
		executeBasicSqlUpdate(sql, session);

		//HOX! Be careful with this hard coded value.... TODO make this better
		Integer oppositeValue = ((Integer) mergeUnmatchUpdateValue) == 1 ? 0 : 1;
		sql = format("UPDATE %s SET %s = %s WHERE (%s) IN (SELECT %s FROM %s) ",
				targetTable, mergeUnmatchUpdateColumn, oppositeValue, matchingStr, matchingStr, sourceTable);
		return executeBasicSqlUpdate(sql, session);
	}

	//Transaction has been started
	public int executeBasicSqlUpdate(String sql, Session session) {
		if (session == null) {
			throw new RuntimeException("session was null");
		}
		SQLQuery query = session.createSQLQuery(sql);
		int updated = query.executeUpdate();

		return updated;
	}

	private String matchingColumnsToStr(List<String> matchingColumns, String matchingColumnSeparator) {
		StringBuilder matchingSql = new StringBuilder();
		for (String matchingCol : matchingColumns) {
			matchingSql.append(format("S.%s = T.%s%s", matchingCol, matchingCol, matchingColumnSeparator));
		}
		matchingSql.replace(matchingSql.length() - matchingColumnSeparator.length(), matchingSql.length(), "");
		return matchingSql.toString();
	}

	public String getMergeTablesSql(String targetTable, String targetTableIdSequence, String idColumn,
									String sourceTable, List<String> matchingColumns,
									List<String> updateColumns, List<String> insertColumns) {

		StringBuilder targetColumnsString = new StringBuilder("(");
		for (String insertColumn : insertColumns) {
			targetColumnsString.append(format("\"%s\",", insertColumn).toLowerCase());
		}
		targetColumnsString.replace(targetColumnsString.length() - 1, targetColumnsString.length(), ")");
		StringBuilder sourceColumnsString = new StringBuilder();
		final String sourceTableAlias = "src.";
		for (String insertColumn : insertColumns) {
			if (insertColumn.equals(idColumn)) {
				sourceColumnsString.append(format("nextval('%s'),", targetTableIdSequence));
			} else {
				sourceColumnsString.append(format("%s\"%s\",",sourceTableAlias , insertColumn).toLowerCase());
			}

		}
		sourceColumnsString.replace(sourceColumnsString.length() - 1, sourceColumnsString.length(), "");
		String matchingSql = StringUtils.join(matchingColumns, ",");
		String lineEnding = ",\n";
		StringBuilder updateSql = new StringBuilder();
		for (String updateColumn : updateColumns) {
			updateSql.append(format("%s = excluded.%s%s", updateColumn, updateColumn, lineEnding));
		}
		updateSql.replace(updateSql.length() - lineEnding.length(), updateSql.length() - 1, "");

		return format(
				"INSERT INTO %s %s\n" +
						"SELECT %s " +
						"FROM %s src\n" +
						"ON CONFLICT (%s)\n" +
						"DO UPDATE\n" +
						"SET %s",
				targetTable, targetColumnsString, sourceColumnsString, sourceTable, matchingSql, updateSql
		);
	}

	// TODO remove or redirect h2 tests to use this
	// Works with h2 but not with postgres
	public String getMergeTablesSqlOLD(String targetTable, String targetTableIdSequence, String idColumn,
									String sourceTable, List<String> matchingColumns,
									List<String> updateColumns, List<String> insertColumns) {
		String lineEnding = ",\n";
		StringBuilder updateSql = new StringBuilder();
		for (String updateColumn : updateColumns) {
			updateSql.append(format("T.%s = S.%s%s", updateColumn, updateColumn, lineEnding));
		}
		updateSql.replace(updateSql.length() - lineEnding.length(), updateSql.length() - 1, "");

		StringBuilder insertSql = new StringBuilder("(");
		// h2 database used for testing uses a slightly different syntax where the target table
		// alias is not used in the insert statement.
		final String targetTableAlias = isTestingSession() ? "": "T.";
		for (String insertColumn : insertColumns) {
			insertSql.append(format("%s%s,",targetTableAlias , insertColumn));
		}
		insertSql.replace(insertSql.length() - 1, insertSql.length(), ")\n");
		insertSql.append("VALUES (");
		for (String insertColumn : insertColumns) {
			if (insertColumn.equals(idColumn)) {
				insertSql.append(format("%s.nextval, ", targetTableIdSequence));
			} else {
				insertSql.append(format("S.%s, ", insertColumn));
			}

		}
		insertSql.replace(insertSql.length() - 2, insertSql.length(), ")");
		String matchingSql = matchingColumnsToStr(matchingColumns, " AND ");

		return format(
				"MERGE INTO %s T\n" +
						"USING %s S\n" +
						"ON (%s)\n" +
						"WHEN MATCHED THEN UPDATE\n" +
						"SET %s" +
						"WHEN NOT MATCHED\n" +
						"THEN INSERT %s",
				targetTable, sourceTable, matchingSql, updateSql.toString(), insertSql.toString());
	}

	/**
	 * Returns a list of all class entities mapped to an id and a name.
	 * @param className The name of the class that is fetched.
	 * @param resourceName The name used for the class in the returned list.
	 * @return
	 */
	protected List<KasiteArvoContent> getClassResources(Class<? extends Haettava> className, String resourceName) {
		List<Haettava> entityList = this.getAll(className);
		List<KasiteArvoContent> kasiteArvoContentList = new ArrayList<>();

		for (Haettava entity : entityList) {
			Integer id = entity.getTunnus();
			String name = entity.getNimi();

			if (name != null && !name.equals("")) {
				KasiteArvoContent kasiteArvoContent = new KasiteArvoContent(id, resourceName, name);
				kasiteArvoContentList.add(kasiteArvoContent);
			}
		}
		return kasiteArvoContentList;
	}

}
