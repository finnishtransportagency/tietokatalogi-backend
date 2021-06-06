package fi.liike.rest.Dao.Hibernate;

import com.google.common.collect.Sets;
import fi.liike.rest.Dao.HibernateSession;
import fi.liike.rest.Model.JoinHistory;
import fi.liike.rest.Model.JoinJarjestelmaLinkkaus;
import fi.liike.rest.Model.JoinTable;
import fi.liike.rest.Model.JoinTietovarantoAttribute;
import fi.liike.rest.api.HistoryType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JoinMainDao extends HibernateSession {

	public String remoteUser;
	private final Logger LOG = LoggerFactory.getLogger(JoinMainDao.class);

	public void createEntry(Session session, JoinTable content,
			JoinHistory newHistory) {
		LOG.info("Saving join entry {} with parentId {} and childId {}, ", content.getClass(), content.getParentNode(), content.getChildNode());

		// check for uniqueness before saving
		List<JoinTable> joinTableList;
		if (content.getClass().equals(JoinJarjestelmaLinkkaus.class)) {
			JoinJarjestelmaLinkkaus linkContent = (JoinJarjestelmaLinkkaus) content;
			joinTableList = getJoinCriteria(session, JoinJarjestelmaLinkkaus.class, "rivitunnus", linkContent.getRivitunnus()).list();
		} else if (content instanceof JoinTietovarantoAttribute) {
			JoinTietovarantoAttribute linkContent = (JoinTietovarantoAttribute) content;
			joinTableList = getJoinTietovarantoAttributeCriteria(session, content.getClass(), linkContent).list();
		} else {
			joinTableList = getJoin(session, content);
		}
		if (!joinTableList.isEmpty()) {
			throw new RuntimeException();
		}

		session.save(content);
		saveHistory(session, content, newHistory, HistoryType.ADD);
	}

	protected void saveHistory(Session session, JoinTable content, JoinHistory newHistory, HistoryType historyType) {
		newHistory.setChildNode(content.getChildNode());
		newHistory.setParentNode(content.getParentNode());
		newHistory.setRivimuokkaajatunnus(remoteUser);
		setCreationTimestamp(newHistory);
		newHistory.setHistoriatyyppi(historyType);

		session.save(newHistory);
	}

	@SuppressWarnings("unchecked")
	private List<JoinTable> getJoin(Session session, JoinTable content) {
		Criteria criteria = session.createCriteria(content.getClass());
		criteria.add(Restrictions.eq("childNode", content.getChildNode()));
		criteria.add(Restrictions.eq("parentNode", content.getParentNode()));
		return criteria.list();
	}

	public void update(Session session, JoinTable content, JoinHistory newHistory) {
		// search current entry with child node id
		Criteria criteria;

		if (content.getClass().equals(JoinJarjestelmaLinkkaus.class)) {
			JoinJarjestelmaLinkkaus linkContent = (JoinJarjestelmaLinkkaus) content;
			criteria = getJoinCriteria(session, JoinJarjestelmaLinkkaus.class, "rivitunnus", linkContent.getRivitunnus());
		} else if (content instanceof JoinTietovarantoAttribute) {
			JoinTietovarantoAttribute linkContent = (JoinTietovarantoAttribute) content;
			criteria = getJoinTietovarantoAttributeCriteria(session, content.getClass(), linkContent);
		} else {
			criteria = getJoinCriteriaWithChildNodeId(session, content);
		}

		// if found update parent id row
		@SuppressWarnings("unchecked")
		List<JoinTable> joinEntries = criteria.list();
		if (joinEntries == null || joinEntries.isEmpty()) {
			createEntry(session, content, newHistory);
			return;
		}

		JoinTable joinEntry = joinEntries.get(0);
		if (joinEntry.getClass().equals(JoinJarjestelmaLinkkaus.class)) {
			JoinJarjestelmaLinkkaus linkContent = (JoinJarjestelmaLinkkaus) content;
			joinEntry.setChildNode(linkContent.getChildNode());
			joinEntry.setParentNode(linkContent.getParentNode());
			((JoinJarjestelmaLinkkaus) joinEntry).setSuunta(linkContent.getSuunta());
			((JoinJarjestelmaLinkkaus) joinEntry).setTietojarjestelmapalveluTunnus(linkContent.getTietojarjestelmapalveluTunnus());
			((JoinJarjestelmaLinkkaus) joinEntry).setTyyppi(linkContent.getTyyppi());
			((JoinJarjestelmaLinkkaus) joinEntry).setKuvaus(linkContent.getKuvaus());
		} else if (content instanceof JoinTietovarantoAttribute) {
			JoinTietovarantoAttribute linkContent = (JoinTietovarantoAttribute) content;
			joinEntry.setParentNode(linkContent.getParentNode());
			((JoinTietovarantoAttribute)joinEntry).setAttribuuttiarvo(linkContent.getAttribuuttiarvo());
		} else {
			joinEntry.setParentNode(content.getParentNode());
		}
		session.update(joinEntry);
		saveHistory(session, content, newHistory, HistoryType.MOD);
	}

	public void delete(Session session, JoinTable content, JoinHistory newHistory) {
		List<JoinTable> joinList;
		if (content.getClass().equals(JoinJarjestelmaLinkkaus.class)) {
			JoinJarjestelmaLinkkaus linkContent = (JoinJarjestelmaLinkkaus) content;
			joinList = getJoinCriteria(session, JoinJarjestelmaLinkkaus.class, "rivitunnus", linkContent.getRivitunnus()).list();
		} else if (content instanceof JoinTietovarantoAttribute) {
			JoinTietovarantoAttribute linkContent = (JoinTietovarantoAttribute) content;
			joinList = getJoinTietovarantoAttributeCriteria(session, content.getClass(), linkContent).list();
		} else {
			joinList = getJoin(session, content);
		}

		if (joinList.isEmpty())
			throw new RuntimeException();
		session.delete(joinList.get(0));
		saveHistory(session, content, newHistory, HistoryType.DEL);
	}

	private void setCreationTimestamp(JoinHistory history) {
		history.setRiviluotupvm(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
	}

	public Integer getParentNodeId(JoinTable content) {
		Session session = getSession();
		Criteria criteria = getJoinCriteriaWithChildNodeId(session, content);
		@SuppressWarnings("unchecked")
		List<JoinTable> results = criteria.list();
		closeSession();
		if (results == null || results.isEmpty())
			return null;
		return results.get(0).getParentNode();
	}

	protected Criteria getJoinCriteriaWithChildNodeId(Session session, JoinTable content) {
		Criteria criteria = session.createCriteria(content.getClass());
		criteria.add(Restrictions.eq("childNode", content.getChildNode()));
		return criteria;
	}

	private Criteria getJoinCriteria(Session session, Class<? extends JoinTable> joinTableClass,  String propertyName,
									 Integer propertyValue) {
		Criteria criteria = session.createCriteria(joinTableClass);
		criteria.add(Restrictions.eq(propertyName, propertyValue));
		return criteria;
	}

	private Criteria getJoinTietovarantoAttributeCriteria(Session session, Class<? extends JoinTable> joinTableClass,
														  JoinTietovarantoAttribute linkContent) {
		Criteria criteria = session.createCriteria(joinTableClass);
		criteria.add(Restrictions.eq("parentNode", linkContent.getParentNode()));
		criteria.add(Restrictions.eq("attribuuttiarvo", linkContent.getAttribuuttiarvo()));
		return criteria;
	}

 	class UpdateChangeListContainer {
	 	private List<? extends JoinTable> deleteList;
	 	private List<? extends JoinTable> createList;

		public <T extends JoinTable> UpdateChangeListContainer(List<T> deleteList, List<T> createList) {
			this.deleteList = deleteList;
			this.createList = createList;
		}


		public List<? extends JoinTable> getDeleteList() {
			return deleteList;
		}

		public List<? extends JoinTable> getCreateList() {
			return createList;
		}
	}

	protected <T extends JoinTable> UpdateChangeListContainer getUpdateChangeLists(
			List<T> existingList, List<T> requestedList) {

		Set<T> newSet = new HashSet<>(requestedList);
		Set<T> existingSet = new HashSet<>(existingList);

		Set<T> saveSet = Sets.difference(newSet, existingSet);
		Set<T> removeSet = Sets.difference(existingSet, newSet);
		List<T> saveList = new ArrayList<>(saveSet);
		List<T> removeList = new ArrayList<>(removeSet);

		return new UpdateChangeListContainer(removeList, saveList);
	}

}
