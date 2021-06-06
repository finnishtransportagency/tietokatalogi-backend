package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.*;
import fi.liike.rest.api.*;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TietoomaisuusDaoImpl extends SearchDaoImpl implements MainDao {

    @Override
    public ModelResults getFiltered(SearchContent searchContent) {
        return super.getFiltered(TietoomaisuusFetch.class, searchContent);
    }

    @Override
    public Haettava get(int id) {
        return get(Tietoomaisuus.class, id);
    }

    @Override
    public List<Haettava> getAll() {
        return null;
    }

    @Override
    public Haettava save(Session session, DaoContent saveContent) throws SQLException {
        return super.save(session, saveContent, "TIETOOMAISUUS_ID_SEQ", new TietoomaisuusHistory());
    }

    @Override
    public Haettava update(Session session, DaoContent content) {
        return super.update(session, content, new TietoomaisuusHistory());
    }

    @Override
    public void delete(int id, DaoContent deleteContent, String remoteUser) throws SQLException {
        super.delete(Tietoomaisuus.class, TietoomaisuusHistory.class, id, new TietoomaisuusHistory(),
                deleteContent, remoteUser);
    }

    @Override
    public List<KasiteArvoContent> getResources() {
        return null;
    }

    public List<KasiteArvoContent> getScoredResources() {
        Criteria crit = getSession().createCriteria(TietoomaisuusKasite.class);
        List<ScoredKasiteArvo> resources = list(crit);
        closeSession();

        ArrayList<KasiteArvoContent> content = new ArrayList<>();
        for (ScoredKasiteArvo kasiteArvo : resources) {
            content.add(new ScoredKasiteArvoContent(kasiteArvo.getId(), kasiteArvo.getKasite(),
                    kasiteArvo.getArvo(), kasiteArvo.getPisteytys()));
        }

        content.addAll(getClassResources(Jarjestelma.class, "Järjestelmä"));
        return content;

    }
}
