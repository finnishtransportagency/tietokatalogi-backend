package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.JoinJarjestelmaLinkkausDao;
import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Model.JoinJarjestelmaLinkkaus;

import java.util.List;

public class JoinJarjestelmaLinkkausService implements JoinService {

    private JoinJarjestelmaLinkkausDao dao;

    public JoinJarjestelmaLinkkausService() {
        this.dao = new JoinJarjestelmaLinkkausDao();
    }

    @Override
    public JoinPublicDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinPublicDao getDao(List<JoinJarjestelmaLinkkaus> jarjestelmaLinkkausList, String remoteUser) {
        return dao.getDao(jarjestelmaLinkkausList, remoteUser);
    }

    @Override
    public Integer getParentNodeId(int childNode) {
        return dao.getParentNodeId(childNode);
    }
}
