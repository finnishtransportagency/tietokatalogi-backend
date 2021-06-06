package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.*;
import fi.liike.rest.api.*;
import fi.liike.rest.api.dto.TietoryhmaMinimalDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;

public class TietojarjestelmapalveluDaoImpl extends SearchDaoImpl implements MainDao {

    private final Logger LOG = LoggerFactory.getLogger(TietojarjestelmapalveluDaoImpl.class);

    @Override
    public ModelResults getFiltered(SearchContent searchContent) {
        return super.getFiltered(TietojarjestelmapalveluFetch.class, searchContent);
    }

    @Override
    public Haettava get(int id) {
        return get(TietojarjestelmapalveluFetch.class, id);
    }

    @Override
    public List<Haettava> getAll() {
        return super.getAll(TietojarjestelmapalveluFetch.class);
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
                "SELECT tunnus AS tunnus, nimi AS nimi FROM tietok.Tietoryhma WHERE tunnus IN :tietoryhmaIDSet")
                .setParameterList("tietoryhmaIDSet", tietoryhmaIDs)
                .setResultTransformer(Transformers.aliasToBean(TietoryhmaMinimalDto.class));
        List<TietoryhmaMinimalDto> dtos = hql.list();

        session.close();
        return new HashSet<>(dtos);
    }

    public Set<Integer> getAllowedTietolajiIDs(Integer jarjestelmaID) {
        if (jarjestelmaID == null) return new HashSet<>();

        Session session = getSession();

        Query sql = session.createSQLQuery("SELECT TIETOTUNNUS as tietolajiID FROM tietok.JARJESTELMA_TIETOARKKITEHTUURI " +
                        "WHERE TIETOJARJESTELMATUNNUS = :jarjestelmaID")
                // ensures that hibernate casts the result to Integer
                .addScalar("tietolajiID", StandardBasicTypes.INTEGER)
                .setParameter("jarjestelmaID", jarjestelmaID);
        List<Integer> tietolajiIDs = sql.list();

        session.close();
        return new HashSet<>(tietolajiIDs);
    }

}