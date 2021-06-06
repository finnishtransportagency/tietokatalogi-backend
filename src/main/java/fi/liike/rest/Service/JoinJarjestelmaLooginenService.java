package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.JoinJarjestelmaLooginenDao;
import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Model.JoinJarjestelmaLooginen;

import java.util.List;

public class JoinJarjestelmaLooginenService implements JoinService {
    private JoinJarjestelmaLooginenDao dao;

    public JoinJarjestelmaLooginenService() {
        this.dao = new JoinJarjestelmaLooginenDao();
    }

    public JoinPublicDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinPublicDao getDao(List<JoinJarjestelmaLooginen> jarjestelmaIds, String remoteUser) {
        return this.dao.getDao(jarjestelmaIds, remoteUser);
    }

    public Integer getParentNodeId(int childNode) {
        return null;
    }

    public List<Integer> getJarjestelmaIdsOfLooginen(Integer looginenId) {
        return dao.getJarjestelmaIdsOfLooginen(looginenId);
    }
}
