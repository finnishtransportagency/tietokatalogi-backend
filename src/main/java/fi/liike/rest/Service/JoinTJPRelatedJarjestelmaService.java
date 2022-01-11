package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.JoinTJPRelatedJarjestelmaDao;
import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Model.JoinTJPRelatedJarjestelma;

import java.util.List;

public class JoinTJPRelatedJarjestelmaService implements JoinService {
    private JoinTJPRelatedJarjestelmaDao dao;

    public JoinTJPRelatedJarjestelmaService() {
        this.dao = new JoinTJPRelatedJarjestelmaDao();
    }

    public JoinPublicDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinPublicDao getDao(List<JoinTJPRelatedJarjestelma> joinTJPRelatedJarjestelmaList, String remoteUser) {
        return this.dao.getDao(joinTJPRelatedJarjestelmaList, remoteUser);
    }

    public Integer getParentNodeId(int childNode) {
        return null;
    }

    public List<Integer> getRelatedJarjestelmaIdsOfTJP(Integer looginenId) {
        return dao.getRelatedJarjestelmaIdsOfTJP(looginenId);
    }
}




