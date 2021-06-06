package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Model.JoinTable;
import fi.liike.rest.Model.JoinToimintaprosessiTietovaranto;
import fi.liike.rest.Model.JoinToimintaprosessiTietovarantoHistory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class JoinToimintaprosessiTietovarantoDao extends JoinMainDao implements JoinDao {
    List<JoinToimintaprosessiTietovaranto> joinList;
    String remoteUser;

    private final Logger LOG = LoggerFactory.getLogger(JoinToimintaprosessiTietovarantoDao.class);

    @Override
    public JoinDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinDao getDao(List<JoinToimintaprosessiTietovaranto> jarjestelmaIds, String remoteUser) {
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
        // Add child nodes (tietovaranto ids).
        // Since this is called on tietovaranto POST, the tietovaranto id (child node) couldn't have been set
        // before (in the service layer) since it didn't exist then.
        for (JoinToimintaprosessiTietovaranto joinToimintaprosessiTietovaranto : this.joinList) {
            joinToimintaprosessiTietovaranto.setChildNode(childNode);
        }
        this.save(session, this.joinList);
    }

    @Override
    public void update(Session session, int childNode) {
        LOG.info("updating linkkaus");

        Criteria criteria = session.createCriteria(JoinToimintaprosessiTietovaranto.class);
        criteria.add(Restrictions.eq("childNode", childNode));
        @SuppressWarnings("unchecked")
        List<JoinToimintaprosessiTietovaranto> existingList = (List<JoinToimintaprosessiTietovaranto>) criteria.list();

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
            super.createEntry(session, joinItem, new JoinToimintaprosessiTietovarantoHistory());
        }
    }

    private void delete(Session session, List<? extends JoinTable> deleteList) {
        for (JoinTable joinItem : deleteList) {
            super.delete(session, joinItem, new JoinToimintaprosessiTietovarantoHistory());
        }
    }

    public List<Integer> getToimintaprosessiIdsOfTietovaranto(Integer tietovarantoId) {
        Session session = getSession();
        Criteria criteria = super.getJoinCriteriaWithChildNodeId(session, new JoinToimintaprosessiTietovaranto(tietovarantoId));
        @SuppressWarnings("unchecked")
        List<JoinToimintaprosessiTietovaranto> results = criteria.list();
        closeSession();
        List<Integer> ids = new ArrayList<>();
        for (JoinToimintaprosessiTietovaranto result : results) {
            ids.add(result.getParentNode());
        }
        return ids;
    }
}
