package fi.liike.rest.Service;

import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Dao.Hibernate.TermilomakeJoinKoostumusshteinenKasiteDao;
import fi.liike.rest.Model.Termilomake;
import fi.liike.rest.Model.TermilomakeJoinKoostumussuhteinenKasite;

import java.util.List;

public class TermilomakeKoostumusKasiteService implements JoinService {
    private TermilomakeJoinKoostumusshteinenKasiteDao dao;

    public TermilomakeKoostumusKasiteService() {
        this.dao = new TermilomakeJoinKoostumusshteinenKasiteDao();
    }

    @Override
    public JoinPublicDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinPublicDao getDao(List<TermilomakeJoinKoostumussuhteinenKasite> list, String remoteUser) {
        return this.dao.getDao(list, remoteUser);
    }

    @Override
    public Integer getParentNodeId(int childNode) {
        return dao.getParentNodeId(childNode);
    }

    public List<Integer> getKoostumussuhdeYlakasiteIds(Integer termilomakeId,
                                                       Class<TermilomakeJoinKoostumussuhteinenKasite> termilomakeJoinKoostumussuhteinenKasite) {
        return dao.getKoostumussuhdeYlakasiteIds(termilomakeId, termilomakeJoinKoostumussuhteinenKasite);
    }
}
