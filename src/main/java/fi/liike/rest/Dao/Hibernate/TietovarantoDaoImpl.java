package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.LooginenKasite;
import fi.liike.rest.Model.Tietovaranto;
import fi.liike.rest.Model.TietovarantoHistory;
import fi.liike.rest.Model.TietovarantoKasite;
import fi.liike.rest.api.AreaCodeKasiteArvoContent;
import fi.liike.rest.api.DaoContent;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.ModelResults;
import fi.liike.rest.api.SearchContent;
import fi.liike.rest.api.SelitysKasiteArvoContent;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TietovarantoDaoImpl extends SearchDaoImpl implements MainDao {
    @Override
    public ModelResults getFiltered(SearchContent searchContent) {
        return super.getFiltered(Tietovaranto.class, searchContent);
    }

    @Override
    public Haettava get(int id) {
        return super.get(Tietovaranto.class, id);
    }

    @Override
    public List<Haettava> getAll() {
        return super.getAll(Tietovaranto.class);
    }

    @Override
    public Haettava save(Session session, DaoContent saveContent) throws SQLException {
        return super.save(session, saveContent, Tietovaranto.seq_name, new TietovarantoHistory());
    }

    @Override
    public Haettava update(Session session, DaoContent content) {
        return super.update(session, content, new TietovarantoHistory());
    }

    @Override
    public void delete(int id, DaoContent deleteContent, String remoteUser) throws SQLException {
        super.delete(Tietovaranto.class, TietovarantoHistory.class, id, new TietovarantoHistory(),
               deleteContent, remoteUser);
    }

    @Override
    public List<KasiteArvoContent> getResources() {
        Criteria crit = getSession().createCriteria(TietovarantoKasite.class);
        List<TietovarantoKasite> resources = list(crit);
        closeSession();

        ArrayList<KasiteArvoContent> content = new ArrayList<>();
        for (TietovarantoKasite kasiteArvo : resources) {
            content.add(new SelitysKasiteArvoContent(kasiteArvo.getId(), kasiteArvo.getKasite(),
                    kasiteArvo.getArvo(), kasiteArvo.getSelitys()));
        }

        return content;
    }
}
