package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.JoinTietojarjestelmapalveluTietolajiDao;
import fi.liike.rest.Dao.Hibernate.TietojarjestelmapalveluDaoImpl;
import fi.liike.rest.Model.*;
import fi.liike.rest.api.*;
import fi.liike.rest.api.dto.*;
import fi.liike.rest.auth.InvalidTietokatalogiDataException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class TietojarjestelmapalveluService extends MinimalFetchService implements Service {
    private TietojarjestelmapalveluDaoImpl dao;
    private TietojarjestelmapalveluConverter converter;
    private final Logger LOG = LoggerFactory.getLogger(TietojarjestelmapalveluService.class);
    private JoinTietojarjestelmapalveluTietolajiService joinTietojarjestelmapalveluTietolajiService;
    private HenkiloService henkiloService;

    public TietojarjestelmapalveluService() {
        super(new TietojarjestelmapalveluDaoImpl(), new TietojarjestelmapalveluConverter());
        this.dao = (TietojarjestelmapalveluDaoImpl) getDao();
        this.converter = (TietojarjestelmapalveluConverter) getConverter();
        this.joinTietojarjestelmapalveluTietolajiService = new JoinTietojarjestelmapalveluTietolajiService();
        this.henkiloService = new HenkiloService();
    }

    @Override
    public DtoResults getFiltered(SearchContent searchContent) {
        return super.getFiltered(converter, dao, searchContent);
    }

    @Override
    public List<ContentDto> getAll() {
        return null;
    }

    @Override
    public ContentDto save(ContentDto content) throws SQLException {
        return save(null, content);
    }

    @Override
    public ContentDto save(Session session, ContentDto content) throws SQLException {
        LOG.info("Converting tietojarjestelmapalvelu with id " + content.getTunnus());
        DaoContent saveContent = new DaoContent();
        saveContent.setHaettava(this.converter.dtoToDomain(content));

        // Tietolaji data is saved through a separate joinDao
        Set<TietolajiMinimalDto> tietolajiSet = ((TietojarjestelmapalveluDto) content).getTietolajit();
        List<JoinTietojarjestelmapalveluTietolaji> joinTietolajiList = new ArrayList<>();
        if (tietolajiSet != null && !(tietolajiSet.isEmpty())) {
            for (TietolajiMinimalDto dto : tietolajiSet) {
                joinTietolajiList.add(this.converter.linkDtoToDomain(dto));
            }
            saveContent.addJoinDao(joinTietojarjestelmapalveluTietolajiService.getDao(joinTietolajiList, content.getRivimuokkaajatunnus()));
        }

        Haettava createdHaettava = dao.save(session, saveContent);
        return get(createdHaettava.getTunnus());
    }

    /**
     * Validates the tietolaji set associated with a tietojarjestelmapalvelu.
     * Checks that the set is a subset of the set of all tietolaji values associated
     * with the given jarjestelma.
     */
    public boolean tietolajiSetIsValid(ContentDto content) {
        TietojarjestelmapalveluDto dto = (TietojarjestelmapalveluDto) content;
        Set<TietolajiMinimalDto> tietolajit = dto.getTietolajit();
        if (tietolajit == null) return true;
        Set<Integer> tietolajiIDs = new LinkedHashSet<>();
        for (TietolajiMinimalDto tl : tietolajit) {
            tietolajiIDs.add(tl.getTunnus());
        }
        Set<Integer> allowedTietolajiIDs = this.dao.getAllowedTietolajiIDs(dto.getJarjestelma());
        return allowedTietolajiIDs.containsAll(tietolajiIDs);
    }

    @Override
    public ContentDto update(ContentDto content) throws IOException, SQLException, InvalidTietokatalogiDataException {
        if (!this.tietolajiSetIsValid(content)) {
            throw new InvalidTietokatalogiDataException("Virheellinen tietolajien joukko");
        }

        DaoContent updateContent = new DaoContent();
        updateContent.setHaettava(this.converter.dtoToDomain(content));
        LOG.info("Update tietojarjestelmapalvelu " + content.getTunnus());

        Set<TietolajiMinimalDto> tietolajiSet = ((TietojarjestelmapalveluDto) content).getTietolajit();
        LOG.info("Update tietolaji set: " + tietolajiSet);

        List<JoinTietojarjestelmapalveluTietolaji> joinTietolajiList = new ArrayList<>();
        if (tietolajiSet != null) {
            for (TietolajiMinimalDto dto : tietolajiSet) {
                joinTietolajiList.add(this.converter.linkDtoToDomain(dto));
            }
            LOG.info("Koottu joinTietolajiList: " + joinTietolajiList);
        }
        updateContent.addJoinDao(joinTietojarjestelmapalveluTietolajiService.getDao(joinTietolajiList, content.getRivimuokkaajatunnus()));
        this.dao.update(null, updateContent);
        if (content.getTunnus() == null) return null;
        return get(content.getTunnus());
    }

    @Override
    public ContentDto update(Session session, ContentDto content) throws IOException, SQLException {
        return null;
    }

    @Override
    public Integer delete(int id, String remoteUser) {
        try {
            DaoContent deleteContent = new DaoContent();
            // The join dao handles deleting join data
            deleteContent.addJoinDao(new JoinTietojarjestelmapalveluTietolajiDao());
            dao.delete(id, deleteContent, remoteUser);
            return id;
        } catch (SQLException e) {
            LOG.error("Unable to delete content. Error Message:" + e.getMessage());
            return null;
        }
    }

    @Override
    public ContentDto get(int id) {
        TietojarjestelmapalveluFetch domainObject = (TietojarjestelmapalveluFetch) dao.get(id);
        if (domainObject == null) return null;
        TietojarjestelmapalveluDto dto = (TietojarjestelmapalveluDto) converter.modelToDto(domainObject);
        dto.setTietoryhmat(dao.getMatchingTietoryhmat(domainObject.getTietolajit()));
        Set<FetchHenkiloRooliDto> fetchHenkiloRooliDtoList = henkiloService.getFetchHenkiloRooliListBySystemId(
                domainObject.getTietojarjestelmatunnus(), false, JarjestelmaHenkiloRooli.class,
                "JARJESTELMA_ID");
        dto.setHenkiloRooliList(new ArrayList<>(fetchHenkiloRooliDtoList));
        return dto;
    }

    @Override
    public List<KasiteArvoContent> getResources() {
        return this.dao.getResources();
    }



}
