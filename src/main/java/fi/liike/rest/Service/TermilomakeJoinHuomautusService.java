package fi.liike.rest.Service;

import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Dao.Hibernate.TermilomakeJoinHuomautusDao;
import fi.liike.rest.Model.TermilomakeJoinHuomautus;

import java.util.List;

public class TermilomakeJoinHuomautusService implements JoinService {

    private TermilomakeJoinHuomautusDao dao;

    public TermilomakeJoinHuomautusService() {
        this.dao = new TermilomakeJoinHuomautusDao();
    }

    @Override
    public JoinPublicDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinPublicDao getDao(List<TermilomakeJoinHuomautus> list, String remoteUser) {
        return this.dao.getDao(list, remoteUser);
    }

    @Override
    public Integer getParentNodeId(int childNode) {
        return dao.getParentNodeId(childNode);
    }

    public List<String> getHuomautusList(Integer termilomakeId, Class<TermilomakeJoinHuomautus> termilomakeJoinHuomautusClass) {
        return dao.getHuomautusList(termilomakeId, termilomakeJoinHuomautusClass);
    }
}
