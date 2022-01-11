package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Model.JoinTable;
import fi.liike.rest.Model.TermilomakeJoinHuomautus;
import fi.liike.rest.Model.TermilomakeJoinHuomautusHistory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TermilomakeJoinHuomautusDao extends JoinMainDao implements JoinDao {
    private List<TermilomakeJoinHuomautus> joinList;
    private String remoteUser;

    private final Logger LOG = LoggerFactory.getLogger(TermilomakeJoinHuomautusDao.class);


    @Override
    public JoinDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinDao getDao(List<TermilomakeJoinHuomautus> list, String remoteUser) {
        this.remoteUser = remoteUser;
        this.joinList = list;
        return this;
    }

    public Integer getParentNodeId(int childNode) {
        // Not used
        return null;
    }

    @Override
    public void save(Session session, int parentNode) {
        this.save(session, parentNode, this.joinList);
    }

    private void save(Session session, int parentNode, List<TermilomakeJoinHuomautus> saveList) {
        for (TermilomakeJoinHuomautus joinitem : saveList) {
            joinitem.setParentNode(parentNode);

            super.createEntry(
                    session,
                    joinitem,
                    new TermilomakeJoinHuomautusHistory(parentNode, joinitem.getHuomautus())
            );
        }
    }

    @Override
    public void update(Session session, int parentNode) {
        LOG.info("updating huomautuslinking");
        Criteria criteria = session.createCriteria(TermilomakeJoinHuomautus.class);
        criteria.add(Restrictions.eq("parentNode", parentNode));

        List<TermilomakeJoinHuomautus> existingList =
                (List<TermilomakeJoinHuomautus>) criteria.list();

        UpdateChangeListContainer changeLists = getUpdateChangeLists(existingList, this.joinList);
        this.save(session, parentNode, (List<TermilomakeJoinHuomautus>) changeLists.getCreateList());
        this.delete(session, (List<TermilomakeJoinHuomautus>) changeLists.getDeleteList());
    }

    @Override
    public void delete(Session session, int childNode) {
        this.delete(session, this.joinList);
    }

    private void delete(Session session, List<TermilomakeJoinHuomautus> deleteList) {
        for (TermilomakeJoinHuomautus joinItem : deleteList) {
            super.delete(
                    session,
                    joinItem,
                    new TermilomakeJoinHuomautusHistory(joinItem.getParentNode(), joinItem.getHuomautus())
            );
        }
    }

    public List<String> getHuomautusList(Integer termilomakeId, Class<TermilomakeJoinHuomautus> termilomakeJoinHuomautusClass) {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(termilomakeJoinHuomautusClass);
        criteria.add(Restrictions.eq("parentNode", termilomakeId));
        List<TermilomakeJoinHuomautus> results = criteria.list();
        closeSession();
        List<String> huomautusList = new ArrayList<>();
        for (TermilomakeJoinHuomautus result : results) {
            huomautusList.add(result.getHuomautus());
        }
        return huomautusList;
    }
}
