package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.JoinTietojarjestelmapalveluTietolajiDao;
import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Model.JoinTietojarjestelmapalveluTietolaji;

import java.util.List;

public class JoinTietojarjestelmapalveluTietolajiService implements JoinService {
    private JoinTietojarjestelmapalveluTietolajiDao dao;

    public JoinTietojarjestelmapalveluTietolajiService() {
        this.dao = new JoinTietojarjestelmapalveluTietolajiDao();
    }

    @Override
    public JoinPublicDao getDao(int parentNode, String remoteUser) {
        return dao.getDao(parentNode, remoteUser);
    }

    public JoinPublicDao getDao(List<JoinTietojarjestelmapalveluTietolaji> joinList, String remoteUser) {
        return dao.getDao(joinList, remoteUser);
    }

    @Override
    public Integer getParentNodeId(int childNode) {
        return dao.getParentNodeId(childNode);
    }
}
