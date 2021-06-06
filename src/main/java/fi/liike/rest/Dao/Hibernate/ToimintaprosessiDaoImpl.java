package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Jarjestelma;
import fi.liike.rest.Model.Sovellus;
import fi.liike.rest.Model.Toimintaprosessi;
import fi.liike.rest.Model.ToimintaprosessiHistory;
import fi.liike.rest.Model.ToimintaprosessiKasite;
import fi.liike.rest.api.DaoContent;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.ModelResults;
import fi.liike.rest.api.SearchContent;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class ToimintaprosessiDaoImpl extends SearchDaoImpl implements MainDao {
    @Override
    public ModelResults getFiltered(SearchContent searchContent) {
        return super.getFiltered(Toimintaprosessi.class, searchContent);
    }

    @Override
    public Haettava get(int id) {
        return super.get(Toimintaprosessi.class, id);
    }

    @Override
    public List<Haettava> getAll() {
        return super.getAll(Toimintaprosessi.class);
    }

    @Override
    public Haettava save(Session session, DaoContent saveContent) throws SQLException {
        return super.save(session, saveContent, Toimintaprosessi.seq_name, new ToimintaprosessiHistory());
    }

    @Override
    public Haettava update(Session session, DaoContent content) {
        return super.update(session, content, new ToimintaprosessiHistory());
    }

    @Override
    public void delete(int id, DaoContent deleteContent, String remoteUser) throws SQLException {
        super.delete(Toimintaprosessi.class, ToimintaprosessiHistory.class, id, new ToimintaprosessiHistory(),
                deleteContent, remoteUser);
    }

    @Override
    public List<KasiteArvoContent> getResources() {
        return super.getResources(ToimintaprosessiKasite.class);
    }
}
