package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Model.JoinJarjestelmaLooginen;
import fi.liike.rest.Model.JoinJarjestelmaLooginenHistory;
import fi.liike.rest.Model.JoinLooginenFyysinen;
import fi.liike.rest.Model.JoinTable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class JoinJarjestelmaLooginenDao extends JoinMainDao implements JoinDao {
    List<JoinJarjestelmaLooginen> joinList;
    String remoteUser;

    private final Logger LOG = LoggerFactory.getLogger(JoinJarjestelmaLooginenDao.class);

    @Override
    public JoinDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinDao getDao(List<JoinJarjestelmaLooginen> jarjestelmaIds, String remoteUser) {
        this.joinList = jarjestelmaIds;
        this.remoteUser = remoteUser;
        return this;
    }

    @Override
    public Integer getParentNodeId(int childNode) {
        return null;
    }

    @Override
    public void save(Session session, int childNode) {
        // Add child nodes (looginen ids).
        // Since this is called on looginen POST, the looginen id (child node) couldn't have been set
        // before (in the service layer) since it didn't exist then.
        for (JoinJarjestelmaLooginen joinJarjestelmaLooginen : this.joinList) {
            joinJarjestelmaLooginen.setChildNode(childNode);
        }
        this.save(session, this.joinList);
    }

    @Override
    public void update(Session session, int childNode) {
        LOG.info("updating linkkaus");

        Criteria criteria = session.createCriteria(JoinJarjestelmaLooginen.class);
        criteria.add(Restrictions.eq("childNode", childNode));
        @SuppressWarnings("unchecked")
        List<JoinJarjestelmaLooginen> existingList = (List<JoinJarjestelmaLooginen>) criteria.list();

        UpdateChangeListContainer changeLists = getUpdateChangeLists(existingList, this.joinList);

        this.save(session, changeLists.getCreateList());
        this.delete(session, changeLists.getDeleteList());
    }

    @Override
    public void delete(Session session, int childNode) {
        this.delete(session, this.joinList);
    }

    private void save(Session session, List<? extends JoinTable> saveList) {
        for (JoinTable joinItem : saveList) {
            super.createEntry(session, joinItem, new JoinJarjestelmaLooginenHistory());
        }
    }

    private void delete(Session session, List<? extends JoinTable> deleteList) {
        for (JoinTable joinItem : deleteList) {
            super.delete(session, joinItem, new JoinJarjestelmaLooginenHistory());
        }
    }

    public List<Integer> getJarjestelmaIdsOfLooginen(Integer looginenId) {
        Session session = getSession();
        Criteria criteria = super.getJoinCriteriaWithChildNodeId(session, new JoinJarjestelmaLooginen(looginenId));
        @SuppressWarnings("unchecked")
        List<JoinJarjestelmaLooginen> results = criteria.list();
        closeSession();
        List<Integer> ids = new ArrayList<>();
        for (JoinJarjestelmaLooginen result : results) {
            ids.add(result.getParentNode());
        }
        return ids;
    }
}
