package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.JoinTietojarjestelmapalveluTietolajiDao;
import fi.liike.rest.Dao.Hibernate.TietojarjestelmapalveluDaoImpl;
import fi.liike.rest.Dao.JoinPublicDao;
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
    private JoinTJPRelatedJarjestelmaService joinTJPRelatedJarjestelmaService;
    private HenkiloService henkiloService;

    public TietojarjestelmapalveluService() {
        super(new TietojarjestelmapalveluDaoImpl(), new TietojarjestelmapalveluConverter());
        this.dao = (TietojarjestelmapalveluDaoImpl) getDao();
        this.converter = (TietojarjestelmapalveluConverter) getConverter();
        this.joinTietojarjestelmapalveluTietolajiService = new JoinTietojarjestelmapalveluTietolajiService();
        this.joinTJPRelatedJarjestelmaService = new JoinTJPRelatedJarjestelmaService();
        this.henkiloService = new HenkiloService();
    }

    @Override
    public DtoResults getFiltered(SearchContent searchContent) {
        DtoResults results = super.getFiltered(converter, dao, searchContent);
        for (ContentDto contentDto : results.getHaettavat()) {
            TietojarjestelmapalveluDto dto = (TietojarjestelmapalveluDto) contentDto;
            List<Integer> relatedJarjestelmaIds = joinTJPRelatedJarjestelmaService.getRelatedJarjestelmaIdsOfTJP(
                    dto.getTunnus());
            dto.setRelatedJarjestelmaIds(relatedJarjestelmaIds);

            List<JoinTietojarjestelmapalveluTietolaji> joins = joinTietojarjestelmapalveluTietolajiService.getJoinEntries(dto.getTunnus());
            List<Tietolaji> tietolajiList = new ArrayList<>();
            Set<AnnotatedTietolajiDto> tietolajiDtos = new HashSet<>();
            for (JoinTietojarjestelmapalveluTietolaji join : joins) {
                Tietolaji tietolaji = dao.getTietolaji(join.getParentNode());
                tietolajiList.add(tietolaji);
                AnnotatedTietolajiDto tietolajiDto = converter.tietolajiModelToAnnotatedDto(tietolaji);
                tietolajiDto.setLiittyvaJarjestelmaTunnus(join.getLiittyvaJarjestelma());
                tietolajiDto.setLiittyvaJarjestelmaNimi(dao.getJarjestelmaName(join.getLiittyvaJarjestelma()));
                tietolajiDtos.add(tietolajiDto);
            }
            dto.setTietolajit(new HashSet<>(tietolajiDtos));
            dto.setTietoryhmat(dao.getMatchingTietoryhmat(new HashSet<>(tietolajiList)));
        }
        return results;
    }

    @Override
    public List<ContentDto> getAll() {
        return null;
    }

    @Override
    public ContentDto save(ContentDto content) throws SQLException {
        return save(null, content);
    }

    private DaoContent prepareDaoContent(ContentDto content) {
        DaoContent saveUpdateContent = new DaoContent();
        saveUpdateContent.setHaettava(this.converter.dtoToDomain(content));
        LOG.info("Save or update tietojarjestelmapalvelu " + content.getTunnus());

        Set<AnnotatedTietolajiDto> tietolajiSet = ((TietojarjestelmapalveluDto) content).getTietolajit();
        LOG.info("Save or update tietolaji set: " + tietolajiSet);

        List<JoinTietojarjestelmapalveluTietolaji> joinTietolajiList = new ArrayList<>();
        if (tietolajiSet != null) {
            for (AnnotatedTietolajiDto dto : tietolajiSet) {
                joinTietolajiList.add(this.converter.linkDtoToDomain(dto));
            }
        }
        LOG.info("Koottu joinTietolajiList: " + joinTietolajiList);
        saveUpdateContent.addJoinDao(joinTietojarjestelmapalveluTietolajiService.getDao(joinTietolajiList, content.getRivimuokkaajatunnus()));

        // Create joinDao for tjp - related jarjestelma joins
        List<JoinTJPRelatedJarjestelma> joinTJPRelatedJarjestelmaList = new ArrayList<>();
        List<Integer> jarjestelmaIds = ((TietojarjestelmapalveluDto) content).getRelatedJarjestelmaIds();
        if (jarjestelmaIds != null) {
            for (Integer jarjestelmaId : jarjestelmaIds) {
                joinTJPRelatedJarjestelmaList.add(new JoinTJPRelatedJarjestelma(jarjestelmaId, content.getTunnus()));
            }
        }
        LOG.info("Koottu liittyvien järjestelmien lista: " + joinTJPRelatedJarjestelmaList);
        JoinPublicDao joinTJPRelatedJarjestelmaDao = joinTJPRelatedJarjestelmaService.getDao(
                joinTJPRelatedJarjestelmaList, content.getRivimuokkaajatunnus());
        saveUpdateContent.addJoinDao(joinTJPRelatedJarjestelmaDao);
        return saveUpdateContent;
    }

    @Override
    public ContentDto save(Session session, ContentDto content) throws SQLException {
        DaoContent saveContent = prepareDaoContent(content);
        Haettava createdHaettava = dao.save(session, saveContent);
        return get(createdHaettava.getTunnus());
    }


    @Override
    public ContentDto update(ContentDto content) throws IOException, SQLException, InvalidTietokatalogiDataException {
        DaoContent updateContent = prepareDaoContent(content);
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
            List<JoinTJPRelatedJarjestelma> joinTJPRelatedJarjestelmaList = new ArrayList<>();
            List<Integer> jarjestelmaIds = joinTJPRelatedJarjestelmaService.getRelatedJarjestelmaIdsOfTJP(id);
            if (jarjestelmaIds != null) {
                for (Integer jarjestelmaId : jarjestelmaIds) {
                    joinTJPRelatedJarjestelmaList.add(new JoinTJPRelatedJarjestelma(jarjestelmaId, id));
                }
                deleteContent.addJoinDao(joinTJPRelatedJarjestelmaService.getDao(joinTJPRelatedJarjestelmaList, remoteUser));
            }
            dao.nullifyJarjestelmaLinkReferences(id);
            dao.delete(id, deleteContent, remoteUser);
            return id;
        } catch (SQLException e) {
            LOG.error("Unable to delete content. Error Message:" + e.getMessage());
            return null;
        }
    }

    @Override
    public ContentDto get(int id) {
        Tietojarjestelmapalvelu domainObject = (Tietojarjestelmapalvelu) dao.get(id);
        if (domainObject == null) return null;
        List<Integer> relatedJarjestelmaIds = joinTJPRelatedJarjestelmaService.getRelatedJarjestelmaIdsOfTJP(id);
        TietojarjestelmapalveluDto dto = (TietojarjestelmapalveluDto) converter.modelToDto(domainObject, relatedJarjestelmaIds);

        List<JoinTietojarjestelmapalveluTietolaji> joins = joinTietojarjestelmapalveluTietolajiService.getJoinEntries(id);
        List<Tietolaji> tietolajiList = new ArrayList<>();
        Set<AnnotatedTietolajiDto> tietolajiDtos = new HashSet<>();
        for (JoinTietojarjestelmapalveluTietolaji join : joins) {
            Tietolaji tietolaji = dao.getTietolaji(join.getParentNode());
            tietolajiList.add(tietolaji);
            AnnotatedTietolajiDto tietolajiDto = converter.tietolajiModelToAnnotatedDto(tietolaji);
            tietolajiDto.setLiittyvaJarjestelmaTunnus(join.getLiittyvaJarjestelma());
            tietolajiDto.setLiittyvaJarjestelmaNimi(dao.getJarjestelmaName(join.getLiittyvaJarjestelma()));
            tietolajiDtos.add(tietolajiDto);
        }
        dto.setTietolajit(new HashSet<>(tietolajiDtos));
        dto.setTietoryhmat(dao.getMatchingTietoryhmat(new HashSet<>(tietolajiList)));
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
