package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.JoinTietoryhmaTietovarantoDao;
import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Dao.JoinPublicDao;

public class JoinTietoryhmaTietovarantoService implements JoinService {
    private JoinDao dao;

    public JoinTietoryhmaTietovarantoService() {
        dao = new JoinTietoryhmaTietovarantoDao();
    }

    @Override
    public JoinPublicDao getDao(int parentNode, String remoteUser) {
        return dao.getDao(parentNode, remoteUser);
    }

    @Override
    public Integer getParentNodeId(int childNode) {
        return dao.getParentNodeId(childNode);
    }
}
