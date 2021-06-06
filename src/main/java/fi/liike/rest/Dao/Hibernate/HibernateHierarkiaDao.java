package fi.liike.rest.Dao.Hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.*;
import fi.liike.rest.Model.linkitys.LinkitysHierarkia;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fi.liike.rest.Dao.HibernateDao;
import fi.liike.rest.Dao.HierarkiaDao;

public class HibernateHierarkiaDao extends HibernateDao implements HierarkiaDao {


    @Override
    public List<LinkitysHierarkia> haeTunnuksella(
            String tunnus, Class<? extends JoinTable> joinTableClass, Map<String, Object> propertyRestrictionMap,
            MainDao childNodeDao, MainDao parentNodeDao) {

        List<? extends JoinTable> joins = getLinksFromDatabase(joinTableClass, propertyRestrictionMap);
        List<LinkitysHierarkia> linkitysHierarkiaList = new ArrayList<LinkitysHierarkia>();
        for (JoinTable join : joins) {
            Haettava child = childNodeDao.get(join.getChildNode());
            Haettava parent = parentNodeDao.get(join.getParentNode());
            LinkitysHierarkia linkitysHierarkia = new LinkitysHierarkia();
            // This check is needed if the database contains links that don't refer to
            // existing rows in other tables
            if (child != null && parent != null) {
                linkitysHierarkia.setUp(child, parent);
                linkitysHierarkiaList.add(linkitysHierarkia);
            }
        }
        return linkitysHierarkiaList;
    }

    @Override
    public List<LinkitysHierarkia> haeJarjestelmalinkkiTunnuksella(
            String tunnus, Map<String, Object> propertyRestrictionMap,
            MainDao childNodeDao, MainDao parentNodeDao, String childTableName, String parentTableName) {

        Class<JoinJarjestelmaLinkkaus> joinTableClass = JoinJarjestelmaLinkkaus.class;
        List<JoinJarjestelmaLinkkaus> joins = getLinksFromDatabase(joinTableClass, propertyRestrictionMap);

        // Create graph link connections based on link id's and link direction.
        List<LinkitysHierarkia> linkitysHierarkiaList = new ArrayList<LinkitysHierarkia>();
        for (JoinJarjestelmaLinkkaus join : joins) {
            Haettava child = childNodeDao.get(join.getChildNode());
            Haettava parent = parentNodeDao.get(join.getParentNode());
            // This check is needed if the database contains links that don't refer to
            // existing rows in other tables
            if (child != null && parent != null) {
                LinkitysHierarkia linkitysHierarkia = new LinkitysHierarkia();
                if (join.getSuunta().equals("Kirjoitus")) {
                    linkitysHierarkia.setUp(parent, child);
                    linkitysHierarkia.setLahdeTaulu(parentTableName);
                    linkitysHierarkia.setKohdeTaulu(childTableName);
                }
                else { // direction = "Luku"
                    linkitysHierarkia.setUp(child, parent);
                    linkitysHierarkia.setLahdeTaulu(childTableName);
                    linkitysHierarkia.setKohdeTaulu(parentTableName);
                }
                linkitysHierarkiaList.add(linkitysHierarkia);
            }
        }

        return linkitysHierarkiaList;
    }

    private <T extends JoinTable> List<T> getLinksFromDatabase(Class<T> joinTableClass, Map<String, Object> propertyRestrictionMap) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(joinTableClass);
        for (String restriction : propertyRestrictionMap.keySet()) {
            criteria.add(Restrictions.eq(restriction, propertyRestrictionMap.get(restriction)));
        }
        criteria.add(Restrictions.isNotNull("childNode"));
        criteria.add(Restrictions.isNotNull("parentNode"));
        List<T> joins = criteria.list();

        tx.commit();
        session.close();
        return joins;
    }

    @Override
    public int poista(String id) {
        Transaction tx = getSession().beginTransaction();
        //Criteria crit = getSession().createCriteria(MolekyyliHierarkia.class);
        Query deleteQuery = getSession().createSQLQuery(
                "delete from tietovirrat "
                        + "where tietovirta_id = ?");
        deleteQuery.setString(0, id);

        int deleted = deleteQuery.executeUpdate();
        tx.commit();
        getSession().close();
        return deleted;
    }

    @Override
    public int lisaa(String id, int lahde, int kohde, String kuvaus) {
        Transaction tx = getSession().beginTransaction();
        Date luontihetki = new Date(System.currentTimeMillis());
        Query insertQuery = getSession().createSQLQuery(
                "insert into tietovirrat (s_jarjestelma, t_jarjestelma, "
                        + "luotu, muokattu, muokkaaja, linkki, tietosisalto, tietovirta_id) "
                        + "values (?, ?, ?, ?, 'tietokatalogi', ?, ?, ?)");
        insertQuery.setInteger(0, lahde);
        insertQuery.setInteger(1, kohde);
        insertQuery.setTimestamp(2, luontihetki);
        insertQuery.setTimestamp(3, luontihetki);
        insertQuery.setString(4, kuvaus);
        insertQuery.setString(5, kuvaus);
        insertQuery.setString(6, id);
        int added = insertQuery.executeUpdate();
        tx.commit();
        getSession().close();
        return added;
    }

    @Override
    public int paivita(String id, String kuvaus) {
        Transaction tx = getSession().beginTransaction();
        Query updateQuery = getSession().createSQLQuery(
                "update tietovirrat "
                        + "set linkki = ? "
                        + "where tietovirta_id = ?");
        updateQuery.setString(0, kuvaus);
        updateQuery.setString(1, id);

        int updated = updateQuery.executeUpdate();
        tx.commit();
        getSession().close();
        return updated;
    }
}
