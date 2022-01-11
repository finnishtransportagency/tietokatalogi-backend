package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class JoinTJPRelatedJarjestelmaDao extends JoinMainDao implements JoinDao {
    List<JoinTJPRelatedJarjestelma> joinList;
    String remoteUser;

    private final Logger LOG = LoggerFactory.getLogger(JoinJarjestelmaLooginenDao.class);

    @Override
    public JoinDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinDao getDao(List<JoinTJPRelatedJarjestelma> relatedJarjestelmaIds, String remoteUser) {
        this.joinList = relatedJarjestelmaIds;
        this.remoteUser = remoteUser;
        return this;
    }

    @Override
    public Integer getParentNodeId(int childNode) {
        return null;
    }

    @Override
    public void save(Session session, int childNode) {
        // Add child nodes (tietojarjestelmapalvelu ids).
        // Since this is called on tietojarjestelmapalvelu POST, the tjp id (child node) couldn't have been set
        // before (in the service layer) since it didn't exist then.
        for (JoinTJPRelatedJarjestelma joinTJPJarjestelma : this.joinList) {
            joinTJPJarjestelma.setChildNode(childNode);
        }
        this.save(session, this.joinList);
    }

    @Override
    public void update(Session session, int childNode) {
        LOG.info("updating linkkaus");

        Criteria criteria = session.createCriteria(JoinTJPRelatedJarjestelma.class);
        criteria.add(Restrictions.eq("childNode", childNode));
        @SuppressWarnings("unchecked")
        List<JoinTJPRelatedJarjestelma> existingList = (List<JoinTJPRelatedJarjestelma>) criteria.list();

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
            super.createEntry(session, joinItem, new JoinTJPRelatedJarjestelmaHistory());
        }
    }

    private void delete(Session session, List<? extends JoinTable> deleteList) {
        for (JoinTable joinItem : deleteList) {
            super.delete(session, joinItem, new JoinTJPRelatedJarjestelmaHistory());
        }
    }

    public List<Integer> getRelatedJarjestelmaIdsOfTJP(Integer tietojarjestelmapalveluId) {
        Session session = getSession();
        Criteria criteria = super.getJoinCriteriaWithChildNodeId(
                session,
                new JoinTJPRelatedJarjestelma(tietojarjestelmapalveluId)
        );
        @SuppressWarnings("unchecked")
        List<JoinTJPRelatedJarjestelma> results = criteria.list();
        closeSession();
        List<Integer> ids = new ArrayList<>();
        for (JoinTJPRelatedJarjestelma result : results) {
            ids.add(result.getParentNode());
        }
        return ids;
    }
}
