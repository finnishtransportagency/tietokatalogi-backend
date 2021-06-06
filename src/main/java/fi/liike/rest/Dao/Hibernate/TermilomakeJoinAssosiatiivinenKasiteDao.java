package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Model.JoinTable;
import fi.liike.rest.Model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TermilomakeJoinAssosiatiivinenKasiteDao extends JoinMainDao implements JoinDao {
    List<TermilomakeJoinAssosiatiivinenKasite> joinList;
    String remoteUser;

    private final Logger LOG = LoggerFactory.getLogger(TermilomakeJoinAssosiatiivinenKasiteDao.class);

    @Override
    public JoinDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinDao getDao(List<TermilomakeJoinAssosiatiivinenKasite> list, String remoteUser) {
        this.joinList = list;
        this.remoteUser = remoteUser;
        return this;
    }

    @Override
    public Integer getParentNodeId(int childNode) {
        return null;
    }

    @Override
    public void save(Session session, int childNode) {
        // Add child nodes (tietovaranto ids).
        // Since this is called on tietovaranto POST, the tietovaranto id (child node) couldn't have been set
        // before (in the service layer) since it didn't exist then.
        for (TermilomakeJoinAssosiatiivinenKasite kasite : this.joinList) {
            kasite.setChildNode(childNode);
        }
        this.save(session, this.joinList);
    }

    private void save(Session session, List<? extends JoinTable> saveList) {
        for (JoinTable joinItem : saveList) {
            super.createEntry(
                    session,
                    joinItem,
                    new TermilomakeJoinAssosiatiivinenKasiteHistory()
                             );
        }
    }

    @Override
    public void update(Session session, int childNode) {
        LOG.info("updating linkkaus");

        Criteria criteria = session.createCriteria(TermilomakeJoinAssosiatiivinenKasite.class);
        //Fetch both links
        criteria.add(Restrictions.disjunction()
                             .add(Restrictions.eq("childNode", childNode))
                             .add(Restrictions.eq("parentNode", childNode)));

//        criteria.add(Restrictions.eq("childNode", childNode));
        @SuppressWarnings("unchecked")
        List<TermilomakeJoinAssosiatiivinenKasite> existingList =
                (List<TermilomakeJoinAssosiatiivinenKasite>) criteria.list();

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
                    new TermilomakeJoinAssosiatiivinenKasiteHistory()
            );
        }
    }



    public List<Integer> getAssosiatiivinenYlakasiteIds(Integer termilomakeId, Class<TermilomakeJoinAssosiatiivinenKasite> termilomakeClass) {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(termilomakeClass);
        criteria.add(Restrictions.eq("childNode", termilomakeId));
//        criteria.add(Restrictions.eq("parentNode", termilomakeId));
        List<TermilomakeJoinAssosiatiivinenKasite> results = criteria.list();
        closeSession();
        List<Integer> ids = new ArrayList<>();
        for (TermilomakeJoinAssosiatiivinenKasite result : results) {
            ids.add(result.getParentNode());
        }
        return ids;
    }


}
