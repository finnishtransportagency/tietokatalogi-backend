package fi.liike.service;

import com.google.common.collect.ImmutableSet;
import fi.liike.rest.Dao.Hibernate.HenkiloDao;
import fi.liike.rest.MainRestTester;
import fi.liike.rest.Model.*;
import fi.liike.rest.Service.FimHenkiloService;
import fi.liike.rest.Service.HenkiloService;
import fi.liike.rest.api.Catalogue;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.HenkiloConverter;
import fi.liike.rest.api.dto.HenkiloDto;
import fi.liike.rest.api.dto.HenkiloRooliDto;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static fi.liike.service.JarjestelmaSalkkuServiceTest.createTestJarjestelma;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class HenkiloServiceTest {

    private HenkiloDao henkiloDao;
    private static HenkiloService henkiloService;
    private HenkiloService mockedHenkiloService;
    private FimHenkiloService fimHenkiloService;
    private MainRestTester mainTester;
    private HenkiloConverter henkiloConverter;
    private HenkiloDao mockedHenkiloDao;

    @Before
    public void setUp() throws SQLException {
        mockedHenkiloDao = mock(HenkiloDao.class);
        henkiloDao = new HenkiloDao();
        henkiloService = new HenkiloService(henkiloDao);
        mockedHenkiloService = new HenkiloService(mockedHenkiloDao);
        fimHenkiloService = new FimHenkiloService();
        mainTester = new MainRestTester(Catalogue.HENKILO);
        henkiloConverter = new HenkiloConverter();
    }

    @After
    public void tearDown() {
        mainTester.clear();
    }


    @Test
    public void modifySystemHenkiloRoolisTest_SAVE() throws IOException, SQLException {
        Class<? extends JoinHenkiloRooliTable> joinClass = SovellusHenkiloRooli.class;
        Class<? extends JoinHenkiloRooliTableHistory> joinHistoryClass = SovellusHenkiloRooliHistory.class;
        Integer systeemiId = 1;
        String rivimuokkaajatunnus = "testMuokkaaja";
        String criterionPropertyName = "sovellusId";

        mockHenkiloRooliFetchingBySysteemiId(systeemiId, joinClass, criterionPropertyName, new HashSet());

        HenkiloRooliDto henkiloRooliDto1 = new HenkiloRooliDto(1, 1);
        JoinHenkiloRooliTable henkiloRooliModel1 = henkiloRooliDtoToModel(henkiloRooliDto1, joinClass, systeemiId);
        List<HenkiloRooliDto> passedList = Collections.singletonList(henkiloRooliDto1);

        mockedHenkiloService.addAndDeleteSystemHenkiloRoolis(joinClass, joinHistoryClass, passedList, systeemiId,
                rivimuokkaajatunnus, criterionPropertyName);

        Set<JoinHenkiloRooliTable> deleteList = new HashSet<>();
        Set<JoinHenkiloRooliTable> addList = new HashSet<>();
        addList.add(henkiloRooliModel1);

        verifyDeleteAndSaveLists(addList, deleteList);
    }

    @Test
    public void modifySystemHenkiloRoolisTest_SAVE2() throws IOException, SQLException {
        Class<? extends JoinHenkiloRooliTable> joinClass = SovellusHenkiloRooli.class;
        Class<? extends JoinHenkiloRooliTableHistory> joinHistoryClass = SovellusHenkiloRooliHistory.class;
        Integer systeemiId = 1;
        String rivimuokkaajatunnus = "testMuokkaaja";
        String criterionPropertyName = "sovellusId";

        HenkiloRooliDto henkiloRooliDto1 = new HenkiloRooliDto(1, 1);
        JoinHenkiloRooliTable henkiloRooliModel1 = henkiloRooliDtoToModel(henkiloRooliDto1, joinClass, systeemiId);

        Set<JoinHenkiloRooliTable> mockedFetchedList = ImmutableSet.of(henkiloRooliModel1);
        mockHenkiloRooliFetchingBySysteemiId(systeemiId, joinClass, criterionPropertyName, mockedFetchedList);

        HenkiloRooliDto henkiloRooliDto2 = new HenkiloRooliDto(2, 2);
        JoinHenkiloRooliTable henkiloRooliModel2 = henkiloRooliDtoToModel(henkiloRooliDto2, joinClass, systeemiId);

        List<HenkiloRooliDto> passedList = Arrays.asList(henkiloRooliDto1, henkiloRooliDto2);
        mockedHenkiloService.addAndDeleteSystemHenkiloRoolis(joinClass, joinHistoryClass, passedList, systeemiId,
                rivimuokkaajatunnus, criterionPropertyName);

        Set<JoinHenkiloRooliTable> deleteList = new HashSet<>();
        Set<JoinHenkiloRooliTable> addList = new HashSet<>();
        addList.add(henkiloRooliModel2);

        verifyDeleteAndSaveLists(addList, deleteList);
    }

    @Test
    public void modifySystemHenkiloRoolisTest_SAVE3() throws IOException, SQLException {
        Class<? extends JoinHenkiloRooliTable> joinClass = SovellusHenkiloRooli.class;
        Class<? extends JoinHenkiloRooliTableHistory> joinHistoryClass = SovellusHenkiloRooliHistory.class;
        Integer systeemiId = 1;
        String rivimuokkaajatunnus = "testMuokkaaja";
        String criterionPropertyName = "sovellusId";

        HenkiloRooliDto henkiloRooliDto1 = new HenkiloRooliDto(2, 1);
        JoinHenkiloRooliTable henkiloRooliModel1 = henkiloRooliDtoToModel(henkiloRooliDto1, joinClass, systeemiId);

        HenkiloRooliDto henkiloRooliDto2 = new HenkiloRooliDto(3, 2);
        JoinHenkiloRooliTable henkiloRooliModel2 = henkiloRooliDtoToModel(henkiloRooliDto2, joinClass, systeemiId);

        HenkiloRooliDto henkiloRooliDto3 = new HenkiloRooliDto(3, 3);
        JoinHenkiloRooliTable henkiloRooliModel3 = henkiloRooliDtoToModel(henkiloRooliDto3, joinClass, systeemiId);

        Set<JoinHenkiloRooliTable> mockedFetchedList = ImmutableSet.of(henkiloRooliModel1, henkiloRooliModel2);
        mockHenkiloRooliFetchingBySysteemiId(systeemiId, joinClass, criterionPropertyName, mockedFetchedList);

        List<HenkiloRooliDto> passedList = Arrays.asList(henkiloRooliDto1, henkiloRooliDto2, henkiloRooliDto3);
        mockedHenkiloService.addAndDeleteSystemHenkiloRoolis(joinClass, joinHistoryClass, passedList, systeemiId,
                rivimuokkaajatunnus, criterionPropertyName);

        Set<JoinHenkiloRooliTable> deleteList = new HashSet<>();
        Set<JoinHenkiloRooliTable> addList = new HashSet<>();
        addList.add(henkiloRooliModel3);

        verifyDeleteAndSaveLists(addList, deleteList);
    }

    @Test
    public void modifySystemHenkiloRoolisTest_SAVE4() throws IOException, SQLException {
        Class<? extends JoinHenkiloRooliTable> joinClass = SovellusHenkiloRooli.class;
        Class<? extends JoinHenkiloRooliTableHistory> joinHistoryClass = SovellusHenkiloRooliHistory.class;
        Integer systeemiId = 1;
        String rivimuokkaajatunnus = "testMuokkaaja";
        String criterionPropertyName = "sovellusId";

        HenkiloRooliDto henkiloRooliDto1 = new HenkiloRooliDto(2, 1);
        JoinHenkiloRooliTable henkiloRooliModel1 = henkiloRooliDtoToModel(henkiloRooliDto1, joinClass, systeemiId);

        HenkiloRooliDto henkiloRooliDto2 = new HenkiloRooliDto(3, 2);
        JoinHenkiloRooliTable henkiloRooliModel2 = henkiloRooliDtoToModel(henkiloRooliDto2, joinClass, systeemiId);

        HenkiloRooliDto henkiloRooliDto3 = new HenkiloRooliDto(3, 3);
        JoinHenkiloRooliTable henkiloRooliModel3 = henkiloRooliDtoToModel(henkiloRooliDto3, joinClass, systeemiId);

        Set<JoinHenkiloRooliTable> mockedFetchedList = ImmutableSet.of(henkiloRooliModel1);
        mockHenkiloRooliFetchingBySysteemiId(systeemiId, joinClass, criterionPropertyName, mockedFetchedList);

        List<HenkiloRooliDto> passedList = Arrays.asList(henkiloRooliDto1, henkiloRooliDto2, henkiloRooliDto3);
        mockedHenkiloService.addAndDeleteSystemHenkiloRoolis(joinClass, joinHistoryClass, passedList, systeemiId,
                rivimuokkaajatunnus, criterionPropertyName);

        Set<JoinHenkiloRooliTable> deleteList = new HashSet<>();
        Set<JoinHenkiloRooliTable> addList = new HashSet<>();
        addList.add(henkiloRooliModel2); addList.add(henkiloRooliModel3);

        verifyDeleteAndSaveLists(addList, deleteList);
    }

    @Test
    public void modifySystemHenkiloRoolisTest_SAVE5() throws IOException, SQLException {
        Class<? extends JoinHenkiloRooliTable> joinClass = SovellusHenkiloRooli.class;
        Class<? extends JoinHenkiloRooliTableHistory> joinHistoryClass = SovellusHenkiloRooliHistory.class;
        Integer systeemiId = 1;
        String rivimuokkaajatunnus = "testMuokkaaja";
        String criterionPropertyName = "sovellusId";

        HenkiloRooliDto henkiloRooliDto1 = new HenkiloRooliDto(1, 1);
        JoinHenkiloRooliTable henkiloRooliModel1 = henkiloRooliDtoToModel(henkiloRooliDto1, joinClass, systeemiId);

        HenkiloRooliDto henkiloRooliDto2 = new HenkiloRooliDto(4, 2);
        JoinHenkiloRooliTable henkiloRooliModel2 = henkiloRooliDtoToModel(henkiloRooliDto2, joinClass, systeemiId);

        HenkiloRooliDto henkiloRooliDto3 = new HenkiloRooliDto(5, 3);
        JoinHenkiloRooliTable henkiloRooliModel3 = henkiloRooliDtoToModel(henkiloRooliDto3, joinClass, systeemiId);

        mockHenkiloRooliFetchingBySysteemiId(systeemiId, joinClass, criterionPropertyName, new HashSet<JoinHenkiloRooliTable>());

        List<HenkiloRooliDto> passedList = Arrays.asList(henkiloRooliDto1, henkiloRooliDto2, henkiloRooliDto3);
        mockedHenkiloService.addAndDeleteSystemHenkiloRoolis(joinClass, joinHistoryClass, passedList, systeemiId,
                rivimuokkaajatunnus, criterionPropertyName, ImmutableSet.of(PersonRole.OMISTAJA));

        Set<JoinHenkiloRooliTable> deleteList = new HashSet<>();
        Set<JoinHenkiloRooliTable> addList = new HashSet<>();
        addList.add(henkiloRooliModel1);

        verifyDeleteAndSaveLists(addList, deleteList);
    }

    @Test
    public void modifySystemHenkiloRoolisTest_DELETE() throws IOException, SQLException {
        Class<? extends JoinHenkiloRooliTable> joinClass = SovellusHenkiloRooli.class;
        Class<? extends JoinHenkiloRooliTableHistory> joinHistoryClass = SovellusHenkiloRooliHistory.class;
        Integer systeemiId = 1;
        String rivimuokkaajatunnus = "testMuokkaaja";
        String criterionPropertyName = "sovellusId";

        HenkiloRooliDto henkiloRooliDto1 = new HenkiloRooliDto(1, 1);
        JoinHenkiloRooliTable henkiloRooliModel1 = henkiloRooliDtoToModel(henkiloRooliDto1, joinClass, systeemiId);

        Set<JoinHenkiloRooliTable> mockedFetchedList = ImmutableSet.of(henkiloRooliModel1);
        mockHenkiloRooliFetchingBySysteemiId(systeemiId, joinClass, criterionPropertyName, mockedFetchedList);

        List<HenkiloRooliDto> passedList = Collections.emptyList();
        mockedHenkiloService.addAndDeleteSystemHenkiloRoolis(joinClass, joinHistoryClass, passedList, systeemiId,
                rivimuokkaajatunnus, criterionPropertyName);

        Set<JoinHenkiloRooliTable> deleteList = new HashSet<>();
        Set<JoinHenkiloRooliTable> addList = new HashSet<>();
        deleteList.add(henkiloRooliModel1);

        verifyDeleteAndSaveLists(addList, deleteList);
    }

    @Test
    public void modifySystemHenkiloRoolisTest_DELETE2() throws IOException, SQLException {
        Class<? extends JoinHenkiloRooliTable> joinClass = SovellusHenkiloRooli.class;
        Class<? extends JoinHenkiloRooliTableHistory> joinHistoryClass = SovellusHenkiloRooliHistory.class;
        Integer systeemiId = 1;
        String rivimuokkaajatunnus = "testMuokkaaja";
        String criterionPropertyName = "sovellusId";

        HenkiloRooliDto henkiloRooliDto1 = new HenkiloRooliDto(2, 1);
        JoinHenkiloRooliTable henkiloRooliModel1 = henkiloRooliDtoToModel(henkiloRooliDto1, joinClass, systeemiId);

        HenkiloRooliDto henkiloRooliDto2 = new HenkiloRooliDto(3, 2);
        JoinHenkiloRooliTable henkiloRooliModel2 = henkiloRooliDtoToModel(henkiloRooliDto2, joinClass, systeemiId);

        HenkiloRooliDto henkiloRooliDto3 = new HenkiloRooliDto(3, 3);
        JoinHenkiloRooliTable henkiloRooliModel3 = henkiloRooliDtoToModel(henkiloRooliDto3, joinClass, systeemiId);

        Set<JoinHenkiloRooliTable> mockedFetchedList = ImmutableSet.of(henkiloRooliModel1, henkiloRooliModel2, henkiloRooliModel3);
        mockHenkiloRooliFetchingBySysteemiId(systeemiId, joinClass, criterionPropertyName, mockedFetchedList);

        List<HenkiloRooliDto> passedList = Arrays.asList(henkiloRooliDto1, henkiloRooliDto3);
        mockedHenkiloService.addAndDeleteSystemHenkiloRoolis(joinClass, joinHistoryClass, passedList, systeemiId,
                rivimuokkaajatunnus, criterionPropertyName);

        Set<JoinHenkiloRooliTable> deleteList = new HashSet<>();
        Set<JoinHenkiloRooliTable> addList = new HashSet<>();
        deleteList.add(henkiloRooliModel2);

        verifyDeleteAndSaveLists(addList, deleteList);
    }

    @Test
    public void modifySystemHenkiloRoolisTest_ADD_AND_DELETE() throws IOException, SQLException {
        Class<? extends JoinHenkiloRooliTable> joinClass = SovellusHenkiloRooli.class;
        Class<? extends JoinHenkiloRooliTableHistory> joinHistoryClass = SovellusHenkiloRooliHistory.class;
        Integer systeemiId = 1;
        String rivimuokkaajatunnus = "testMuokkaaja";
        String criterionPropertyName = "sovellusId";

        HenkiloRooliDto henkiloRooliDto1 = new HenkiloRooliDto(1, 1);
        JoinHenkiloRooliTable henkiloRooliModel1 = henkiloRooliDtoToModel(henkiloRooliDto1, joinClass, systeemiId);

        HenkiloRooliDto henkiloRooliDto2 = new HenkiloRooliDto(2, 2);
        JoinHenkiloRooliTable henkiloRooliModel2 = henkiloRooliDtoToModel(henkiloRooliDto2, joinClass, systeemiId);

        HenkiloRooliDto henkiloRooliDto3 = new HenkiloRooliDto(3, 1);
        JoinHenkiloRooliTable henkiloRooliModel3 = henkiloRooliDtoToModel(henkiloRooliDto3, joinClass, systeemiId);

        HenkiloRooliDto henkiloRooliDto4 = new HenkiloRooliDto(3, 4);
        JoinHenkiloRooliTable henkiloRooliModel4 = henkiloRooliDtoToModel(henkiloRooliDto4, joinClass, systeemiId);

        Set<JoinHenkiloRooliTable> mockedFetchedList = ImmutableSet.of(henkiloRooliModel2);
        List<HenkiloRooliDto> passedList = Arrays.asList(henkiloRooliDto1, henkiloRooliDto3, henkiloRooliDto4);

        mockHenkiloRooliFetchingBySysteemiId(systeemiId, joinClass, criterionPropertyName, mockedFetchedList);
        mockedHenkiloService.addAndDeleteSystemHenkiloRoolis(joinClass, joinHistoryClass, passedList, systeemiId,
                rivimuokkaajatunnus, criterionPropertyName);

        Set<JoinHenkiloRooliTable> deleteList = new HashSet<>();
        Set<JoinHenkiloRooliTable> addList = new HashSet<>();
        deleteList.add(henkiloRooliModel2);
        addList.add(henkiloRooliModel1); addList.add(henkiloRooliModel3); addList.add(henkiloRooliModel4);

        verifyDeleteAndSaveLists(addList, deleteList);
    }

    private void verifyDeleteAndSaveLists(Set<JoinHenkiloRooliTable> addList, Set<JoinHenkiloRooliTable> deleteList) throws SQLException {
        verify(mockedHenkiloDao).deleteHenkiloRooliList((Session) any(), eq(deleteList), eq(SovellusHenkiloRooliHistory.class));
        verify(mockedHenkiloDao).saveHenkiloRooliList((Session) any(), eq(addList), eq(SovellusHenkiloRooliHistory.class));
    }

    private void mockHenkiloRooliFetchingBySysteemiId(Integer systeemiId, Class<? extends JoinHenkiloRooliTable> criteriaClass, String propertyName,
                      Set<JoinHenkiloRooliTable> mockedResult) {
        when(mockedHenkiloDao.getAllHenkiloRooliBySysteemiId(systeemiId, criteriaClass, propertyName,
                ImmutableSet.copyOf(PersonRole.values()))).thenReturn(mockedResult);
    }

    private JoinHenkiloRooliTable henkiloRooliDtoToModel(
            HenkiloRooliDto henkiloRooli, Class<? extends JoinHenkiloRooliTable> joinClass, Integer systeemiId) {
        JoinHenkiloRooliTable joinHenkiloRooli;
        if (joinClass.equals(SovellusHenkiloRooli.class)) {
            joinHenkiloRooli = new SovellusHenkiloRooli();
        } else {
            joinHenkiloRooli = new JarjestelmaHenkiloRooli();
        }
        joinHenkiloRooli.setHenkiloId(henkiloRooli.getHenkiloId());
        joinHenkiloRooli.setRooliId(henkiloRooli.getRooliId());
        joinHenkiloRooli.setSysteemiId(systeemiId);
        return joinHenkiloRooli;
    }

    @Test
    public void getAllHenkiloRooliBySysteemiIdTest() throws IOException, SQLException {
        List<String> rooliNimiList = Arrays.asList("OMISTAJA", "VASTAAVA", "TUOTANTOON_HYVAKSYNYT");
        List<Rooli> rooliList = createTestRooliList(rooliNimiList);
        List<Henkilo> henkiloList = createTestHenkiloList(rooliNimiList);
        createTestJarjestelmaWithHenkiloRooliList(rooliList, henkiloList);
        Set<JoinHenkiloRooliTable> fetchedHenkiloRoolis = henkiloDao.getAllHenkiloRooliBySysteemiId(
                1, JarjestelmaHenkiloRooli.class, "jarjestelmaId",
                ImmutableSet.of(PersonRole.OMISTAJA, PersonRole.VASTAAVA));

        assertThat(fetchedHenkiloRoolis.size(), is(2));
    }

    @Test
    public void saveHenkiloRooliSimpleTest() throws IOException, SQLException {
        List<String> rooliNimiList = Collections.singletonList("OMISTAJA");
        List<Rooli> rooliList = createTestRooliList(rooliNimiList);
        List<Henkilo> henkiloList = createTestHenkiloList(rooliNimiList);
        createTestJarjestelmaWithHenkiloRooliList(rooliList, henkiloList);
    }

    @Test
    public void getHenkiloByRooliAndJarjestelmaTest() throws IOException, SQLException {
        List<String> rooliNimiList = Arrays.asList("OMISTAJA", "VASTAAVA");
        List<Rooli> rooliList = createTestRooliList(rooliNimiList);
        List<Henkilo> henkiloList = createTestHenkiloList(rooliNimiList);
        List<JarjestelmaHenkiloRooli> henkiloRooliList = createTestJarjestelmaWithHenkiloRooliList(rooliList, henkiloList);

        assertEquals(rooliNimiList.size(), rooliList.size());
        assertEquals(rooliList.size(), henkiloList.size());
        assertEquals(henkiloList.size(), henkiloRooliList.size());
        assertEquals(henkiloRooliList.size(), rooliNimiList.size());

        Integer expectedJarjestelmaId = 1;

        for (int i = 0; i < rooliList.size(); i++) {
            Rooli rooli = rooliList.get(i);
            String rooliNimi = rooli.getNimi();
            Henkilo henkilo = henkiloList.get(i);
            assertEquals(henkilo.getEtunimi().contains(rooliNimi), true);
            assertEquals(henkilo.getSukunimi().contains(rooliNimi), true);
            JarjestelmaHenkiloRooli jarjHenkRooli = findJarjHenkRooli(henkiloRooliList, henkilo.getTunnus());

            assertEquals(jarjHenkRooli.getRooliId(), rooli.getTunnus());
            assertEquals(jarjHenkRooli.getHenkiloId(), henkilo.getTunnus());
            assertEquals(jarjHenkRooli.getSysteemiId(), expectedJarjestelmaId);

            List<Henkilo> fetchedHenkiloList = henkiloService.getHenkiloByRooliAndSystemId(rooli.getTunnus(),
                    expectedJarjestelmaId, false, JarjestelmaHenkiloRooli.class, "JARJESTELMA_ID");
            Henkilo fetchedHenkilo = fetchedHenkiloList.get(0);
            assertEquals(fetchedHenkiloList, Collections.singletonList(fetchedHenkilo));
            assertEquals(henkilo, fetchedHenkilo);
        }
    }

    @Test
    public void getHenkiloByRooliAndJarjestelmaTest2() throws IOException, SQLException {
        String omistajaNimi = "OMISTAJA";
        String vastaavaNimi = "VASTAAVA";
        String sijainenNimi = "SIJAINEN";
        List<String> rooliNimiList = Arrays.asList(omistajaNimi, vastaavaNimi, sijainenNimi);
        List<Rooli> rooliList = createTestRooliList(rooliNimiList);
        Rooli omistaja = rooliList.get(0);
        assertEquals(omistaja.getNimi(), omistajaNimi);
        Rooli vastaava = rooliList.get(1);
        assertEquals(vastaava.getNimi(), vastaavaNimi);
        Rooli sijainen = rooliList.get(2);
        assertEquals(sijainen.getNimi(), sijainenNimi);

        List<Henkilo> henkiloList = createTestHenkiloList(Arrays.asList("OMISTAJA", "VASTAAVA", "SIJAINEN1", "SIJAINEN2"));
        Henkilo omistajaHenkilo = henkiloList.get(0);
        assertEquals(omistajaHenkilo.getEtunimi().contains(omistajaNimi), true);
        Henkilo vastaavaHenkilo = henkiloList.get(1);
        assertEquals(vastaavaHenkilo.getEtunimi().contains(vastaavaNimi), true);
        Henkilo sijainen1Henkilo = henkiloList.get(2);
        assertEquals(sijainen1Henkilo.getEtunimi().contains(sijainenNimi), true);
        Henkilo sijainen2Henkilo = henkiloList.get(3);
        assertEquals(sijainen2Henkilo.getEtunimi().contains(sijainenNimi), true);

        rooliList.add(sijainen);
        List<JarjestelmaHenkiloRooli> henkiloRooliList = createTestJarjestelmaWithHenkiloRooliList(rooliList, henkiloList);

        assertEquals(rooliList.size(), henkiloList.size());
        assertEquals(henkiloList.size(), henkiloRooliList.size());

        Integer expectedJarjestelmaId = 1;

        for (int i = 0; i < rooliList.size(); i++) {
            Rooli rooli = rooliList.get(i);
            String rooliNimi = rooli.getNimi();
            Henkilo henkilo = henkiloList.get(i);
            assertEquals(henkilo.getEtunimi().contains(rooliNimi), true);
            assertEquals(henkilo.getSukunimi().contains(rooliNimi), true);
            JarjestelmaHenkiloRooli jarjHenkRooli = findJarjHenkRooli(henkiloRooliList, henkilo.getTunnus());
            assertEquals(jarjHenkRooli.getRooliId(), rooli.getTunnus());
            assertEquals(jarjHenkRooli.getHenkiloId(), henkilo.getTunnus());
            assertEquals(jarjHenkRooli.getSysteemiId(), expectedJarjestelmaId);
        }

        List<Henkilo> fetchedOmistajaList = henkiloService.getHenkiloByRooliAndSystemId(omistaja.getTunnus(),
                expectedJarjestelmaId, false, JarjestelmaHenkiloRooli.class, "JARJESTELMA_ID");
        assertEquals(fetchedOmistajaList, Collections.singletonList(omistajaHenkilo));

        List<Henkilo> fetchedVastaavaList = henkiloService.getHenkiloByRooliAndSystemId(vastaava.getTunnus(),
                expectedJarjestelmaId, false, JarjestelmaHenkiloRooli.class, "JARJESTELMA_ID");
        assertEquals(fetchedVastaavaList, Collections.singletonList(vastaavaHenkilo));

        List<Henkilo> fetchedSijainenList = henkiloService.getHenkiloByRooliAndSystemId(sijainen.getTunnus(),
                expectedJarjestelmaId, false, JarjestelmaHenkiloRooli.class, "JARJESTELMA_ID");
        assertEquals(fetchedSijainenList, Arrays.asList(sijainen1Henkilo, sijainen2Henkilo));
    }

    @Test
    public void getRooliListByJarjestelmaTest() throws SQLException, IOException {
        List<String> rooliNimiList = Arrays.asList("OMISTAJA", "VASTAAVA");
        List<Rooli> rooliList = createTestRooliList(rooliNimiList);
        List<Henkilo> henkiloList = createTestHenkiloList(rooliNimiList);
        createTestJarjestelmaWithHenkiloRooliList(rooliList, henkiloList);
        Integer expectedJarjestelmaId = 1;
        List<Rooli> fetchedRooliList = new ArrayList<>(henkiloService.getRooliSetBySystemId(expectedJarjestelmaId,
                JarjestelmaHenkiloRooli.class, "JARJESTELMA_ID"));
        assertEquals(rooliList, fetchedRooliList);
    }

    @Test
    public void mergeHenkiloListToEmptyTableTest() throws SQLException {
        List<String> nimiList = Arrays.asList("HenkiloA", "HenkiloB", "HenkiloY");
        List<HenkiloTemp> henkiloList = henkiloConverter.convertHenkiloListToHenkiloTempList(
                initializeHenkiloSaveList(getHenkiloObjList(nimiList)));
        fimHenkiloService.mergeHenkiloList(henkiloList);

        List<Henkilo> fetchedHenkiloList = henkiloConverter.henkiloDtoListToModelList(henkiloService.getHenkiloListAll());

        List<Henkilo> expectedHenkiloList = henkiloConverter.convertHenkiloTempListToHenkiloList(henkiloList);
        setIDsForAsserting(expectedHenkiloList, fetchedHenkiloList);
        assertEquals(expectedHenkiloList.containsAll(fetchedHenkiloList), true);
        assertEquals(expectedHenkiloList.size(), fetchedHenkiloList.size());
    }

    @Test
    public void mergeHenkiloListTest() throws SQLException {
        List<String> nimiList = Arrays.asList("Henkilo_A_", "Henkilo_B_", "Henkilo_Y_", "Henkilo_C_");
        List<HenkiloTemp> henkiloList = henkiloConverter.convertHenkiloListToHenkiloTempList(
                initializeHenkiloSaveList(getHenkiloObjList(nimiList)));

        List<HenkiloTemp> initialHenkiloList = henkiloList.subList(0, 3);
        fimHenkiloService.mergeHenkiloList(initialHenkiloList);

        List<Henkilo> fetchedHenkiloList = henkiloConverter.henkiloDtoListToModelList(henkiloService.getHenkiloListAll());
        List<Henkilo> expectedHenkiloList = henkiloConverter.convertHenkiloTempListToHenkiloList(initialHenkiloList);
        setIDsForAsserting(expectedHenkiloList, fetchedHenkiloList);
        assertEquals(expectedHenkiloList.containsAll(fetchedHenkiloList), true);
        assertEquals(expectedHenkiloList.size(), fetchedHenkiloList.size());

        HenkiloTemp newHenkilo1 = henkiloList.get(1);
        newHenkilo1.setTunnus(null);
        HenkiloTemp newHenkilo2 = henkiloList.get(0);
        newHenkilo2.setTunnus(null);
        newHenkilo2.setEtunimi("New firstname");
        HenkiloTemp newHenkilo3 = henkiloList.get(3);
        newHenkilo3.setTunnus(null);
        List<HenkiloTemp> newHenkiloList = Arrays.asList(newHenkilo1, newHenkilo2, newHenkilo3);
        fimHenkiloService.mergeHenkiloList(newHenkiloList);

        fetchedHenkiloList = henkiloConverter.henkiloDtoListToModelList(henkiloService.getHenkiloListAll());
        HenkiloTemp expected1 = henkiloList.get(0);
        expected1.setEtunimi("New firstname");
        HenkiloTemp expected2 = henkiloList.get(1);
        HenkiloTemp expected3 = henkiloList.get(2);
        expected3.setPoistunut(1);
        HenkiloTemp expected4 = henkiloList.get(3);
        List<HenkiloTemp> expectedHenkiloTempList = Arrays.asList(expected1, expected2, expected3, expected4);
        expectedHenkiloList = henkiloConverter.convertHenkiloTempListToHenkiloList(expectedHenkiloTempList);
        expectedHenkiloList = setIDsForAsserting(expectedHenkiloList, fetchedHenkiloList);

        assertEquals(expectedHenkiloList.containsAll(fetchedHenkiloList), true);
        assertEquals(expectedHenkiloList.size(), fetchedHenkiloList.size());

    }

    @Test
    public void getHenkiloListTest() throws SQLException {
        String firstName = "Test";
        String lastName = "Person";
        List<Object[]> list = Collections.singletonList(new Object[]{firstName, lastName});
        createTestHenkiloListWithFullnames(list);

        getHenkiloAndAssert(firstName.toLowerCase(), firstName, lastName, 1, 0);
        getHenkiloAndAssert(lastName.toLowerCase(), firstName, lastName, 1, 0);
        getHenkiloAndAssert(firstName.substring(0, 2).toLowerCase(), firstName, lastName, 1, 0);
        getHenkiloAndAssert(lastName.substring(0, 2).toLowerCase(), firstName, lastName, 1, 0);
        getHenkiloAndAssert(format("%s %s", firstName, lastName), firstName, lastName, 1, 0);
        getHenkiloAndAssert(format("%s %s", lastName, firstName), firstName, lastName, 1, 0);
        getHenkiloAndAssert(format("%s %s", lastName.substring(0, 2), firstName.substring(0, 1)), firstName, lastName,
                1, 0);
        getHenkiloAndAssert(format("%s %s", firstName.substring(0, 2), "X"), firstName, lastName,
                0, -1);
    }

    private void getHenkiloAndAssert(String searchParam, String expectedFirstname, String expectedLastname,
                                  Integer expectedListSize, Integer expectedIdx) {

        List<HenkiloDto> fetchedHenkiloDtoList = henkiloService.getHenkiloList("", searchParam);
        assertEquals((Integer) fetchedHenkiloDtoList.size(), expectedListSize);
        if (expectedIdx != -1) {
            assertEquals(fetchedHenkiloDtoList.get(expectedIdx).getNayttonimi(),
                    format("%s %s", expectedLastname, expectedFirstname));
        }
    }

    @Test
    public void getHenkiloListWithoutRemovedPersons() throws SQLException {
        String firstName = "Test";
        String lastName = "Person";

        List<Object[]> fullnameList = Arrays.asList(new Object[]{firstName, lastName}, new Object[]{firstName, lastName});
        List<Object[]> henkiloObjList = getHenkiloObjListByFullname(fullnameList);
        List<Henkilo> henkiloSaveList = initializeHenkiloSaveList(henkiloObjList);

        Henkilo testPerson = henkiloSaveList.get(0);
        Henkilo removedPerson = henkiloSaveList.get(1);
        removedPerson.setPoistunut(1);

        List<Henkilo> henkiloList = henkiloService.saveHenkiloList(Arrays.asList(testPerson, removedPerson));
        assertCreatedPersons(henkiloObjList, henkiloList);
        List<HenkiloDto> fetchedHenkiloDtoList = henkiloService.getHenkiloList("",
                firstName.toLowerCase() + " " +  lastName.toLowerCase());

        assertEquals(fetchedHenkiloDtoList, Collections.singletonList(henkiloConverter.henkiloModelToDto(testPerson)));
    }

    private List<Henkilo> setIDsForAsserting(List<Henkilo> expected, List<Henkilo> fetched) {
        for (Henkilo expectedHenkilo : expected) {
            for (Henkilo fetchedHenkilo : fetched) {
                if (expectedHenkilo.getObjectID().equals(fetchedHenkilo.getObjectID())) {
                    expectedHenkilo.setTunnus(fetchedHenkilo.getTunnus());
                }
            }
        }
        return expected;
    }

    public static List<Rooli> createTestRooliList(List<String> rooliNimiList) throws SQLException {
        List<Rooli> rooliList = new ArrayList<>();

        for (String rooliNimi : rooliNimiList) {
            Rooli rooli = saveTestRooli(rooliNimi);
            assertEquals(rooli.getNimi(), rooliNimi);
            assertEquals(rooli.getTunnus() != null, true);
            rooliList.add(rooli);
        }
        return rooliList;
    }

    static JarjestelmaHenkiloRooli findJarjHenkRooli(List<JarjestelmaHenkiloRooli> jarjHenkRooliList, Integer henkiloId) {
        for (JarjestelmaHenkiloRooli jarjHenkRooli : jarjHenkRooliList) {
            if (jarjHenkRooli.getHenkiloId().equals(henkiloId)) {
                return jarjHenkRooli;
            }
        }
        assertEquals(true, false);
        return null;
    }

    static List<Object[]> getHenkiloObjList(List<String> nimiList) {
        return getHenkiloObjListByFullname(getFullNameObjByNamelist(nimiList));
    }

    static List<Object[]> getFullNameObjByNamelist(List<String> nimiList) {
        List<Object[]> fullnameList = new ArrayList<Object[]>();
        for (String nimi : nimiList) {
            String uuid = UUID.randomUUID().toString().substring(0, 7);
            Object[] fullname = new Object[]{format("Etunimi%s%s", nimi, uuid),
                    format("Sukunimi%s%s", nimi, uuid), uuid};
            fullnameList.add(fullname);
        }
        return fullnameList;
    }

    public static List<Object[]> getHenkiloObjListByFullname(List<Object[]> nimiList) {
        List<Object[]> henkiloObjList = new ArrayList<Object[]>();
        for (Object[] nimi : nimiList) {
            String etunimi = (String) nimi[0];
            String sukunimi = (String) nimi[1];
            String uuid = UUID.randomUUID().toString().substring(0, 7);
            Object[] henkilo = new Object[]{etunimi, sukunimi, uuid};
            henkiloObjList.add(henkilo);
        }
        return henkiloObjList;
    }

    public static List<Henkilo> createTestHenkiloList(List<String> nimiList) throws SQLException {
        return createTestHenkiloListWithFullnames(getFullNameObjByNamelist(nimiList));
    }

    static List<Henkilo> createTestHenkiloListWithFullnames(List<Object[]> fullnameList) throws SQLException {
        List<Object[]> henkiloObjList = getHenkiloObjListByFullname(fullnameList);

        List<Henkilo> henkiloSaveList = initializeHenkiloSaveList(henkiloObjList);
        setUpHenkiloService();
        List<Henkilo> henkiloList = henkiloService.saveHenkiloList(henkiloSaveList);
        assertCreatedPersons(henkiloObjList, henkiloList);
        return henkiloList;
    }

    private static void assertCreatedPersons(List<Object[]> henkiloObjList, List<Henkilo> henkiloList) {
        for (int i = 0; i < henkiloList.size(); i++) {
            Henkilo henkilo = henkiloList.get(i);
            Object[] henkiloObj = henkiloObjList.get(i);
            assertEquals(henkilo.getTunnus() != null, true);
            assertEquals(henkilo.getEtunimi(), henkiloObj[0]);
            assertEquals(henkilo.getSukunimi(), henkiloObj[1]);
        }
    }


    public static List<Henkilo> initializeHenkiloSaveList(Collection<Object[]> henkiloList) {
        List<Object[]> newHenkiloList = new ArrayList<>();
        for (Object[] henkiloObj : henkiloList) {
            String etunimi = (String) henkiloObj[0];
            String sukunimi = (String) henkiloObj[1];
            String uuid = (String) henkiloObj[2];
            String objectId = format("%s-29d0-1bb2-1ab2-12347f0965e5", uuid);
            Object[] henkilo = new Object[]{etunimi, sukunimi, objectId};
            newHenkiloList.add(henkilo);
        }
        return initializeHenkiloSaveListWithObjectIDs(newHenkiloList);
    }

    private static List<Henkilo> initializeHenkiloSaveListWithObjectIDs(Collection<Object[]> henkiloList) {
        List<Henkilo> henkiloSaveList = new ArrayList<Henkilo>();
        int i = 1;
        for (Object[] henkiloObj : henkiloList) {
            String etunimi = (String) henkiloObj[0];
            String sukunimi = (String) henkiloObj[1];
            String objectId = (String) henkiloObj[2];

            String kayttajatunnus = (i >= 10) ? format("K1234%d", i) : format("K12349%d", i);

            Henkilo henkilo = new Henkilo(objectId, format("%s %s", sukunimi, etunimi),
                    "K2-tunnus", 0, kayttajatunnus, "Testiyritys",
                    "1234567-8", etunimi, sukunimi, format("%s.%s@example.com", etunimi, sukunimi),
                    "+358401234567");

            henkiloSaveList.add(henkilo);
            i++;
        }
        return henkiloSaveList;
    }

    private static Object[] initializeSovellusHenkilo(String rooliNimi, String objectId) {
        String etunimi = format("Etunimi%s", rooliNimi);
        String sukunimi = format("Sukunimi%s", rooliNimi);
        return new Object[]{etunimi, sukunimi, objectId};
    }

    public static void createSovellusHenkiloList(String tuotantoonHyvaksyjaObjectId, String asennuksenHyvaksyjaObjectId) throws SQLException {
        Object[] tuotantoonObj = initializeSovellusHenkilo("TuotantoonHyvaksyja", tuotantoonHyvaksyjaObjectId);
        Object[] asennusObj = initializeSovellusHenkilo("AsennuksenHyvaksyja", asennuksenHyvaksyjaObjectId);

        List<Henkilo> sovellusHenkiloList = initializeHenkiloSaveListWithObjectIDs(Arrays.asList(tuotantoonObj, asennusObj));
        if (henkiloService == null) henkiloService = new HenkiloService();
        henkiloService.saveHenkiloList(sovellusHenkiloList);
    }


    public static List<JarjestelmaHenkiloRooli> createTestJarjestelmaWithHenkiloRooliList(
            List<Rooli> rooliList, List<Henkilo> henkiloList) throws SQLException, IOException {
        setUpHenkiloService();

        ContentDto testJarjestelma = createTestJarjestelma("TestJärjestelmä");
        Integer jarjestelmaId = testJarjestelma.getTunnus();
        assertEquals(jarjestelmaId, Integer.valueOf(1));

        List<JarjestelmaHenkiloRooli> henkiloRooliList = (List<JarjestelmaHenkiloRooli>) (Object)
                createTestHenkiloRooliList(rooliList, henkiloList, jarjestelmaId, JarjestelmaHenkiloRooli.class);

        return henkiloRooliList;
    }

    public static List<JoinHenkiloRooliTable> createTestHenkiloRooliList(
            List<Rooli> rooliList, List<Henkilo> henkiloList, Integer systemId,
            Class<? extends JoinHenkiloRooliTable> joinClass) throws SQLException {

        List<JoinHenkiloRooliTable> henkiloRooliList = new ArrayList<>();

        for (int i = 0; i < henkiloList.size(); i++) {
            Henkilo henkilo = henkiloList.get(i);
            Integer henkiloId = henkilo.getTunnus();
            Integer rooliId = rooliList.get(i).getTunnus();

            JoinHenkiloRooliTable joinTable;
            JoinHenkiloRooliTableHistory history;
            if (joinClass.equals(JarjestelmaHenkiloRooli.class)) {
                joinTable = new JarjestelmaHenkiloRooli(henkiloId, rooliId, systemId);
                history = new JarjestelmaHenkiloRooliHistory();
            } else {
                joinTable = new SovellusHenkiloRooli(henkiloId, rooliId, systemId);
                history = new SovellusHenkiloRooliHistory();
            }


            henkiloRooliList.add(henkiloService.saveHenkiloRooli(joinTable, history));
        }

        return henkiloRooliList;
    }


    public static Rooli saveTestRooli(String roleName) throws SQLException {
        setUpHenkiloService();
        return henkiloService.saveRooli(new Rooli(roleName));
    }

    private static void setUpHenkiloService() {
        if (henkiloService == null) henkiloService = new HenkiloService();
    }

}
