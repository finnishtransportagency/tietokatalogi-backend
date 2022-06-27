package fi.liike.testutils;

import java.math.BigInteger;
import java.util.*;

import com.google.common.collect.ImmutableSet;
import fi.liike.rest.util.HibernateUtil;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fi.liike.rest.Dao.Hibernate.HistoryComparator;
import fi.liike.rest.Dao.Hibernate.KatalogiComparator;
import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.HaettavaHistory;
import fi.liike.rest.Model.JoinHistory;
import fi.liike.rest.Model.JoinTable;
import fi.liike.rest.api.Catalogue;
import fi.liike.rest.api.HistoryType;
import fi.liike.rest.api.JoinCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestDbUtil {

	private static final Logger LOG = LoggerFactory.getLogger(TestDbUtil.class);
	private static Session session;
	private static SessionFactory sessionFactory;
	private static final Set<String> idOnlySequences = ImmutableSet.of("sovellus_tuonti", "tietovaranto",
			"tietovarantohist", "toimintaprosessi", "toimintaprosessihist", "toimintaprostietovarant",
			"toimintaprostietovahist", "tietoryhmatietovaranto", "tietoryhmatietovarahist",
			"termilomake", "termilomakehist","TERMILOMAKEHIERKASITE", "TERMILOMAKEHIERKHIST",
		   "TERMILOMAKEKOOSTKASITE","TERMILOMAKEKOOSTKASHIST","TERMILOMAKEASSOSKASITE","TERMILOMAKEASKAHISTORIA",
			"tietojarjestelmapalvelu", "tietojarjpalveluhist", "tietojarjpalvelutieto", "tietojarjpalvtietohist");


	public static void init() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	private static Session getSession() {

		if (session == null || !session.isOpen())
			session = sessionFactory.openSession();
		return session;
	}

	public void clearTestTable(String table) {
		String sql = "DELETE FROM " + table;
		Query query = getSession().createSQLQuery(sql);
		query.executeUpdate();
		Transaction tx = getSession().beginTransaction();
		tx.commit();
		closeSession();
	}

	public static void writeDb(JSONArray testData) {
		Transaction tx = getSession().beginTransaction();

		try {
			for (int i = 0; i < testData.length(); i++) {
				JSONObject json;
				json = testData.getJSONObject(i);
				String sql = TestUtil.jsonToSql(json);
				SQLQuery query = getSession().createSQLQuery(sql);
				query.executeUpdate();
			}
		} catch (JSONException e) {
			LOG.error("There was an error: " + e.getMessage());
		} catch (Exception e) {
			LOG.error("There was an error: " + e.getMessage());
		}
		tx.commit();
		closeSession();
	}

	private static void closeSession() {
		if (session != null)
			session.close();
	}

	public static void setupDB(Catalogue catalogue) {
		List<String> sequences = new ArrayList<String>();
		switch (catalogue) {
		case JARJESTELMA:
			sequences.add("jarjestelma");
			sequences.add("jarjestelmahist");
			sequences.add("joinjarjestelmalinkkaus");
			sequences.add("joinjlinkhist");
			break;
		case PALVELU:
			sequences.add("palvelu");
			sequences.add("palveluhist");
			break;
		case LOOGINEN:
			sequences.add("looginentv");
			sequences.add("looginentvhist");
			sequences.add("joinlooginenfyysinen");
			sequences.add("joinloogfyyshistoria");
			break;
		case FYYSINEN:
			sequences.add("fyysinentv");
			sequences.add("fyysinentvhist");
			break;
		case PAATIETORYHMA:
			sequences.add("paatietoryhma");
			sequences.add("paatietoryhmahist");
			break;
		case TIETORYHMA:
			sequences.add("tietoryhma");
			sequences.add("tietoryhmahist");
			sequences.add("jointietorpaatietor");
			sequences.add("jtietorpaatietorhist");
			sequences.add("tietoryhmatietovaranto");
			sequences.add("tietoryhmatietovarahist");
			break;
		case TIETOLAJI:
			sequences.add("tietolaji");
			sequences.add("tietolajihist");
			sequences.add("jointietolajilooginen");
			sequences.add("jointietolooghistoria");
			sequences.add("jointietolajitietoryhma");
			sequences.add("jtietotietoryhmahist");
			break;
		case MOLEKYYLILINKKI:
			setupDB(Catalogue.TIETORYHMA);
			setupDB(Catalogue.PAATIETORYHMA);
			setupDB(Catalogue.TIETOLAJI);
			setupDB(Catalogue.FYYSINEN);
			setupDB(Catalogue.LOOGINEN);
			break;
		case TIETOARKKITEHTUURI:
            setupDB(Catalogue.PAATIETORYHMA);
            setupDB(Catalogue.TIETORYHMA);
			setupDB(Catalogue.TIETOLAJI);
			setupDB(Catalogue.LOOGINEN);
			setupDB(Catalogue.FYYSINEN);
			break;
		case HENKILO:
			setupDB(Catalogue.JARJESTELMA);
			sequences.add("henkilo");
			sequences.add("rooli");
			sequences.add("joinjarjhenkrooli");
			break;
		case SOVELLUS:
			sequences.add("sovellus");
			sequences.add("sovellus_hist");
			sequences.add("joinsovellushenkrooli");
			sequences.add("sovhenkroolihist");
			sequences.add("sovellus_tuonti");
			break;
		case TIETOVARANTO:
			sequences.add("tietovaranto");
			sequences.add("tietovarantohist");
			break;
		case TOIMINTAPROSESSI:
			sequences.add("toimintaprosessi");
			sequences.add("toimintaprosessihist");
			sequences.add("toimintaprostietovarant");
			sequences.add("toimintaprostietovahist");
			break;
		case TERMILOMAKE:
			sequences.add("termilomake");
			sequences.add("termilomakehist");
			sequences.add("TERMILOMAKEHIERKASITE"); // hierarkkinen käsite
			sequences.add("TERMILOMAKEHIERKHIST"); // hierarkkinen käsite historia
			sequences.add("TERMILOMAKEKOOSTKASITE"); // koostumussuhteinen käsite
			sequences.add("TERMILOMAKEKOOSTKASHIST"); // koostumussuhteinen käsite historia
			sequences.add("TERMILOMAKEASSOSKASITE"); // assosiatiivinen käsite
			sequences.add("TERMILOMAKEASKAHISTORIA"); // assosiatiivinen käsite historia
			break;
		case TIETOJARJESTELMAPALVELU:
			setupDB(Catalogue.JARJESTELMA);
			setupDB(Catalogue.TIETOLAJI);
			sequences.add("tietojarjestelmapalvelu");
			sequences.add("tietojarjpalveluhist");
			sequences.add("tietojarjpalvelutieto");
			sequences.add("tietojarjpalvtietohist");
			break;
		default:
			return;
		}
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		for (String sequence : sequences) {
			session.createSQLQuery("drop sequence if exists " + sequence + "_id_seq").executeUpdate();
			if (!TestDbUtil.idOnlySequences.contains(sequence)) {
				session.createSQLQuery("drop sequence if exists " + sequence + "_seq").executeUpdate();
			}
		}
		tx.commit();
		Transaction tx2 = session.beginTransaction();
		for (String sequence : sequences) {
			session.createSQLQuery("create sequence " + sequence + "_id_seq start with 1").executeUpdate();
			if (!TestDbUtil.idOnlySequences.contains(sequence)) {
				session.createSQLQuery("create sequence " + sequence + "_seq start with 1").executeUpdate();
			}
		}
		tx2.commit();
	}

	public static void setupJoinTableDB(JoinCategory category) {
		String sequence;
		switch (category) {
		case LOOGINENFYYSINEN:
			sequence = "joinlooginenfyysinen";
			break;

		default:
			return;
		}
		Session session = getSession();
		session.createSQLQuery("drop sequence " + sequence + "_id_seq").executeUpdate();
		session.createSQLQuery("create sequence " + sequence + "_id_seq start with 1").executeUpdate();

	}

	public static void clearDB(Catalogue catalogue) {
		List<String> dbNames = new ArrayList<String>();
		switch (catalogue) {
		case JARJESTELMA:
			dbNames.add("tietojarjestelmasalkku");
			dbNames.add("tietojarjestelmasalkkuhistoria");
			dbNames.add("tietojarjestelma_kasite_arvo");
			dbNames.add("jarjestelmalinkkaus");
			dbNames.add("jarjestelmaLinkkaushistoria");
			break;
		case PALVELU:
			dbNames.add("palvelu");
			dbNames.add("palveluhistoria");
			dbNames.add("palvelu_kasite_arvo");
			break;
		case LOOGINEN:
			dbNames.add("looginentietovaranto");
			dbNames.add("looginentietovarantohistoria");
			dbNames.add("fyysinentietovaranto");
			dbNames.add("fyysinentietovarantohistoria");
			break;
		case FYYSINEN:
			dbNames.add("fyysinentietovaranto");
			dbNames.add("fyysinentietovarantohistoria");
			break;
		case PAATIETORYHMA:
			dbNames.add("paatietoryhma");
			dbNames.add("paatietoryhmahistoria");
			break;
		case TIETORYHMA:
			dbNames.add("tietoryhma");
			dbNames.add("tietoryhmahistoria");
			dbNames.add("paatietoryhma");
			dbNames.add("paatietoryhmahistoria");
			dbNames.add("tietoryhmapaatietoryhma");
			dbNames.add("tietoryhmapaatietohistoria");
			dbNames.add("tietoryhmatietovaranto");
			dbNames.add("tietoryhmatietovarantohistoria");
			break;
		case TIETOLAJI:
			dbNames.add("tieto");
			dbNames.add("tietohistoria");
			dbNames.add("looginentietovaranto");
			dbNames.add("looginentietovarantohistoria");
			dbNames.add("tietoryhma");
			dbNames.add("tietoryhmahistoria");
			dbNames.add("tietolooginentietovaranto");
			dbNames.add("tietolooginenhistoria");
			dbNames.add("tietotietoryhma");
			dbNames.add("tietotietoryhmahistoria");
			break;
		case TIETOARKKITEHTUURI:
			clearDB(Catalogue.PAATIETORYHMA);
			clearDB(Catalogue.TIETORYHMA);
			clearDB(Catalogue.TIETOLAJI);
			clearDB(Catalogue.LOOGINEN);
			clearDB(Catalogue.FYYSINEN);
			break;
		case MOLEKYYLILINKKI:
			clearDB(Catalogue.TIETORYHMA);
			clearDB(Catalogue.PAATIETORYHMA);
			clearDB(Catalogue.TIETOLAJI);
			clearDB(Catalogue.LOOGINEN);
			clearDB(Catalogue.FYYSINEN);
			break;
		case HENKILO:
			dbNames.add("jarjestelma_henkilo_rooli");
			dbNames.add("jarjestelma_henkilo_rooli_hist");
			dbNames.add("henkilo");
			dbNames.add("henkilo_temp");
			dbNames.add("rooli");
			break;
		case SOVELLUS:
			dbNames.add("sovellus_henkilo_rooli_hist");
			dbNames.add("sovellus_henkilo_rooli");
			dbNames.add("rooli");
			dbNames.add("henkilo_temp");
			dbNames.add("henkilo");
			dbNames.add("sovellus_history");
			dbNames.add("sovellus");
			dbNames.add("sovellus_tuonti");
			break;
		case TIETOVARANTO:
			dbNames.add("tietovaranto");
			dbNames.add("tietovarantohistoria");
			break;
		case TOIMINTAPROSESSI:
			dbNames.add("toimintaprosessi");
			dbNames.add("toimintaprosessihistoria");
			break;
		case TERMILOMAKE:
			dbNames.add("termilomake");
			dbNames.add("termilomakehistoria");
			break;
		case TIETOJARJESTELMAPALVELU:
			clearDB(Catalogue.JARJESTELMA);
			clearDB(Catalogue.TIETOLAJI);
			dbNames.add("tietojarjestelmapalvelu");
			dbNames.add("tietojarjpalveluhistoria");
			break;
		default:
			return;
		}
		Transaction tx = getSession().beginTransaction();
		for (String dbName : dbNames) {
			SQLQuery query = getSession().createSQLQuery("delete from " + dbName);
			query.executeUpdate();
		}
		tx.commit();
		closeSession();
	}

	public static void disableConstraintsH2() {
		SQLQuery setIntegrityFalse = getSession().createSQLQuery("SET REFERENTIAL_INTEGRITY FALSE;");
		setIntegrityFalse.executeUpdate();
	}

	public static void enableConstraintsH2() {
		SQLQuery setIntegrityTrue = getSession().createSQLQuery("SET REFERENTIAL_INTEGRITY TRUE;");
		setIntegrityTrue.executeUpdate();
	}

	public static void clearJoinTableDB(JoinCategory category) {
		List<String> dbNames = new ArrayList<String>();
		switch (category) {
		case LOOGINENFYYSINEN:
			dbNames.add("looginentietovarantofyysinenti");
			dbNames.add("looginenfyysinenhistoria");
			break;
		default:
			return;
		}
		for (String dbName : dbNames) {
			SQLQuery query = getSession().createSQLQuery("delete from " + dbName);
			query.executeUpdate();
		}
		Transaction tx = getSession().beginTransaction();
		tx.commit();
		closeSession();
	}

	public static void close() {
		sessionFactory.close();
	}

	public static boolean isRowActiveInDb(Class<? extends Haettava> className, int id) {
		Session session = sessionFactory.openSession();
		Criteria crit = session.createCriteria(className);
		crit.add(Restrictions.eq("tunnus", id));
		crit.add(Restrictions.eq("aktiivinen", 1));
		@SuppressWarnings("unchecked")
		List<Haettava> result = crit.list();
		session.close();
		if (result.size() == 1)
			return true;
		return false;
	}

	public static boolean isRowInDb(Class<? extends Haettava> className, int id) {
		Session session = sessionFactory.openSession();
		Criteria crit = session.createCriteria(className);
		crit.add(Restrictions.eq("tunnus", id));
		@SuppressWarnings("unchecked")
		List<Haettava> result = crit.list();
		session.close();
		if (result.size() == 1)
			return true;
		return false;
	}

	public static boolean isRowInHistoryTableDb(Class<? extends HaettavaHistory> className, int id,
			HistoryType historyType) {
		Session session = sessionFactory.openSession();
		Criteria crit = session.createCriteria(className);
		crit.add(Restrictions.eq("tunnus", id));
		crit.add(Restrictions.eq("historiatyyppi", historyType));
		@SuppressWarnings("unchecked")
		List<HaettavaHistory> result = crit.list();
		session.close();
		if (result.size() == 1)
			return true;
		return false;
	}

	public static <T extends Haettava> List<T> getHaettavaRowsFromDb(Class<T> className) {
		Criteria criteria = sessionFactory.openSession().createCriteria(className);
		@SuppressWarnings("unchecked")
		List<T> list = criteria.list();
		Collections.sort(list, new KatalogiComparator());
		return list;
	}

	public static <T extends HaettavaHistory> List<T> getHaettavaHistoryRowsFromDb(Class<T> className) {
		Criteria criteria = sessionFactory.openSession().createCriteria(className);
		@SuppressWarnings("unchecked")
		List<T> list = criteria.list();
		Collections.sort(list, new Comparator<HaettavaHistory>() {

			@Override
			public int compare(HaettavaHistory o1, HaettavaHistory o2) {
				return o2.getRiviluotupvm().compareTo(o1.getRiviluotupvm());
			}
		});
		return list;
	}

	public static <T extends JoinTable> List<T> getJoinRowsFromDb(Class<T> className) {
		Criteria criteria = sessionFactory.openSession().createCriteria(className);
		@SuppressWarnings("unchecked")
		List<T> list = criteria.list();
		return list;
	}

	public static void writeToDb(JSONArray jsonArr) {
		Session session = null;
		try {
			session = getSession();
			Transaction tx = session.beginTransaction();

			for (int i = 0; i < jsonArr.length(); i++) {
				JSONObject json;
				json = jsonArr.getJSONObject(i);
				String sql = TestUtil.jsonToSql(json);
				SQLQuery query = session.createSQLQuery(sql);
				query.executeUpdate();
			}

			tx.commit();
		} catch (Exception e) {
			LOG.error("There was an error: " + e.getMessage());
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public static <T extends JoinHistory> List<T> getJoinHistoryRowsFromDb(Class<T> className) {
		Criteria criteria = sessionFactory.openSession().createCriteria(className);
		@SuppressWarnings("unchecked")
		List<T> list = criteria.list();
		Collections.sort(list, new HistoryComparator());
		return list;
	}

	public static BigInteger getNextSequenceVal(String sequenceName) {
		Session session = sessionFactory.openSession();
		String sql = "select nextval('" + sequenceName + "')";

		try {
			Query query = session.createSQLQuery(sql);
			return (BigInteger) query.uniqueResult();
		} catch(Exception e) {
			LOG.error("getNextSequenceVal failed with the following error: " + e.getMessage());
			LOG.info("fallback to 42");
			return BigInteger.valueOf(42);
		}

	}

}
