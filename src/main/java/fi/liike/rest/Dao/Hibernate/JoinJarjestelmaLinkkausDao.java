package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Model.JoinJarjestelmaLinkkaus;
import fi.liike.rest.Model.JoinJarjestelmaLinkkausHistory;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class JoinJarjestelmaLinkkausDao extends JoinMainDao implements JoinDao {
    private List<JoinJarjestelmaLinkkaus> jarjestelmaLinkkausList;

	private final Logger LOG = LoggerFactory.getLogger(JoinJarjestelmaLinkkausDao.class);

    @Override
    public JoinDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinDao getDao(List<JoinJarjestelmaLinkkaus> jarjestelmaLinkkausList, String remoteUser) {
        this.remoteUser = remoteUser;
        this.jarjestelmaLinkkausList = jarjestelmaLinkkausList;
        return this;
    }

    @Override
    public Integer getParentNodeId(int childNodeId) {
        return super.getParentNodeId(new JoinJarjestelmaLinkkaus(childNodeId));
    }

    @Override
    public void save(Session session, int childNode) {
        //In this case childNode is parentNode i.e tietojarjestelmatunnus
        save(session, childNode, jarjestelmaLinkkausList);
    }

    private void save(Session session, Integer parentNode, List<JoinJarjestelmaLinkkaus> joinJarjestelmaLinkkausList) {
        for (JoinJarjestelmaLinkkaus joinJarjestelmaLinkkaus : joinJarjestelmaLinkkausList) {
            if (parentNode != null) joinJarjestelmaLinkkaus.setParentNode(parentNode);
            super.createEntry(session, joinJarjestelmaLinkkaus, new JoinJarjestelmaLinkkausHistory(
                    joinJarjestelmaLinkkaus.getSuunta(),
                    joinJarjestelmaLinkkaus.getTietojarjestelmapalveluTunnus(),
                    joinJarjestelmaLinkkaus.getTyyppi(),
                    joinJarjestelmaLinkkaus.getKuvaus(),
                    joinJarjestelmaLinkkaus.getElinkaaritila()));
        }
    }

    @Override
    public void update(Session session, int childNode) {
        List<JoinJarjestelmaLinkkaus> jarjestelmaLinkkausSaveList = new ArrayList<>();
        List<JoinJarjestelmaLinkkaus> jarjestelmaLinkkausRemoveList = new ArrayList<>();
        List<JoinJarjestelmaLinkkaus> jarjestelmaLinkkausUpdateList = new ArrayList<>();

        Integer tietojarjestelmaTunnus = childNode;

        List<JoinJarjestelmaLinkkaus> linkkausListAllByJarjestelmaTunnus = getJoinJarjestelmaLinkkausList(session, tietojarjestelmaTunnus);
        LOG.info("Onko tyhjä? linkkausListAllByJarjestelmaTunnus" + linkkausListAllByJarjestelmaTunnus.toString());
        for (JoinJarjestelmaLinkkaus joinJarjestelmaLinkkaus : linkkausListAllByJarjestelmaTunnus) {
            if (!listContainsLinkkausByRivitunnus(jarjestelmaLinkkausList, joinJarjestelmaLinkkaus.getRivitunnus())) {
                //Remove jarjestelmaLinkkaus
                jarjestelmaLinkkausRemoveList.add(joinJarjestelmaLinkkaus);
            }
        }
        LOG.info("Poisto lista jarjestelmaLinkkausRemoveList" + jarjestelmaLinkkausRemoveList);
        for (JoinJarjestelmaLinkkaus joinJarjestelmaLinkkaus : jarjestelmaLinkkausList) {
            if (joinJarjestelmaLinkkaus.getRivitunnus() == null) {
                //Save jarjestelmaLinkkaus
                jarjestelmaLinkkausSaveList.add(joinJarjestelmaLinkkaus);
            } else {
                JoinJarjestelmaLinkkaus fetchedLinkkaus = findJoinJarjestelmaLinkkausByRivitunnus(session, joinJarjestelmaLinkkaus.getRivitunnus());
                if (fetchedLinkkaus == null) {
                    //Throw error?
                } else {
                    if (!fetchedLinkkaus.equals(joinJarjestelmaLinkkaus)) {
                        //Update jarjestelmaLinkkaus
                        jarjestelmaLinkkausUpdateList.add(joinJarjestelmaLinkkaus);
                    }
                }
            }
        }
        LOG.info("Tallennuslista jarjestelmaLinkkausSaveList" + jarjestelmaLinkkausSaveList);
        LOG.info("Päivitys lista jarjestelmaLinkkausUpdateList" + jarjestelmaLinkkausUpdateList);
        for (JoinJarjestelmaLinkkaus joinJarjestelmaLinkkaus : jarjestelmaLinkkausUpdateList) {
            super.update(session, joinJarjestelmaLinkkaus, new JoinJarjestelmaLinkkausHistory(
                    joinJarjestelmaLinkkaus.getSuunta(),
                    joinJarjestelmaLinkkaus.getTietojarjestelmapalveluTunnus(),
                    joinJarjestelmaLinkkaus.getTyyppi(),
                    joinJarjestelmaLinkkaus.getKuvaus(),
                    joinJarjestelmaLinkkaus.getElinkaaritila()));
        }

        LOG.info("Jäljellä delete ja save.");
        delete(session, jarjestelmaLinkkausRemoveList);
        save(session, null, jarjestelmaLinkkausSaveList);

    }

    private Boolean listContainsLinkkausByRivitunnus(List<JoinJarjestelmaLinkkaus> joinJarjestelmaLinkkausList, Integer rivitunnus) {
        for (JoinJarjestelmaLinkkaus joinJarjestelmaLinkkaus : joinJarjestelmaLinkkausList) {
            if (joinJarjestelmaLinkkaus.getRivitunnus() != null && joinJarjestelmaLinkkaus.getRivitunnus().equals(rivitunnus)) {
                return true;
            }
        }
        return false;
    }

    private JoinJarjestelmaLinkkaus findJoinJarjestelmaLinkkausByRivitunnus(Session session, Integer rivitunnus) {
        Criteria criteria = session.createCriteria(JoinJarjestelmaLinkkaus.class);
        criteria.add(Restrictions.eq("rivitunnus", rivitunnus));
        List<JoinJarjestelmaLinkkaus> joinJarjestelmaLinkkausList = criteria.list();
        //throw exception if size != 1  ?
        return joinJarjestelmaLinkkausList.get(0);
    }

    public List<JoinJarjestelmaLinkkaus> getJoinJarjestelmaLinkkausList(Session session, Integer tietojarjestelmaTunnus) {
        Criteria criteria = session.createCriteria(JoinJarjestelmaLinkkaus.class);
        Criterion parentEq = Restrictions.eq("parentNode", tietojarjestelmaTunnus);
        Criterion childEq = Restrictions.and(
                Restrictions.eq("childNode", tietojarjestelmaTunnus),
                Restrictions.eq("tyyppi", "Järjestelmä")
        );
        criteria.add(Restrictions.or(parentEq, childEq));
        return criteria.list();
    }

    private void delete(Session session, List<JoinJarjestelmaLinkkaus> joinJarjestelmaLinkkausList) {
        for (JoinJarjestelmaLinkkaus joinJarjestelmaLinkkaus : joinJarjestelmaLinkkausList) {
            super.delete(session, joinJarjestelmaLinkkaus, new JoinJarjestelmaLinkkausHistory(
                    joinJarjestelmaLinkkaus.getSuunta(),
                    joinJarjestelmaLinkkaus.getTietojarjestelmapalveluTunnus(),
                    joinJarjestelmaLinkkaus.getTyyppi(),
                    joinJarjestelmaLinkkaus.getKuvaus(),
                    joinJarjestelmaLinkkaus.getElinkaaritila()
            ));
        }
    }

    @Override
    public void delete(Session session, int childNode) {
        delete(session, this.jarjestelmaLinkkausList);
    }
}
