package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Termilomake;
import fi.liike.rest.Model.TermilomakeHistory;
import fi.liike.rest.api.*;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TermilomakeDaoImpl extends SearchDaoImpl implements MainDao {

    @Override
    public ModelResults getFiltered(SearchContent searchContent) {
        return super.getFiltered(Termilomake.class, searchContent);
    }

    @Override
    public Haettava get(int id) {
        return get(Termilomake.class, id);
    }

    @Override
    public List<Haettava> getAll() {
        return super.getAll(Termilomake.class);
    }

    @Override
    public Haettava save(Session session, DaoContent saveContent) throws SQLException {
        return super.save(session, saveContent, Termilomake.seq_name, new TermilomakeHistory());
    }

    @Override
    public Haettava update(Session session, DaoContent content) {
        return super.update(session, content, new TermilomakeHistory());
    }

    @Override
    public void delete(int id, DaoContent deleteContent, String remoteUser) throws SQLException {
        super.delete(Termilomake.class, TermilomakeHistory.class, id, new TermilomakeHistory(),
                     deleteContent, remoteUser);
    }

    @Override
    public List<KasiteArvoContent> getResources() {
        Criteria crit = getSession().createCriteria(Termilomake.class);
        List<Termilomake> resources = list(crit);
        closeSession();

        ArrayList<KasiteArvoContent> contents = new ArrayList<>();
        for (Termilomake termilomake : resources) {
            contents.add(new SelitysKasiteArvoContent(
                    termilomake.getTunnus(),
                    termilomake.getNimi(), termilomake.getNimi(), termilomake.getNimi() ));
        }
        return contents;
    }

}
