package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Model.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public List<JoinTietojarjestelmapalveluTietolaji> getJoinEntries(Integer tjpTunnus) {
        List<JoinTietojarjestelmapalveluTietolaji> results = new ArrayList<>();
        if (tjpTunnus == null) return results;
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = getSession().createCriteria(JoinTietojarjestelmapalveluTietolaji.class);
            criteria.add(Restrictions.eq("childNode", tjpTunnus));
            results = criteria.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            LOG.error(e.getLocalizedMessage());
        }
        finally {
            session.close();
        }
        return results;
    }

    public JoinTietojarjestelmapalveluTietolaji getJoinEntry(Integer tjpTunnus, Integer tietoTunnus) {
        JoinTietojarjestelmapalveluTietolaji result = null;
        if (tjpTunnus == null) return null;
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = getSession().createCriteria(JoinTietojarjestelmapalveluTietolaji.class);
            criteria.add(Restrictions.eq("childNode", tjpTunnus));
            criteria.add(Restrictions.eq("parentNode", tietoTunnus));
            result = (JoinTietojarjestelmapalveluTietolaji) criteria.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            LOG.error(e.getLocalizedMessage());
        }
        finally {
            session.close();
        }
        return result;
    }

    @Override
    public void save(Session session, int tietojarjestelmapalveluID) {
        save(session, tietojarjestelmapalveluID, joinList);
    }

    private void save(Session session, Integer tietojarjestelmapalveluID, List<JoinTietojarjestelmapalveluTietolaji> joinList) {
        for (JoinTietojarjestelmapalveluTietolaji joinObject : joinList) {
            joinObject.setChildNode(tietojarjestelmapalveluID);
            super.createEntry(session, joinObject, new JoinTietojarjestelmapalveluTietolajiHistory(
                    joinObject.getLiittyvaJarjestelma()
            ));
        }
    }

    private void save(Session session, List<JoinTietojarjestelmapalveluTietolaji> joinList) {
        for (JoinTietojarjestelmapalveluTietolaji joinObject : joinList) {
            super.createEntry(session, joinObject, new JoinTietojarjestelmapalveluTietolajiHistory(
                    joinObject.getLiittyvaJarjestelma()
            ));
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
        nullifyReferencesInJarjestelmaLinks(session, (List<JoinTietojarjestelmapalveluTietolaji>)changeLists.getDeleteList(), childNode);
    }

    private void nullifyReferencesInJarjestelmaLinks(Session session, List<JoinTietojarjestelmapalveluTietolaji> linksToDelete, Integer tjpId) {
        if (linksToDelete.isEmpty() || tjpId == null) return;
        List<String> idsToNullify = new ArrayList<>();
        for (JoinTietojarjestelmapalveluTietolaji link : linksToDelete) {
            idsToNullify.add(link.getParentNode().toString());
        }
        Query hql = session.createQuery("UPDATE JoinJarjestelmaLinkkaus SET kuvaus = null " +
                "WHERE tietojarjestelmapalveluTunnus = :tjpId AND kuvaus in (:idsToNullify)");
        hql.setInteger("tjpId", tjpId);
        hql.setParameterList("idsToNullify", idsToNullify);
        int rowsAffected = hql.executeUpdate();
        LOG.info("Nullified jarjestelma link references to tietolaji " + idsToNullify +
                " under tietojarjestelmapalvelu " + tjpId + ", " + rowsAffected + " rows affected");
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
            super.delete(session, joinTable, new JoinTietojarjestelmapalveluTietolajiHistory(
                    ((JoinTietojarjestelmapalveluTietolaji) joinTable).getLiittyvaJarjestelma()
            ));
        }
    }

}
