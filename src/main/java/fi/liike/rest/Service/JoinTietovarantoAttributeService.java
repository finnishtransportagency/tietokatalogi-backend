package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.JoinTietovarantoAttributeDao;
import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Model.JoinTietovarantoAttribute;

import java.util.List;

public class JoinTietovarantoAttributeService implements JoinService {
    private JoinTietovarantoAttributeDao dao;

    public JoinTietovarantoAttributeService() {
        this.dao = new JoinTietovarantoAttributeDao();
    }

    @Override
    public JoinPublicDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinPublicDao getDao(List<JoinTietovarantoAttribute> tietovarantoAttributeList,
                                Class<? extends JoinTietovarantoAttribute> joinTietovarantoAttributeClass,
                                String remoteUser) {
        return dao.getDao(tietovarantoAttributeList, joinTietovarantoAttributeClass, remoteUser);
    }

    @Override
    public Integer getParentNodeId(int childNode) {
        return dao.getParentNodeId(childNode);
    }

    public List<String> getTietovarantoAttributeList(int tietovarantoId,
                                                     Class<? extends JoinTietovarantoAttribute> joinTietovarantoAttributeClass) {
        return dao.getTietovarantoAttributeList(tietovarantoId, joinTietovarantoAttributeClass);
    }

    public List<String> getAllTietovarantoAttributes(Class<? extends JoinTietovarantoAttribute> joinTietovarantoAttributeClass) {
        return dao.getAllTietovarantoAttributes(joinTietovarantoAttributeClass);
    }
}
