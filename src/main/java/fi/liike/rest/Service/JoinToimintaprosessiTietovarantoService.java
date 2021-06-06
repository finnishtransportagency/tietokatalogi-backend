package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.JoinToimintaprosessiTietovarantoDao;
import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Model.JoinToimintaprosessiTietovaranto;

import java.util.List;

public class JoinToimintaprosessiTietovarantoService implements JoinService {
    private JoinToimintaprosessiTietovarantoDao dao;

    public JoinToimintaprosessiTietovarantoService() {
        this.dao = new JoinToimintaprosessiTietovarantoDao();
    }

    public JoinPublicDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinPublicDao getDao(List<JoinToimintaprosessiTietovaranto> toimintaprosessiIds, String remoteUser) {
        return this.dao.getDao(toimintaprosessiIds, remoteUser);
    }

    public Integer getParentNodeId(int childNode) {
        return null;
    }

    public List<Integer> getToimintaprosessiIdsOfTietovaranto(Integer tietovarantoId) {
        return dao.getToimintaprosessiIdsOfTietovaranto(tietovarantoId);
    }
}
