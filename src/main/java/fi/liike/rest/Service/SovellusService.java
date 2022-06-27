package fi.liike.rest.Service;

import com.google.common.collect.ImmutableSet;
import fi.liike.rest.Dao.Hibernate.SovellusDaoImpl;
import fi.liike.rest.Model.*;
import fi.liike.rest.api.*;
import fi.liike.rest.api.dto.*;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class SovellusService extends MainService implements Service {
    private final SovellusDaoImpl sovellusDao;
    private final SovellusConverter converter;
    private final HenkiloService henkiloService;
    private final HenkiloConverter henkiloConverter;
    private final Logger LOG = LoggerFactory.getLogger(SovellusService.class);

    public SovellusService() {
        this.sovellusDao = new SovellusDaoImpl();
        this.henkiloService = new HenkiloService();
        this.converter = new SovellusConverter();
        this.henkiloConverter = new HenkiloConverter();
    }

    public SovellusImportMetadata getImportMetadata() {
        Timestamp latest = sovellusDao.getLatestImport();
        Timestamp latestSuccessful = sovellusDao.getLatestSuccessfulImport();
        String latestString = "";
        String successfulString = "";
        if (latest != null) {
            latestString = latest.toString();
        }
        if (latestSuccessful != null) {
            successfulString = latestSuccessful.toString();
        }
        return new SovellusImportMetadata(latestString, successfulString);
    }

    public ContentDto get(int id, Boolean filterDisabled) {
        Haettava haettava = sovellusDao.get(id, filterDisabled);
        if (haettava == null) return null;
        List<FetchHenkiloRooliDto> fetchHenkiloRooliList = getFetchHenkiloRooliListBySovellusId((Sovellus) haettava);
        ContentDto content = converter.modelToDto(haettava);
        ((SovellusDto) content).setFetchHenkiloRooliList(fetchHenkiloRooliList);
        return content;
    }

    @Override
    public DtoResults getFiltered(SearchContent searchContent) {
        ModelResults filtered = sovellusDao.getFiltered(searchContent);
        List<Haettava> modelResults = filtered.getHaettavat();
        List<ContentDto> dtoResults = new ArrayList<>();
        for (Haettava haettava : modelResults) {
            List<FetchHenkiloRooliDto> fetchHenkiloRooliList = getFetchHenkiloRooliListBySovellusId((Sovellus) haettava);
            ContentDto contentDto = converter.modelToDto(haettava);
            ((SovellusDto) contentDto).setFetchHenkiloRooliList(new ArrayList<>(fetchHenkiloRooliList));
            dtoResults.add(contentDto);
        }
        return new DtoResults(dtoResults, filtered.getTotalCount());
    }

    private List<FetchHenkiloRooliDto> getFetchHenkiloRooliListBySovellusId(Sovellus sovellus) {
        return henkiloService.getSovellusHenkiloRooliList(sovellus);
    }


    public ContentDto update(SovellusUpdateDto update) throws IOException, SQLException {
        Integer tunnus = update.getTunnus();
        DaoContent saveContent = new DaoContent();
        saveContent.setHaettava(converter.updateDtoToUpdateDomain(update));
        sovellusDao.update(null, saveContent);
        List<HenkiloRooliDto> henkiloRooliList = update.getHenkiloRooliList();
        Set<PersonRole> includeRoles = ImmutableSet.of(PersonRole.OMISTAJA, PersonRole.VASTAAVA);
        if (henkiloRooliList != null) {
            henkiloService.addAndDeleteSystemHenkiloRoolis(
                    SovellusHenkiloRooli.class, SovellusHenkiloRooliHistory.class,
                    update.getHenkiloRooliList(), tunnus,
                    update.getRivimuokkaajatunnus(), "sovellusId", includeRoles);
        }

        if (tunnus == null) return null;
        return get(tunnus);
    }

    public ContentDto deactivateSovellus(Integer id, Boolean deactivate) throws IOException, SQLException {
        SovellusDto currSovellus = (SovellusDto) get(id);
        if (currSovellus == null) return null;
        Set<JoinHenkiloRooliTable> henkiloRooliList = henkiloService.getHenkiloRooliListBySysteemiId(id,
                SovellusHenkiloRooli.class, "sovellusId");
        List<HenkiloRooliDto> henkiloRooliDtoList = henkiloConverter.convertJoinHenkiloRooliTableListToHenkiloRooliDtoList(
                new ArrayList<>(henkiloRooliList));
        SovellusUpdateDto sovellusUpdate = new SovellusUpdateDto(id, currSovellus.getElinkaaritieto(),
                henkiloRooliDtoList, deactivate);
        return update(sovellusUpdate);
    }

    // Most likely unnecessary. But import metadata can still be _read_.
    public void saveImportMetadata(Boolean isSuccessful) throws IOException, SQLException {
        SovellusImport sovellusImport = new SovellusImport();
        sovellusImport.setOnnistunut(isSuccessful ? 1 : 0);
        sovellusImport.setTuontiaika(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        this.sovellusDao.saveImportMetadata(sovellusImport);
    }

    @Override
    public List<ContentDto> getAll() {
        SearchContent searchContent = new SearchContent("", "");
        searchContent.setOffset(0);
        searchContent.setSize(-1);
        return getFiltered(searchContent).getHaettavat();
    }

    @Override
    public ContentDto save(ContentDto content) throws SQLException, IOException {
        return null;
    }

    @Override
    public ContentDto save(Session session, ContentDto content) throws SQLException, IOException {
        return null;
    }

    @Override
    public ContentDto update(ContentDto content) throws IOException, SQLException {
        return null;
    }

    @Override
    public ContentDto update(Session session, ContentDto content) throws IOException, SQLException {
        return null;
    }

    @Override
    public Integer delete(int id, String remoteUser) {
        return null;
    }

    @Override
    public ContentDto get(int id) {
        return get(id, true);
    }

    @Override
    public List<KasiteArvoContent> getResources() {
        return null;
    }
}
