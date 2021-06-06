package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Organisaatio;
import fi.liike.rest.Model.OrganisaatioHistory;
import fi.liike.rest.api.DaoContent;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.ModelResults;
import fi.liike.rest.api.SearchContent;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class OrganisaatioDaoImpl extends SearchDaoImpl implements MainDao {
    @Override
    public ModelResults getFiltered(SearchContent searchContent) {
        return super.getFiltered(Organisaatio.class, searchContent);
    }

    @Override
    public Haettava get(int id) {
        return super.get(Organisaatio.class, id);
    }

    @Override
    public List<Haettava> getAll() {
        return super.getAll(Organisaatio.class);
    }

    @Override
    public Haettava save(Session session, DaoContent saveContent) throws SQLException {
        return super.save(session, saveContent, Organisaatio.seq_name, new OrganisaatioHistory());
    }

    @Override
    public Haettava update(Session session, DaoContent content) {
        return super.update(session, content, new OrganisaatioHistory());
    }

    @Override
    public void delete(int id, DaoContent deleteContent, String remoteUser) throws SQLException {
        super.delete(Organisaatio.class, OrganisaatioHistory.class, id, new OrganisaatioHistory(),
                deleteContent, remoteUser);
    }

    @Override
    public List<KasiteArvoContent> getResources() {
        return null;
    }
}
