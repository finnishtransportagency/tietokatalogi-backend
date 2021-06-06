package fi.liike.rest.Service;

import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Dao.Hibernate.TermilomakeJoinHierarkkinenKasiteDao;
import fi.liike.rest.Model.Termilomake;
import fi.liike.rest.Model.TermilomakeJoinHierarkkinenKasite;

import java.util.List;

public class TermilomakeHierarkkinenKasiteService implements JoinService {

    private TermilomakeJoinHierarkkinenKasiteDao dao;

    public TermilomakeHierarkkinenKasiteService() {
        this.dao = new TermilomakeJoinHierarkkinenKasiteDao();
    }

    @Override
    public JoinPublicDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinPublicDao getDao(List<TermilomakeJoinHierarkkinenKasite> list, String remoteUser) {
        return this.dao.getDao(list, remoteUser);
    }


    @Override
    public Integer getParentNodeId(int childNode) {
        return dao.getParentNodeId(childNode);
    }


    public List<Integer> getHierarkkinenYlaKasiteIds(Integer termilomakeId, Class<TermilomakeJoinHierarkkinenKasite> termilomakeClass) {
        return dao.getHierarkkinenYlaKasiteIds(termilomakeId, termilomakeClass);
    }
}
