package fi.liike.rest.Service;

import com.google.common.collect.ImmutableSet;
import fi.liike.rest.Dao.Hibernate.JarjestelmaDaoImpl;
import fi.liike.rest.Dao.Hibernate.JoinJarjestelmaLinkkausDao;
import fi.liike.rest.Model.*;
import fi.liike.rest.Model.Dto.MolekyyliLinkkiDto;
import fi.liike.rest.api.*;
import fi.liike.rest.api.Converter.JarjestelmaConverter;
import fi.liike.rest.api.dto.*;
import fi.liike.rest.util.DocumentCreator;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

public class JarjestelmaService extends MinimalFetchService implements Service {

    private final Logger LOG = LoggerFactory.getLogger(JarjestelmaService.class);

    private JarjestelmaDaoImpl dao;
    private JarjestelmaConverter converter;
    private JoinJarjestelmaLinkkausService joinJarjestelmaLinkkausService;
    private HenkiloService henkiloService;
    private MolekyyliLinkkiService molekyyliLinkkiService;

    private static final Set<PersonRole> includeRoles = ImmutableSet.of(PersonRole.OMISTAJA, PersonRole.VASTAAVA, PersonRole.SIJAINEN);

    public JarjestelmaService() {
        super(new JarjestelmaDaoImpl(), new JarjestelmaConverter());
        this.dao = (JarjestelmaDaoImpl) getDao();
        this.converter = (JarjestelmaConverter) getConverter();
        this.joinJarjestelmaLinkkausService = new JoinJarjestelmaLinkkausService();
        this.henkiloService = new HenkiloService();
        this.molekyyliLinkkiService = new MolekyyliLinkkiService();
    }

    @Override
    public ContentDto save(ContentDto content) throws SQLException, IOException {
        return save(null, content);
    }

    @Override
    public ContentDto save(Session session, ContentDto content) throws SQLException, IOException {
        DaoContent saveContent = new DaoContent();
        saveContent.setHaettava(converter.dtoToDomain(content));

        List<JarjestelmaLinkkausDto> jarjestelmaLinkkausList = ((JarjestelmaDto) content).getJarjestelmaLinkkausList();
        List<JarjestelmaLinkkausDto> uniqueJarjestelmaLinkkausList = removeDuplicates(jarjestelmaLinkkausList);

        List<JoinJarjestelmaLinkkaus> joinJarjestelmaLinkkausList = new ArrayList<>();
        this.checkLinkListValidity(uniqueJarjestelmaLinkkausList);

        if (uniqueJarjestelmaLinkkausList != null && uniqueJarjestelmaLinkkausList.size() > 0) {
            for (JarjestelmaLinkkausDto jarjestelmaLinkkausDto : uniqueJarjestelmaLinkkausList) {
                joinJarjestelmaLinkkausList.add(converter.jarjestelmaLinkkausDtoToDomain(jarjestelmaLinkkausDto));
            }
            saveContent.addJoinDao(joinJarjestelmaLinkkausService.getDao(joinJarjestelmaLinkkausList, content.getRivimuokkaajatunnus()));
        }

        Haettava createdHaettava = dao.save(session, saveContent);

        List<HenkiloRooliDto> henkiloRooliList = ((JarjestelmaDto) content).getHenkiloRooliList();
        checkHenkiloRooliListValidity(henkiloRooliList);

        henkiloService.addAndDeleteSystemHenkiloRoolis(
                JarjestelmaHenkiloRooli.class,
                JarjestelmaHenkiloRooliHistory.class,
                ((JarjestelmaDto) content).getHenkiloRooliList(),
                createdHaettava.getTunnus(),
                content.getRivimuokkaajatunnus(),
                "jarjestelmaId",
                includeRoles);


        return get(createdHaettava.getTunnus());
    }

    public Set<JoinHenkiloRooliTable> getHenkiloRooliListByJarjestelmaId(Integer jarjestelmaId) {
        return henkiloService.getHenkiloRooliListBySysteemiId(jarjestelmaId, JarjestelmaHenkiloRooli.class,
                "jarjestelmaId");
    }

    @Override
    public ContentDto update(ContentDto content) throws IOException, SQLException {
        return update(null, content);
    }

    @Override
    public ContentDto update(Session session, ContentDto content) throws IOException, SQLException {
        Integer tunnus = content.getTunnus();
        DaoContent saveContent = new DaoContent();
        saveContent.setHaettava(converter.dtoToDomain(content));
        LOG.info("Update jarjestelma " + tunnus);
        List<JarjestelmaLinkkausDto> jarjestelmaLinkkausList = ((JarjestelmaDto) content).getJarjestelmaLinkkausList();
        List<JarjestelmaLinkkausDto> uniqueJarjestelmaLinkkausList = removeDuplicates(jarjestelmaLinkkausList);
        checkLinkListValidity(uniqueJarjestelmaLinkkausList);

        LOG.info("Update jarjestelmaLinkkausList: " + uniqueJarjestelmaLinkkausList);
        List<JoinJarjestelmaLinkkaus> joinJarjestelmaLinkkausList = new ArrayList<JoinJarjestelmaLinkkaus>();
        if (uniqueJarjestelmaLinkkausList != null) {
            for (JarjestelmaLinkkausDto jarjestelmaLinkkausDto : uniqueJarjestelmaLinkkausList) {
                joinJarjestelmaLinkkausList.add(converter.jarjestelmaLinkkausDtoToDomain(jarjestelmaLinkkausDto));

            }
            LOG.info("Koottu joinJarjestelmaLinkkausList: " + joinJarjestelmaLinkkausList);
            saveContent.addJoinDao(joinJarjestelmaLinkkausService.getDao(joinJarjestelmaLinkkausList, content.getRivimuokkaajatunnus()));
        }
        LOG.info("Palautetaan varmistus: " + joinJarjestelmaLinkkausList);

        dao.update(session, saveContent);
        List<HenkiloRooliDto> henkiloRooliList = ((JarjestelmaDto) content).getHenkiloRooliList();
        checkHenkiloRooliListValidity(henkiloRooliList);

        henkiloService.addAndDeleteSystemHenkiloRoolis(
                JarjestelmaHenkiloRooli.class, JarjestelmaHenkiloRooliHistory.class,
                ((JarjestelmaDto) content).getHenkiloRooliList(), tunnus,
                content.getRivimuokkaajatunnus(), "jarjestelmaId", includeRoles);

        if (tunnus == null) return null;
        return get(tunnus);
    }

    /**
     * Create copy where tietojarjestelmaTunnus < linkattavaTunnus (swap them and change suunta if not)
     */
    public JarjestelmaLinkkausDto createSingleDirectionalLink(JarjestelmaLinkkausDto dto) throws InvocationTargetException, IllegalAccessException {
        JarjestelmaLinkkausDto out = new JarjestelmaLinkkausDto();
        BeanUtils.copyProperties(out, dto);
        if ((dto.getTietojarjestelmaTunnus() != null && dto.getLinkattavaTunnus() != null)
                && dto.getTietojarjestelmaTunnus() > dto.getLinkattavaTunnus()) {
            out.setTietojarjestelmaTunnus(dto.getLinkattavaTunnus());
            out.setLinkattavaTunnus(dto.getTietojarjestelmaTunnus());
            out.setSuunta(dto.getSuunta().equals("Kirjoitus") ? "Luku" : "Kirjoitus");
        }
        return out;
    }

    public List<JarjestelmaLinkkausDto> removeDuplicates(List<JarjestelmaLinkkausDto> linkList) {
        List<JarjestelmaLinkkausDto> uniqueList = new ArrayList<>();
        if (linkList == null) return uniqueList;
        Set<JarjestelmaLinkkausDto> linkSet = new HashSet<>();
        for (JarjestelmaLinkkausDto link : linkList) {
            try {
                JarjestelmaLinkkausDto directionalLink = createSingleDirectionalLink(link);
                if (!linkSet.contains(directionalLink)) {
                    linkSet.add(directionalLink);
                    uniqueList.add(link);
                } else {
                    linkSet.add(directionalLink);
                }
            } catch (InvocationTargetException | IllegalAccessException e) {
                LOG.error("Failed to make link " + link + " single directional");
            }
        }
        return uniqueList;
    }

    public void checkLinkListValidity(List<JarjestelmaLinkkausDto> jarjLinkkausList) throws IOException {
        if (jarjLinkkausList == null || jarjLinkkausList.size() == 0) return;

        Boolean isInvalid = false;
        String message = "";

        for (JarjestelmaLinkkausDto jarjLink1 : jarjLinkkausList) {
            if (jarjLink1.getLinkattavaTunnus() == null) {
                isInvalid = true;
                message = "linkattava puuttuu";
            }

            if (!isInvalid && jarjLink1.getTietojarjestelmaTunnus() != null &&
                    jarjLink1.getTietojarjestelmaTunnus().equals(jarjLink1.getLinkattavaTunnus()) &&
                    jarjLink1.getTyyppi().equals("Järjestelmä")
            ) {
                isInvalid = true;
                message = "linkkaus osoittaa itseensä";
            }

            // Check for duplicates. Note the equals method of JarjestelmaLinkkausDto that compares links
            // symmetrically.
            if (!isInvalid) {
                if (jarjLinkkausList.indexOf(jarjLink1) != jarjLinkkausList.lastIndexOf(jarjLink1)) {
                    isInvalid = true;
                    message = "duplikaatti";
                }
            }
            if (isInvalid) {
                throw new IOException("Virheellinen järjestelmälinkkaus (" + message + ")");
            }
        }
    }

    private void checkHenkiloRooliListValidity(List<HenkiloRooliDto> henkiloRooliList) throws IOException {
        if (henkiloRooliList == null || henkiloRooliList.size() == 0) return;

        Boolean isInvalid = false;

        for (HenkiloRooliDto henkiloRooli1 : henkiloRooliList) {
            if (henkiloRooli1 == null) {
                isInvalid = true;
            }

            if (!isInvalid && henkiloRooli1.getHenkiloId() == null && henkiloRooli1.getRooliId() == null) {
                isInvalid = true;
            }

            if (isInvalid) {
                throw new IOException();
            }
        }
    }

    @Override
    public Integer delete(int id, String remoteUser) {
        try {
            List<JoinJarjestelmaLinkkaus> joinList = dao.getJoinJarjestelmaLinkkausList(id);
            DaoContent daoContent = new DaoContent();
            JoinJarjestelmaLinkkausDao joinDao = new JoinJarjestelmaLinkkausDao();
            daoContent.addJoinDao(joinDao.getDao(joinList, remoteUser));
            dao.delete(id, daoContent, remoteUser);
            return id;
        } catch (SQLException e) {
            LOG.error("Unable to do a delete transaction. Error message: " + e.getMessage());
            return null;
        }
    }

    @Override
    public DtoResults getFiltered(SearchContent searchContent) {
        ModelResults filtered = dao.getFiltered(searchContent);
        List<Haettava> modelResults = filtered.getHaettavat();

        ArrayList<ContentDto> dtoResults = new ArrayList<>();
        addLinksAndConvertJarjestelmaList(modelResults, dtoResults);

        return new DtoResults(dtoResults, filtered.getTotalCount());
    }


    public DtoResults getFilteredGeneral(SearchContent searchContent) {
        ModelResults filtered = dao.getFiltered(searchContent);
        List<Haettava> modelResults = filtered.getHaettavat();

        ArrayList<ContentDto> dtoResults = new ArrayList<>();
        for (Haettava haettava : modelResults) {
            Integer tunnus = haettava.getTunnus();
            dtoResults.add(addGeneralLinksAndConvert(haettava, tunnus));
        }

        return new DtoResults(dtoResults, filtered.getTotalCount());
    }


    public DtoResults getFilteredForSahke(SearchContent searchContent) {
        ModelResults filtered = dao.getFiltered(searchContent);
        ArrayList<ContentDto> dtoResults = new ArrayList<>();
        List<Haettava> modelResults = filtered.getHaettavat();

        Set<Integer> systemIds = new HashSet<>();
        for (Haettava haettava : modelResults) {
            systemIds.add(haettava.getTunnus());
        }
        Map<Integer, Set<FetchHenkiloRooliDto>> mapSystemIdToHenkiloRooliSet = henkiloService.getFetchHenkiloRooliListBySystemIds(systemIds,
                true, JarjestelmaHenkiloRooli.class, "JARJESTELMA_ID");

        for (Haettava haettava : modelResults) {
            Set<FetchHenkiloRooliDto> fetchHenkiloRoolis = mapSystemIdToHenkiloRooliSet.get(haettava.getTunnus());
            dtoResults.add(converter.modelToFilteredSahkeDto(haettava, new ArrayList<>(fetchHenkiloRoolis)));
        }

        return new DtoResults(dtoResults, filtered.getTotalCount());
    }

    @Override
    public List<ContentDto> getAll() {
        ArrayList<ContentDto> dtoResults = new ArrayList<>();
        addLinksAndConvertJarjestelmaList(dao.getAll(), dtoResults);
        return dtoResults;
    }

    @Override
    public ContentDto get(int id) {
        Haettava jarjestelmaModel = dao.get(id);
        if (jarjestelmaModel == null) return null;
        return addLinksAndConvert(jarjestelmaModel, id);
    }

    private void addLinksAndConvertJarjestelmaList(List<Haettava> modelResults, List<ContentDto> dtoResults) {
        for (Haettava haettava : modelResults) {
            Integer tunnus = haettava.getTunnus();
            dtoResults.add(addLinksAndConvert(haettava, tunnus));
        }
    }

    private ContentDto addLinksAndConvert(Haettava jarjestelmaModel, Integer tunnus) {
        List<JoinJarjestelmaLinkkaus> jarjestelmaLinkkausList = dao.getJoinJarjestelmaLinkkausList(tunnus);
        Set<FetchHenkiloRooliDto> fetchHenkiloRooliDtoList = henkiloService.getFetchHenkiloRooliListBySystemId(tunnus,
                false, JarjestelmaHenkiloRooli.class, "JARJESTELMA_ID");
        MolekyyliLinkkiDto linkData = molekyyliLinkkiService.haeMolekyyliLinkkiDtoJarjestelmaTunnuksella(tunnus.toString());

        JarjestelmaDto result = (JarjestelmaDto) converter.modelToDto(jarjestelmaModel, new ArrayList<>(fetchHenkiloRooliDtoList),
                jarjestelmaLinkkausList, linkData, joinJarjestelmaLinkkausService.getParentNodeId(tunnus));

        addNameInfoToLinks(result.getJarjestelmaLinkkausList());
        return result;
    }

    // general all comes here
    private ContentDto addGeneralLinksAndConvert(Haettava jarjestelmaModel, Integer tunnus) {
        List<JoinJarjestelmaLinkkaus> jarjestelmaLinkkausList = dao.getJoinJarjestelmaLinkkausList(tunnus);
        Set<FetchHenkiloRooliDto> fetchHenkiloRooliDtoList = henkiloService.getFetchHenkiloRooliListBySystemId(tunnus,
                false, JarjestelmaHenkiloRooli.class, "JARJESTELMA_ID");

        JarjestelmaGeneralDto result =
                (JarjestelmaGeneralDto) converter.modelToGeneralDto(jarjestelmaModel,
                        new ArrayList<>(fetchHenkiloRooliDtoList), jarjestelmaLinkkausList);

        addNameInfoToLinks(result.getJarjestelmaLinkkausList());
        return result;
    }

    /**
     * Add names for tietolaji (tietovirta) and tietojärjestelmäpalvelu for convenience
     */
    private void addNameInfoToLinks(List<JarjestelmaLinkkausDto> links) {
        for (JarjestelmaLinkkausDto link : links) {
            link.setTietovirtaNimi(dao.getTietovirtaNimi(link.getTietovirta()));
            link.setTietojarjestelmapalveluNimi(dao.getTietojarjestelmapalveluNimi(
                    link.getTietojarjestelmapalveluTunnus())
            );
        }
    }

    @Override
    public List<KasiteArvoContent> getResources() {
        return dao.getResources();
    }

    public ByteArrayOutputStream createDescriptionDocument(String name, int id, ServletContext servletContext) {
        return new DocumentCreator().createOutputStream(this, henkiloService, name, id, servletContext);
    }

}
