package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.TermilomakeDaoImpl;
import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.*;
import fi.liike.rest.api.*;
import fi.liike.rest.api.Converter.TermilomakeConverter;
import fi.liike.rest.api.dto.*;
import org.hibernate.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;

public class TermilomakeService extends MainService implements Service {

    private MainDao dao;
    private TermilomakeConverter converter;
    private TermilomakeHierarkkinenKasiteService hierarkkinenKasiteService;
    private TermilomakeKoostumusKasiteService koostumusKasiteService;
    private TermilomakeAssosiatiivinenKasiteService assosiatiivinenKasiteService;
    private TermilomakeJoinHuomautusService joinHuomautusService;
    private final Logger LOG = LoggerFactory.getLogger(TermilomakeService.class);



    public TermilomakeService() {
        this.dao = new TermilomakeDaoImpl();
        this.converter = new TermilomakeConverter();
        this.hierarkkinenKasiteService = new TermilomakeHierarkkinenKasiteService();
        this.koostumusKasiteService = new TermilomakeKoostumusKasiteService();
        this.assosiatiivinenKasiteService = new TermilomakeAssosiatiivinenKasiteService();
        this.joinHuomautusService = new TermilomakeJoinHuomautusService();

    }

    @Override
    public ContentDto save(ContentDto content) throws SQLException {
        return save(null, content);
    }

    @Override
    public ContentDto save(Session session, ContentDto content) throws SQLException {

        DaoContent dataToSave = prepareDaoContent(content);

        Termilomake savedTermilomake = (Termilomake) dao.save(session, dataToSave);
        // Set editable id to database id if empty
        if (savedTermilomake.getMuokattava_tunnus() == null) {
            savedTermilomake.setMuokattava_tunnus(savedTermilomake.getTunnus());
            DaoContent daoContent = new DaoContent();
            daoContent.setHaettava(savedTermilomake);
            dao.update(session, daoContent);
        }
        return get(savedTermilomake.getTunnus());
    }

    private DaoContent prepareDaoContent(ContentDto content) {
        Integer termilomakeTunnus = ((TermilomakeDto) content).getTunnus();
        DaoContent saveContent = new DaoContent();
        saveContent.setHaettava(converter.dtoToDomain(content));


        List<TermilomakeJoinHuomautus> joinTermilomakeHuomautusList = new ArrayList<>();
        List<String> huomautusList = ((TermilomakeDto) content).gethuomautusList();

        if (huomautusList != null) {
            for (String huomautus : huomautusList) {
                joinTermilomakeHuomautusList.add(new TermilomakeJoinHuomautus(content.getTunnus(), huomautus));
            }
            JoinPublicDao joinTermilomakeHuomautusDao = joinHuomautusService.getDao(
                    joinTermilomakeHuomautusList, content.getRivimuokkaajatunnus());
            saveContent.addJoinDao(joinTermilomakeHuomautusDao);
        }

        // Create joinDao for Koostumussuhteinen kasite joins, 2-sided
        List<TermilomakeJoinKoostumussuhteinenKasite> termilomakeJoinKoostumussuhteinenKasiteList =
                new ArrayList<>();
        List<Integer> koostumussuhteinenKasiteIds = ((TermilomakeDto) content).getKoostumussuht_ylakasite();
        if (koostumussuhteinenKasiteIds != null) {
            // Prevent linking to self
            koostumussuhteinenKasiteIds.remove(termilomakeTunnus);
            for (Integer ylakasiteKoostumussuhdeId : koostumussuhteinenKasiteIds) {
//                koostumussuhteinenKasiteIds List contains yläkäsite relationships -> it's ther parentNode
//                in TermilomakeJoinKoostumussuhteinenKasite. Content.getTunnus() is child ?? where to get
//                the id for child???
                termilomakeJoinKoostumussuhteinenKasiteList.add(
                        new TermilomakeJoinKoostumussuhteinenKasite(
                                content.getTunnus(),
                                ylakasiteKoostumussuhdeId)
                                                               );
                termilomakeJoinKoostumussuhteinenKasiteList.add(
                        new TermilomakeJoinKoostumussuhteinenKasite(
                                ylakasiteKoostumussuhdeId,
                                content.getTunnus())
                                                               );
            }
            JoinPublicDao joinTermilomakeKoostumussuhteinenKasiteDao =
                    koostumusKasiteService.getDao(
                            termilomakeJoinKoostumussuhteinenKasiteList,
                            content.getRivimuokkaajatunnus()
                    );
            saveContent.addJoinDao(joinTermilomakeKoostumussuhteinenKasiteDao);
        }

        // Create joinDao for Hierarkkinen kasite joins, 2-sided
        List<TermilomakeJoinHierarkkinenKasite> termilomakeJoinHierarkkinenKasiteList = new ArrayList<>();
        List<Integer> hierarkkinenKasiteIds = ((TermilomakeDto) content).getHierarkk_ylakasite();
        if (hierarkkinenKasiteIds != null) {
            // Prevent linking to self
            hierarkkinenKasiteIds.remove(termilomakeTunnus);
            for (Integer hierarkkinenId : hierarkkinenKasiteIds) {
                termilomakeJoinHierarkkinenKasiteList.add(
                        new TermilomakeJoinHierarkkinenKasite(
                                content.getTunnus(),
                                hierarkkinenId)
                                                         );
                termilomakeJoinHierarkkinenKasiteList.add(
                        new TermilomakeJoinHierarkkinenKasite(
                                hierarkkinenId,
                                content.getTunnus())
                                                         );
            }
            JoinPublicDao joinTermilomakeHierarkkinenKasiteDao =
                    hierarkkinenKasiteService.getDao(
                            termilomakeJoinHierarkkinenKasiteList,
                            content.getRivimuokkaajatunnus()
                    );
            saveContent.addJoinDao(joinTermilomakeHierarkkinenKasiteDao);
        }

        //Assosiatiivinen link needs to be 2-sided
        List<TermilomakeJoinAssosiatiivinenKasite> termilomakeJoinAssosiatiivinenKasiteList = new ArrayList<>();
        List<Integer> assosiatiiv_kasiteList = ((TermilomakeDto) content).getAssosiatiiv_kasite();
        if (assosiatiiv_kasiteList != null) {
            // Prevent linking to itself
            assosiatiiv_kasiteList.remove(termilomakeTunnus);
            //Add assosiatiivinen linkki to Termilomake in termilomakeList and save
            //Linking needs to be done both ways
            for (Integer assosId : assosiatiiv_kasiteList) {
                termilomakeJoinAssosiatiivinenKasiteList.add(
                        new TermilomakeJoinAssosiatiivinenKasite(
                                content.getTunnus(),
                                assosId)
                                                         );
                termilomakeJoinAssosiatiivinenKasiteList.add(
                        new TermilomakeJoinAssosiatiivinenKasite(
                                assosId,
                                content.getTunnus())
                                                         );
            }
            JoinPublicDao joinTermilomakeAssosiatiivinenKasiteDao =
                    this.assosiatiivinenKasiteService.getDao(
                            termilomakeJoinAssosiatiivinenKasiteList,
                            content.getRivimuokkaajatunnus()
                    );
            saveContent.addJoinDao(joinTermilomakeAssosiatiivinenKasiteDao);
        }

        return saveContent;
    }

    @Override
    public ContentDto update(ContentDto content) throws SQLException {
        return update(null, content);
    }

    @Override
    public ContentDto update(Session session, ContentDto content) throws SQLException {

        DaoContent dataToSave = prepareDaoContent(content);
        Haettava savedTermilomake = dao.update(session, dataToSave);
        if (savedTermilomake != null) {
            return get(savedTermilomake.getTunnus());
        }
        return null;
    }

    @Override
    public Integer delete(int id, String remoteUser) {
        try {
            // Fetch existing Termilomake content including joins
            ContentDto existingContent = this.get(id);
            if (existingContent != null) {

                DaoContent deleteContent = prepareDaoContent(existingContent);
                dao.delete(id, deleteContent, remoteUser);
                return id;
            }
            return null;
        } catch (SQLException e) {
            LOG.error("Unable to do a handle database. Error message: " + e.getMessage());
            return null;
        }
    }

    @Override
    public DtoResults getFiltered(SearchContent searchContent) {
        return super.getFiltered(converter, dao, searchContent);
    }

    @Override
    public ContentDto get(int id) {
        Haettava haettava = dao.get(id);
        return addLinksAndConvert(haettava);
    }


    @Override
    public List<KasiteArvoContent> getResources() {
        ResourceTool resourceTool = new ResourceTool();
        List<KasiteArvoContent> resources = dao.getResources();
        resources.addAll(resourceTool.createResources(Catalogue.TERMILOMAKE_ASSOS_KASITE));
        resources.addAll(resourceTool.createResources(Catalogue.TERMILOMAKE_KOOSTUMUSSUHT_KASITE));
        resources.addAll(resourceTool.createResources(Catalogue.TERMILOMAKE_HIERARKKINEN_KASITE));

        return resources;
    }

    @Override
    public List<ContentDto> getAll() {
        return super.getAll(converter, dao);
    }

    private ContentDto addLinksAndConvert(Haettava haettava) {
        if (haettava != null) {

            Integer termilomakeId = haettava.getTunnus();

            List<String> huomautusList = joinHuomautusService.getHuomautusList(termilomakeId,
                    TermilomakeJoinHuomautus.class);

            List<Integer> hierarkkIds = hierarkkinenKasiteService.getHierarkkinenYlaKasiteIds(termilomakeId
                    , TermilomakeJoinHierarkkinenKasite.class);

            // Fetch both parent and child nodes for assosiatiivinen suhde, then filter out duplicates for UI
            List<Integer> assosIds =
                    assosiatiivinenKasiteService.getAssosiatiivinenYlakasiteIds(termilomakeId
                    , TermilomakeJoinAssosiatiivinenKasite.class);

            List<Integer> koostIds = koostumusKasiteService.getKoostumussuhdeYlakasiteIds(termilomakeId
                    , TermilomakeJoinKoostumussuhteinenKasite.class);


            return converter.modelToDto(haettava, hierarkkIds, assosIds, koostIds, huomautusList);

        }

        return null;
    }


}
