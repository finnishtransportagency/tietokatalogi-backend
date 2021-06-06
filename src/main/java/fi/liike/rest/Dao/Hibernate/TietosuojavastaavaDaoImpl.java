package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Tietosuojavastaava;
import fi.liike.rest.Model.TietosuojavastaavaHistory;
import fi.liike.rest.api.DaoContent;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.ModelResults;
import fi.liike.rest.api.SearchContent;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class TietosuojavastaavaDaoImpl extends SearchDaoImpl implements MainDao {
    @Override
    public ModelResults getFiltered(SearchContent searchContent) {
        return super.getFiltered(Tietosuojavastaava.class, searchContent);
    }

    @Override
    public Haettava get(int id) {
        return super.get(Tietosuojavastaava.class, id);
    }

    @Override
    public List<Haettava> getAll() {
        return super.getAll(Tietosuojavastaava.class);
    }

    @Override
    public Haettava save(Session session, DaoContent saveContent) throws SQLException {
        return super.save(session, saveContent, Tietosuojavastaava.seq_name, new TietosuojavastaavaHistory());
    }

    @Override
    public Haettava update(Session session, DaoContent content) {
        return super.update(session, content, new TietosuojavastaavaHistory());
    }

    @Override
    public void delete(int id, DaoContent deleteContent, String remoteUser) throws SQLException {
        super.delete(Tietosuojavastaava.class, TietosuojavastaavaHistory.class, id, new TietosuojavastaavaHistory(),
                deleteContent, remoteUser);
    }

    @Override
    public List<KasiteArvoContent> getResources() {
        return null;
    }
}
