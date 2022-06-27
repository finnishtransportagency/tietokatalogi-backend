package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.*;
import fi.liike.rest.api.DaoContent;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.ModelResults;
import fi.liike.rest.api.SearchContent;
import fi.liike.rest.api.dto.ExternalSovellusCSVDto;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Table;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SovellusDaoImpl extends SearchDaoImpl implements MainDao {
    private final Logger LOG = LoggerFactory.getLogger(SovellusDaoImpl.class);

    public Timestamp getLatestImport() {
        Session session = getSession();
        Criteria criteria = session.createCriteria(SovellusImport.class);
        criteria.setProjection(Projections.max("tuontiaika"));
        Timestamp res = (Timestamp) criteria.uniqueResult();
        closeSession();
        return res;
    }

    public Timestamp getLatestSuccessfulImport() {
        Session session = getSession();
        Criteria criteria = session.createCriteria(SovellusImport.class);
        criteria.add(Restrictions.eq("onnistunut", 1));
        criteria.setProjection(Projections.max("tuontiaika"));
        Timestamp res = (Timestamp) criteria.uniqueResult();
        closeSession();
        return res;
    }

    @Override
    public ModelResults getFiltered(SearchContent searchContent) {
        return super.getFiltered(Sovellus.class, searchContent);
    }

    @Override
    public Haettava get(int id) {
        return get(id, true);
    }

    public Haettava get(int id, Boolean filterDisabled) {
        List<Criterion> criterionList = new ArrayList<>();
        if (filterDisabled) {
            criterionList.add(Restrictions.eq("poistunut", 0));
        }

        return super.get(Sovellus.class, id, criterionList);
    }

    public Haettava getByExternalSovellusCSVDto(ExternalSovellusCSVDto externalCSVsovellus) {
        return super.getByExternalSovellusCSVDto(Sovellus.class, externalCSVsovellus);
    }

    @Override
    public List<Haettava> getAll() { return null; }

    @Override
    public Haettava save(Session session, DaoContent saveContent) throws SQLException {
        return null;
    }

    @Override
    public Haettava update(Session session, DaoContent content) {
        return super.update(session, content, new SovellusHistory());
    }

    @Override
    public void delete(int id, DaoContent deleteContent, String remoteUser) throws SQLException {

    }

    @Override
    public List<KasiteArvoContent> getResources() {
        return null;
    }

    public void saveImportMetadata(SovellusImport sovellusImport) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        session.save(sovellusImport);

        transaction.commit();
        session.close();
    }
}
