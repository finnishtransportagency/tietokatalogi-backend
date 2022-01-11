package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.*;
import fi.liike.rest.api.*;
import fi.liike.rest.api.dto.TietoryhmaMinimalDto;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;

public class TietojarjestelmapalveluDaoImpl extends SearchDaoImpl implements MainDao {

    private final Logger LOG = LoggerFactory.getLogger(TietojarjestelmapalveluDaoImpl.class);

    @Override
    public ModelResults getFiltered(SearchContent searchContent) {
        return super.getFiltered(Tietojarjestelmapalvelu.class, searchContent);
    }

    @Override
    public Haettava get(int id) {
        return get(Tietojarjestelmapalvelu.class, id);
    }

    @Override
    public List<Haettava> getAll() {
        return super.getAll(Tietojarjestelmapalvelu.class);
    }

    @Override
    public Haettava save(Session session, DaoContent saveContent) throws SQLException {
        return super.save(session, saveContent, "TIETOJARJESTELMAPALVELU_ID_SEQ", new TietojarjestelmapalveluHistory());

    }

    @Override
    public Haettava update(Session session, DaoContent content) {
        return super.update(session, content, new TietojarjestelmapalveluHistory());
    }

    @Override
    public void delete(int id, DaoContent deleteContent, String remoteUser) throws SQLException {
        super.delete(Tietojarjestelmapalvelu.class, TietojarjestelmapalveluHistory.class,
                id, new TietojarjestelmapalveluHistory(), deleteContent, remoteUser);
    }

    @Override
    public List<KasiteArvoContent> getResources() {
        List<KasiteArvoContent> resources = super.getResources(TietojarjestelmapalveluKasite.class);
        resources.addAll(getClassResources(Jarjestelma.class, "Järjestelmä"));
        return resources;
    }

    public Set<TietoryhmaMinimalDto> getMatchingTietoryhmat(Set<Tietolaji> tietolajiSet) {
        if (tietolajiSet.isEmpty()) return new HashSet<>();
        Set<Integer> tietoryhmaIDs = new HashSet<>();
        for (Tietolaji tietolaji : tietolajiSet) {
            tietoryhmaIDs.add(tietolaji.getTietoryhmatunnus());
        }
        Session session = getSession();

        // We use a Hibernate Query to fetch the tietoryhma data separately.
        // This seems more simple than setting another manyToOne relation and using hibernate to fetch
        // Tietojarjestelmapalvelu -> Tietolaji -> Tietoryhma  in the same query.
        Query hql = session.createQuery(
                "SELECT tunnus AS tunnus, nimi AS nimi FROM Tietoryhma WHERE tunnus IN :tietoryhmaIDSet")
                .setParameterList("tietoryhmaIDSet", tietoryhmaIDs)
                .setResultTransformer(Transformers.aliasToBean(TietoryhmaMinimalDto.class));
        List<TietoryhmaMinimalDto> dtos = hql.list();

        session.close();
        return new HashSet<>(dtos);
    }

    public Tietolaji getTietolaji(Integer tietolajiId) {
        if (tietolajiId == null) return null;
        Tietolaji result = null;
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = getSession().createCriteria(Tietolaji.class);
            criteria.add(Restrictions.eq("tunnus", tietolajiId));
            result = (Tietolaji) criteria.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            LOG.error(e.getLocalizedMessage());
        }
        finally {
            session.close();
        }
        return result;
    }

    /**
     * Updates jarjestelma links to reflect a deleted tietojarjestelmapalvelu.
     * The related links' tietojarjestelmapalveluTunnus and kuvaus (tietovirta/tietolaji) are set to null.
     */
    public void nullifyJarjestelmaLinkReferences(int tjpId) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query hql = session.createQuery("UPDATE JoinJarjestelmaLinkkaus SET tietojarjestelmapalveluTunnus = null, " +
                    "kuvaus = null " +
                    "WHERE tietojarjestelmapalveluTunnus = :tjpId");
            hql.setInteger("tjpId", tjpId);
            int rowsAffected = hql.executeUpdate();
            tx.commit();
            LOG.info("Nullified jarjestelma link references to tietojarjestelmapalvelu " + tjpId +
                    ", " + rowsAffected + " rows affected");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            LOG.error(e.getLocalizedMessage());
        }
        finally {
            session.close();
        }
    }
}
