package fi.liike.rest.Service;

import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Dao.Hibernate.TermilomakeJoinAssosiatiivinenKasiteDao;
import fi.liike.rest.Model.Termilomake;
import fi.liike.rest.Model.TermilomakeJoinAssosiatiivinenKasite;

import java.util.List;

public class TermilomakeAssosiatiivinenKasiteService implements JoinService {

    private TermilomakeJoinAssosiatiivinenKasiteDao dao;

    public TermilomakeAssosiatiivinenKasiteService() {
        this.dao = new TermilomakeJoinAssosiatiivinenKasiteDao();
    }

    @Override
    public JoinPublicDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinPublicDao getDao(List<TermilomakeJoinAssosiatiivinenKasite> list, String remoteUser) {
        return this.dao.getDao(list, remoteUser);
    }


    @Override
    public Integer getParentNodeId(int childNode) {
        return dao.getParentNodeId(childNode);
    }

    public List<Integer> getAssosiatiivinenYlakasiteIds(Integer termilomakeId, Class<TermilomakeJoinAssosiatiivinenKasite> termilomakeClass) {
        return dao.getAssosiatiivinenYlakasiteIds(termilomakeId, termilomakeClass);
    }

}
