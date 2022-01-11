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

public class TermilomakeJoinHierarkkinenKasiteDao extends JoinMainDao implements JoinDao {
    private List<TermilomakeJoinHierarkkinenKasite> joinList;
    private String remoteUser;

    private final Logger LOG = LoggerFactory.getLogger(TermilomakeJoinHierarkkinenKasiteDao.class);

    @Override
    public JoinDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinDao getDao(List<TermilomakeJoinHierarkkinenKasite> termilomakeJoinHierarkkinenKasiteList, String remoteUser) {
        this.remoteUser = remoteUser;
        this.joinList = termilomakeJoinHierarkkinenKasiteList;
        return this;
    }


    @Override
    public Integer getParentNodeId(int childNode) {
        return null;
    }

    @Override
    public void save(Session session, int childNode) {
        for(TermilomakeJoinHierarkkinenKasite hierarkkinenKasite : this.joinList){
            if (hierarkkinenKasite.getChildNode() == null)
                hierarkkinenKasite.setChildNode(childNode);
            if (hierarkkinenKasite.getParentNode() == null)
                hierarkkinenKasite.setParentNode(childNode);
        }
        this.save(session, this.joinList);
    }

    private void save(Session session, List<? extends JoinTable> saveList) {
        for (JoinTable joinItem : saveList) {
            super.createEntry(
                    session,
                    joinItem,
                    new TermilomakeJoinHierarkkinenKasiteHistory()
             );
        }
    }

    @Override
    public void update(Session session, int childNode) {
        LOG.info("updating linkkaus");

        Criteria criteria = session.createCriteria(TermilomakeJoinHierarkkinenKasite.class);
        //Fetch both links
        criteria.add(Restrictions.disjunction()
             .add(Restrictions.eq("childNode", childNode))
             .add(Restrictions.eq("parentNode", childNode)));

//        criteria.add(Restrictions.eq("childNode", childNode));
        @SuppressWarnings("unchecked")
        List<TermilomakeJoinHierarkkinenKasite> existingList =
                (List<TermilomakeJoinHierarkkinenKasite>) criteria.list();

        UpdateChangeListContainer changeLists = getUpdateChangeLists(existingList, this.joinList);

        this.save(session, changeLists.getCreateList());
        this.delete(session, changeLists.getDeleteList());

    }

    @Override
    public void delete(Session session, int childNode) {
        this.delete(session, this.joinList);
    }

    private void delete(Session session, List<? extends JoinTable> deleteList) {
        for (JoinTable joinItem : deleteList) {
            super.delete(
                    session,
                    joinItem,
                    new TermilomakeJoinHierarkkinenKasiteHistory()
            );
        }
    }


    public List<Integer> getHierarkkinenYlaKasiteIds(Integer termilomakeId, Class<TermilomakeJoinHierarkkinenKasite> termilomakeClass) {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(termilomakeClass);
        criteria.add(Restrictions.eq("childNode", termilomakeId));
        List<TermilomakeJoinHierarkkinenKasite> results = criteria.list();
        closeSession();
        List<Integer> ylakasiteIds = new ArrayList<>();
        for (TermilomakeJoinHierarkkinenKasite result : results) {
            ylakasiteIds.add(result.getParentNode());
        }
        return ylakasiteIds;
    }
}
