package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class JoinTietojarjestelmapalveluTietolajiDao extends JoinMainDao implements JoinDao {
    private List<JoinTietojarjestelmapalveluTietolaji> joinList;

    private final Logger LOG = LoggerFactory.getLogger(JoinTietojarjestelmapalveluTietolajiDao.class);

    @Override
    public JoinDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinDao getDao(List<JoinTietojarjestelmapalveluTietolaji> joinList, String remoteUser) {
        this.remoteUser = remoteUser;
        this.joinList = joinList;
        return this;
    }

    @Override
    public Integer getParentNodeId(int childNodeId) {
        return -1;
    }

    @Override
    public void save(Session session, int tietojarjestelmapalveluID) {
        save(session, tietojarjestelmapalveluID, joinList);
    }

    private void save(Session session, Integer tietojarjestelmapalveluID, List<JoinTietojarjestelmapalveluTietolaji> joinList) {
        for (JoinTietojarjestelmapalveluTietolaji joinObject : joinList) {
            joinObject.setChildNode(tietojarjestelmapalveluID);
            super.createEntry(session, joinObject, new JoinTietojarjestelmapalveluTietolajiHistory());
        }
    }

    private void save(Session session, List<JoinTietojarjestelmapalveluTietolaji> joinList) {
        for (JoinTietojarjestelmapalveluTietolaji joinObject : joinList) {
            super.createEntry(session, joinObject, new JoinTietojarjestelmapalveluTietolajiHistory());
        }
    }


    /**
     * Handles logic for updating the list of tietolaji values related to a tietojarjestelmapalvelu.
     * @param session Hibernate session
     * @param childNode tietojarjestelmapalvelu id
     */
    @Override
    public void update(Session session, int childNode) {
        LOG.info("updating linkkaus");

        Criteria criteria = session.createCriteria(JoinTietojarjestelmapalveluTietolaji.class);
        criteria.add(Restrictions.eq("childNode", childNode));
        List<JoinTietojarjestelmapalveluTietolaji> existingList = (List<JoinTietojarjestelmapalveluTietolaji>) criteria.list();

        for (JoinTietojarjestelmapalveluTietolaji join : this.joinList) {
            join.setChildNode(childNode);
        }

        UpdateChangeListContainer changeLists = getUpdateChangeLists(
                existingList, this.joinList);

        this.save(session, (List<JoinTietojarjestelmapalveluTietolaji>)changeLists.getCreateList());
        this.delete(session, (List<JoinTable>)changeLists.getDeleteList());
    }

    /**
     * Deletes all rows related to the given tietojarjestelmapalvelu id from the join table,
     * and saves a history entry for each deleted row.
     * @param session Hibernate session
     * @param childNode Tietojarjestelmapalvelu ID
     */
    @Override
    public void delete(Session session, int childNode) {
        Criteria criteria = session.createCriteria(JoinTietojarjestelmapalveluTietolaji.class);
        criteria.add(Restrictions.eq("childNode", childNode));
        List<JoinTable> deleteList = criteria.list();

        this.delete(session, deleteList);
    }

    private void delete(Session session, List<JoinTable> deleteList) {
        for (JoinTable joinTable : deleteList) {
            super.delete(session, joinTable, new JoinTietojarjestelmapalveluTietolajiHistory());
        }
    }

}
