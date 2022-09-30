package fi.liike.service;

import com.google.common.collect.Lists;
import fi.liike.rest.MainRestTester;
import fi.liike.rest.Model.*;
import fi.liike.rest.Service.JarjestelmaService;
import fi.liike.rest.api.Catalogue;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.Converter.HenkiloConverter;
import fi.liike.rest.api.dto.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static fi.liike.service.HenkiloServiceTest.*;
import static org.junit.Assert.assertEquals;

public class JarjestelmaSalkkuServiceTest {
    private MainRestTester mainTester;
    private static JarjestelmaService jarjestelmaService;
    private HenkiloConverter henkiloConverter;

    @Before
    public void setUp() throws SQLException {
        jarjestelmaService = new JarjestelmaService();
        mainTester = new MainRestTester(Catalogue.HENKILO);
        henkiloConverter = new HenkiloConverter();
    }

    @After
    public void tearDown() {
        mainTester.clear();
    }

    @Test
    public void getJarjestelmaDtoWithHenkiloListTest() throws SQLException, IOException {
        List<String> rooliNimiList = Arrays.asList("OMISTAJA", "VASTAAVA");
        List<Rooli> rooliList = createTestRooliList(rooliNimiList);
        List<Henkilo> henkiloList = createTestHenkiloList(rooliNimiList);
        List<JarjestelmaHenkiloRooli> henkiloRooliList = createTestJarjestelmaWithHenkiloRooliList(rooliList, henkiloList);
        Integer expectedJarjestelmaId = 1;
        JarjestelmaDto jarjestelmaDto = (JarjestelmaDto) jarjestelmaService.get(expectedJarjestelmaId);

        assertEquals(jarjestelmaDto.getTunnus(), expectedJarjestelmaId);
        List<FetchHenkiloRooliDto> expectedHenkiloRooliMap = new ArrayList<>();

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

            expectedHenkiloRooliMap.add(new FetchHenkiloRooliDto(
                    henkiloConverter.rooliModelToDto(rooli), henkiloConverter.henkiloModelToDto(henkilo)));
        }

        assertEquals(expectedHenkiloRooliMap.containsAll(jarjestelmaDto.getFetchRooliHenkiloList()), true);
        assertEquals(expectedHenkiloRooliMap.size(), jarjestelmaDto.getFetchRooliHenkiloList().size());
    }

    @Test
    public void createJarjestelmaWithHenkiloRooliListTest() throws SQLException, IOException {
        List<String> rooliNimiList = Arrays.asList("OMISTAJA", "VASTAAVA");
        List<Rooli> rooliList = createTestRooliList(rooliNimiList);
        List<Henkilo> henkiloList = createTestHenkiloList(rooliNimiList);
        assertEquals(rooliList.size(), henkiloList.size());

        JarjestelmaDto jarjestelma = new JarjestelmaDto();
        jarjestelma.setNimi("TestiJärjestelmä");
        List<FetchHenkiloRooliDto> expectedRooliHenkiloListMap = initializeExpectedRooliHenkiloListMap(rooliList, henkiloList);
        setUpHenkiloRooliList(expectedRooliHenkiloListMap, jarjestelma);
        JarjestelmaDto createdJarjestelma = (JarjestelmaDto) jarjestelmaService.save(jarjestelma);

        List<FetchHenkiloRooliDto> henkiloRooliMap = createdJarjestelma.getFetchRooliHenkiloList();

        assertEquals(expectedRooliHenkiloListMap.containsAll(henkiloRooliMap), true);
        assertEquals(expectedRooliHenkiloListMap.size(), henkiloRooliMap.size());
    }

    @Test
    public void modifyJarjestelmaHenkiloRoolisTest() throws SQLException, IOException {
        List<String> rooliNimiList = Arrays.asList("OMISTAJA", "VASTAAVA", "SIJAINEN");
        List<Rooli> rooliList = createTestRooliList(rooliNimiList);

        List<Henkilo> henkiloList = createTestHenkiloList(Arrays.asList("Henk1", "Henk2", "Henk3", "Henk4"));

        JarjestelmaDto jarjestelma = new JarjestelmaDto();
        jarjestelma.setNimi("TestiJärjestelmä");
        List<Rooli> currRooliList = Arrays.asList(rooliList.get(0), rooliList.get(1));
        List<Henkilo> currHenkiloList = Arrays.asList(henkiloList.get(0), henkiloList.get(1));
        List<FetchHenkiloRooliDto> expectedRooliHenkiloListMap = initializeExpectedRooliHenkiloListMap(currRooliList, currHenkiloList);
        setUpHenkiloRooliList(expectedRooliHenkiloListMap, jarjestelma);

        JarjestelmaDto createdJarjestelma = (JarjestelmaDto) jarjestelmaService.save(jarjestelma);
        assertEquals(expectedRooliHenkiloListMap.containsAll(createdJarjestelma.getFetchRooliHenkiloList()), true);
        assertEquals(expectedRooliHenkiloListMap.size(), createdJarjestelma.getFetchRooliHenkiloList().size());


        currRooliList = Arrays.asList(rooliList.get(0), rooliList.get(1), rooliList.get(2));
        currHenkiloList = Arrays.asList(henkiloList.get(0), henkiloList.get(2), henkiloList.get(3));
        expectedRooliHenkiloListMap = initializeExpectedRooliHenkiloListMap(currRooliList, currHenkiloList);
        setUpHenkiloRooliList(expectedRooliHenkiloListMap, createdJarjestelma);
        createdJarjestelma = (JarjestelmaDto) jarjestelmaService.update(createdJarjestelma);

        assertEquals(expectedRooliHenkiloListMap.containsAll(createdJarjestelma.getFetchRooliHenkiloList()), true);
        Set<JoinHenkiloRooliTable> fetchedHenkiloRooliList = jarjestelmaService.getHenkiloRooliListByJarjestelmaId(createdJarjestelma.getTunnus());
        assertHenkiloRooliList(expectedRooliHenkiloListMap, fetchedHenkiloRooliList);
    }

    @Test
    public void modifyJarjestelmaHenkiloRoolis2Test() throws SQLException, IOException {
        List<String> rooliNimiList = Arrays.asList("OMISTAJA", "VASTAAVA");
        List<Rooli> rooliList = createTestRooliList(rooliNimiList);

        List<Henkilo> henkiloList = createTestHenkiloList(Arrays.asList("Henk1", "Henk2", "Henk3"));

        JarjestelmaDto jarjestelma = new JarjestelmaDto();
        jarjestelma.setNimi("TestiJärjestelmä");
        List<Rooli> currRooliList = Arrays.asList(rooliList.get(0), rooliList.get(1));
        List<Henkilo> currHenkiloList = Arrays.asList(henkiloList.get(0), henkiloList.get(1));
        List<FetchHenkiloRooliDto> expectedRooliHenkiloListMap = initializeExpectedRooliHenkiloListMap(currRooliList, currHenkiloList);
        setUpHenkiloRooliList(expectedRooliHenkiloListMap, jarjestelma);

        JarjestelmaDto createdJarjestelma = (JarjestelmaDto) jarjestelmaService.save(jarjestelma);
        assertEquals(expectedRooliHenkiloListMap.containsAll(createdJarjestelma.getFetchRooliHenkiloList()), true);
        assertEquals(expectedRooliHenkiloListMap.size(), createdJarjestelma.getFetchRooliHenkiloList().size());

        currRooliList = Arrays.asList(rooliList.get(0), rooliList.get(1));
        currHenkiloList = Arrays.asList(henkiloList.get(2), henkiloList.get(0));
        expectedRooliHenkiloListMap = initializeExpectedRooliHenkiloListMap(currRooliList, currHenkiloList);
        setUpHenkiloRooliList(expectedRooliHenkiloListMap, createdJarjestelma);
        createdJarjestelma = (JarjestelmaDto) jarjestelmaService.update(createdJarjestelma);
        assertEquals(expectedRooliHenkiloListMap.containsAll(createdJarjestelma.getFetchRooliHenkiloList()), true);
        Set<JoinHenkiloRooliTable> fetchedHenkiloRooliList = jarjestelmaService.getHenkiloRooliListByJarjestelmaId(createdJarjestelma.getTunnus());
        assertHenkiloRooliList(expectedRooliHenkiloListMap, fetchedHenkiloRooliList);
    }

    @Test
    public void modifyJarjestelmaHenkiloRoolis3Test() throws SQLException, IOException {
        List<String> rooliNimiList = Arrays.asList("OMISTAJA", "VASTAAVA");
        List<Rooli> rooliList = createTestRooliList(rooliNimiList);

        List<Henkilo> henkiloList = createTestHenkiloList(rooliNimiList);

        JarjestelmaDto jarjestelma = new JarjestelmaDto();
        jarjestelma.setNimi("TestiJärjestelmä");
        List<FetchHenkiloRooliDto> expectedRooliHenkiloListMap = initializeExpectedRooliHenkiloListMap(rooliList, henkiloList);
        setUpHenkiloRooliList(expectedRooliHenkiloListMap, jarjestelma);

        JarjestelmaDto createdJarjestelma = (JarjestelmaDto) jarjestelmaService.save(jarjestelma);
        assertEquals(expectedRooliHenkiloListMap.containsAll(createdJarjestelma.getFetchRooliHenkiloList()), true);
        assertEquals(expectedRooliHenkiloListMap.size(), createdJarjestelma.getFetchRooliHenkiloList().size());

        //Remove vastaava
        List<Rooli> currRooliList = Arrays.asList(rooliList.get(0));
        List<Henkilo> currHenkiloList = Arrays.asList(henkiloList.get(0));
        expectedRooliHenkiloListMap = initializeExpectedRooliHenkiloListMap(currRooliList, currHenkiloList);
        setUpHenkiloRooliList(expectedRooliHenkiloListMap, createdJarjestelma);
        createdJarjestelma = (JarjestelmaDto) jarjestelmaService.update(createdJarjestelma);
        assertEquals(expectedRooliHenkiloListMap, createdJarjestelma.getFetchRooliHenkiloList());
        Set<JoinHenkiloRooliTable> fetchedHenkiloRooliList = jarjestelmaService.getHenkiloRooliListByJarjestelmaId(createdJarjestelma.getTunnus());
        assertHenkiloRooliList(expectedRooliHenkiloListMap, fetchedHenkiloRooliList);
    }

    @Test
    public void modifyJarjestelmaHenkiloRoolis4Test() throws SQLException, IOException {
        List<String> rooliNimiList = Arrays.asList("OMISTAJA", "VASTAAVA");
        List<Rooli> rooliList = createTestRooliList(rooliNimiList);

        List<Henkilo> henkiloList = createTestHenkiloList(Arrays.asList("Henk1", "Henk2", "Henk3"));
        List<Rooli> currRooliList = Arrays.asList(rooliList.get(0), rooliList.get(1));
        List<Henkilo> currHenkiloList = Arrays.asList(henkiloList.get(0), henkiloList.get(1));

        JarjestelmaDto jarjestelma = new JarjestelmaDto();
        jarjestelma.setNimi("TestiJärjestelmä");
        List<FetchHenkiloRooliDto> expectedRooliHenkiloListMap = initializeExpectedRooliHenkiloListMap(currRooliList, currHenkiloList);
        setUpHenkiloRooliList(expectedRooliHenkiloListMap, jarjestelma);

        JarjestelmaDto createdJarjestelma = (JarjestelmaDto) jarjestelmaService.save(jarjestelma);
        assertEquals(expectedRooliHenkiloListMap.containsAll(createdJarjestelma.getFetchRooliHenkiloList()), true);
        assertEquals(expectedRooliHenkiloListMap.size(), createdJarjestelma.getFetchRooliHenkiloList().size());

        //Change vastaava
        currHenkiloList = Arrays.asList(henkiloList.get(0), henkiloList.get(2));
        expectedRooliHenkiloListMap = initializeExpectedRooliHenkiloListMap(currRooliList, currHenkiloList);
        setUpHenkiloRooliList(expectedRooliHenkiloListMap, createdJarjestelma);
        createdJarjestelma = (JarjestelmaDto) jarjestelmaService.update(createdJarjestelma);
        assertEquals(expectedRooliHenkiloListMap.containsAll(createdJarjestelma.getFetchRooliHenkiloList()), true);
        Set<JoinHenkiloRooliTable> fetchedHenkiloRooliList = jarjestelmaService.getHenkiloRooliListByJarjestelmaId(createdJarjestelma.getTunnus());
        assertHenkiloRooliList(expectedRooliHenkiloListMap, fetchedHenkiloRooliList);
    }

    @Test
    public void modifyJarjestelmaHenkiloRoolis5Test() throws SQLException, IOException {
        List<String> rooliNimiList = Arrays.asList("SIJAINEN");
        List<Rooli> rooliList = createTestRooliList(rooliNimiList);

        List<Henkilo> henkiloList = createTestHenkiloList(Arrays.asList("Henk1", "Henk2"));
        List<Rooli> currRooliList = rooliList;
        List<Henkilo> currHenkiloList = Arrays.asList(henkiloList.get(0));

        JarjestelmaDto jarjestelma = new JarjestelmaDto();
        jarjestelma.setNimi("TestiJärjestelmä");
        List<FetchHenkiloRooliDto> expectedRooliHenkiloListMap = initializeExpectedRooliHenkiloListMap(currRooliList, currHenkiloList);
        setUpHenkiloRooliList(expectedRooliHenkiloListMap, jarjestelma);

        JarjestelmaDto createdJarjestelma = (JarjestelmaDto) jarjestelmaService.save(jarjestelma);
        assertEquals(expectedRooliHenkiloListMap, createdJarjestelma.getFetchRooliHenkiloList());

        //Add another sijainen
        currHenkiloList = Arrays.asList(henkiloList.get(0), henkiloList.get(1));
        expectedRooliHenkiloListMap = initializeExpectedRooliHenkiloListMap(currRooliList, currHenkiloList);
        setUpHenkiloRooliList(expectedRooliHenkiloListMap, createdJarjestelma);
        createdJarjestelma = (JarjestelmaDto) jarjestelmaService.update(createdJarjestelma);
        assertEquals(expectedRooliHenkiloListMap.containsAll(createdJarjestelma.getFetchRooliHenkiloList()), true);
        Set<JoinHenkiloRooliTable> fetchedHenkiloRooliList = jarjestelmaService.getHenkiloRooliListByJarjestelmaId(createdJarjestelma.getTunnus());
        assertHenkiloRooliList(expectedRooliHenkiloListMap, fetchedHenkiloRooliList);
    }

    @Test(expected = IOException.class)
    public void samePersonAsVastaavaAndSijainenFail1Test() throws SQLException, IOException {
        List<String> rooliNimiList = Arrays.asList("OMISTAJA", "VASTAAVA", "SIJAINEN");
        List<Rooli> rooliList = createTestRooliList(rooliNimiList);
        List<Henkilo> henkiloList = createTestHenkiloList(Arrays.asList("Henk"));
        List<Rooli> currRooliList = rooliList.subList(1, 3);
        List<Henkilo> currHenkiloList = Arrays.asList(henkiloList.get(0), henkiloList.get(0));
        JarjestelmaDto jarjestelma = getTestJarjestelma();
        List<FetchHenkiloRooliDto> expectedRooliHenkiloListMap = initializeExpectedRooliHenkiloListMap(currRooliList,
                currHenkiloList);
        setUpHenkiloRooliList(expectedRooliHenkiloListMap, jarjestelma);

        jarjestelmaService.save(jarjestelma);
    }

    @Test(expected = IOException.class)
    public void linkListValidationSelfReferentialLinkTest() throws IOException {
        JarjestelmaLinkkausDto l = new JarjestelmaLinkkausDto();
        l.setId(1); l.setTietojarjestelmaTunnus(0); l.setLinkattavaTunnus(0); l.setSuunta("Luku");
        l.setTietovirta("1"); l.setTyyppi("Järjestelmä");
        List<JarjestelmaLinkkausDto> list = Lists.newArrayList(l);
        jarjestelmaService.checkLinkListValidity(list);
    }

    @Test(expected = IOException.class)
    public void linkListValidationDuplicateLinkWithNewIdTest() throws IOException {
        JarjestelmaLinkkausDto l1 = new JarjestelmaLinkkausDto();
        l1.setId(1); l1.setTietojarjestelmaTunnus(0); l1.setLinkattavaTunnus(1);
        JarjestelmaLinkkausDto l2 = new JarjestelmaLinkkausDto();
        l1.setId(2); l2.setTietojarjestelmaTunnus(0); l2.setLinkattavaTunnus(1);
        List<JarjestelmaLinkkausDto> list = Lists.newArrayList(l1, l2);
        jarjestelmaService.checkLinkListValidity(list);
    }

    @Test(expected = IOException.class)
    public void linkListValidationDuplicateButReversedLinkTest() throws IOException {
        JarjestelmaLinkkausDto l1 = new JarjestelmaLinkkausDto();
        l1.setId(1); l1.setTietojarjestelmaTunnus(0); l1.setLinkattavaTunnus(1); l1.setSuunta("Luku");
        JarjestelmaLinkkausDto l2 = new JarjestelmaLinkkausDto();
        l1.setId(2); l2.setTietojarjestelmaTunnus(1); l2.setLinkattavaTunnus(0); l2.setSuunta("Kirjoitus");
        List<JarjestelmaLinkkausDto> list = Lists.newArrayList(l1, l2);
        jarjestelmaService.checkLinkListValidity(list);
    }

    @Test(expected = IOException.class)
    public void linkListValidationMissingDestinationTest() throws IOException {
        JarjestelmaLinkkausDto l1 = new JarjestelmaLinkkausDto();
        l1.setId(1); l1.setTietojarjestelmaTunnus(0); l1.setSuunta("Luku");
        JarjestelmaLinkkausDto l2 = new JarjestelmaLinkkausDto();
        l1.setId(2); l2.setSuunta("Kirjoitus");
        List<JarjestelmaLinkkausDto> list = Lists.newArrayList(l1, l2);
        jarjestelmaService.checkLinkListValidity(list);
    }

    @Test(expected = Test.None.class /* no exception */)
    public void linkListValidationSameLinkWithDifferentDataStreamTest() throws IOException {
        JarjestelmaLinkkausDto l1 = new JarjestelmaLinkkausDto();
        l1.setId(1); l1.setTietojarjestelmaTunnus(0); l1.setLinkattavaTunnus(1); l1.setSuunta("Luku");
        l1.setTietovirta("1");
        JarjestelmaLinkkausDto l2 = new JarjestelmaLinkkausDto();
        l1.setId(2); l2.setTietojarjestelmaTunnus(0); l2.setLinkattavaTunnus(1); l2.setSuunta("Luku");
        l2.setTietovirta("2");
        List<JarjestelmaLinkkausDto> list = Lists.newArrayList(l1, l2);
        jarjestelmaService.checkLinkListValidity(list);
    }

    @Test(expected = Test.None.class /* no exception */)
    public void linkListValidationOpposingDirectionsTest() throws IOException {
        JarjestelmaLinkkausDto l1 = new JarjestelmaLinkkausDto();
        l1.setId(1); l1.setTietojarjestelmaTunnus(0); l1.setLinkattavaTunnus(1); l1.setSuunta("Luku");
        l1.setTietovirta("1");
        JarjestelmaLinkkausDto l2 = new JarjestelmaLinkkausDto();
        l1.setId(2); l2.setTietojarjestelmaTunnus(0); l2.setLinkattavaTunnus(1); l2.setSuunta("Kirjoitus");
        l2.setTietovirta("1");
        List<JarjestelmaLinkkausDto> list = Lists.newArrayList(l1, l2);
        jarjestelmaService.checkLinkListValidity(list);
    }

    /**
     * When creating a link while creating a new jarjestelma, the tietojarjestelmatunnus will
     * be null. Thus we allow links with null tietojarjestelmatunnus values.
     */
    @Test(expected = Test.None.class /* no exception */)
    public void linkListValidationLinkFromNewJarjestelmaTest() throws IOException {
        JarjestelmaLinkkausDto l1 = new JarjestelmaLinkkausDto();
        l1.setId(1); l1.setLinkattavaTunnus(1); l1.setSuunta("Luku");
        l1.setTietovirta("1");
        JarjestelmaLinkkausDto l2 = new JarjestelmaLinkkausDto();
        l1.setId(2); l2.setLinkattavaTunnus(2); l2.setSuunta("Kirjoitus");
        l2.setTietovirta("1");
        List<JarjestelmaLinkkausDto> list = Lists.newArrayList(l1, l2);
        jarjestelmaService.checkLinkListValidity(list);
    }

    /**
     * When creating new links, the IDs will be null. Thus, several links with the same (null) id are allowed.
     */
    @Test(expected = Test.None.class /* no exception */)
    public void linkListValidationNewLinksTest() throws IOException {
        JarjestelmaLinkkausDto l1 = new JarjestelmaLinkkausDto();
        l1.setTietojarjestelmaTunnus(0); l1.setLinkattavaTunnus(1); l1.setSuunta("Luku");
        l1.setTietovirta("1");
        JarjestelmaLinkkausDto l2 = new JarjestelmaLinkkausDto();
        l2.setTietojarjestelmaTunnus(0); l2.setLinkattavaTunnus(2); l2.setSuunta("Kirjoitus");
        l2.setTietovirta("1");
        List<JarjestelmaLinkkausDto> list = Lists.newArrayList(l1, l2);
        jarjestelmaService.checkLinkListValidity(list);
    }

    /**
     * New link with null id should still fail if it is otherwise a duplicate of another link.
     */
    @Test(expected = IOException.class)
    public void linkListValidationNewDuplicateLinkTest() throws IOException {
        JarjestelmaLinkkausDto l1 = new JarjestelmaLinkkausDto();
        l1.setId(0); l1.setTietojarjestelmaTunnus(0); l1.setLinkattavaTunnus(1); l1.setSuunta("Luku");
        JarjestelmaLinkkausDto l2 = new JarjestelmaLinkkausDto();
        l2.setTietojarjestelmaTunnus(1); l2.setLinkattavaTunnus(0); l2.setSuunta("Kirjoitus");
        List<JarjestelmaLinkkausDto> list = Lists.newArrayList(l1, l2);
        jarjestelmaService.checkLinkListValidity(list);
    }

    private JarjestelmaDto getTestJarjestelma() {
        JarjestelmaDto jarjestelma = new JarjestelmaDto();
        jarjestelma.setNimi("TestiJärjestelmä");
        return jarjestelma;
    }

    private void assertHenkiloRooliList(List<FetchHenkiloRooliDto> expectedRooliHenkiloList, Set<JoinHenkiloRooliTable>  fetchedHenkiloRooliList) {
        assertEquals(expectedRooliHenkiloList.size(), fetchedHenkiloRooliList.size());
        for (FetchHenkiloRooliDto henkiloRooli : expectedRooliHenkiloList) {
            Boolean contains = false;
            for (JoinHenkiloRooliTable fetchedHenkRooli : fetchedHenkiloRooliList) {
                if (fetchedHenkRooli.getHenkiloId().equals(henkiloRooli.getHenkilo().getTunnus()) &&
                        fetchedHenkRooli.getRooliId().equals(henkiloRooli.getRooli().getTunnus())) {
                    contains = true;
                }
            }
            assertEquals(contains, true);
        }
    }

    private void setUpHenkiloRooliList(List<FetchHenkiloRooliDto> expectedRooliHenkiloListMap, JarjestelmaDto jarjestelma) {
        List<HenkiloRooliDto> henkiloRooliList = new ArrayList<HenkiloRooliDto>();

        for (FetchHenkiloRooliDto henkiloRooli : expectedRooliHenkiloListMap) {
            henkiloRooliList.add(new HenkiloRooliDto(henkiloRooli.getRooli().getTunnus(), henkiloRooli.getHenkilo().getTunnus()));
        }

        jarjestelma.setHenkiloRooliList(henkiloRooliList);
    }

    private List<FetchHenkiloRooliDto> initializeExpectedRooliHenkiloListMap(List<Rooli> rooliList, List<Henkilo> henkiloList) {
        List<FetchHenkiloRooliDto> expectedList = new ArrayList<FetchHenkiloRooliDto>();
        for (int i = 0; i < rooliList.size(); i++) {
            Rooli rooli = rooliList.get(i);
            Henkilo henkilo = henkiloList.get(i);
            expectedList.add(new FetchHenkiloRooliDto(
                    henkiloConverter.rooliModelToDto(rooli), henkiloConverter.henkiloModelToDto(henkilo)));
        }
        return expectedList;
    }

    static ContentDto createTestJarjestelma(String name) throws SQLException, IOException {
        setUpJarjestelmaService();
        JarjestelmaDto jarjestelma = new JarjestelmaDto();
        jarjestelma.setNimi(name);
        return jarjestelmaService.save(jarjestelma);
    }

    private static void setUpJarjestelmaService() {
        if (jarjestelmaService == null) jarjestelmaService = new JarjestelmaService();
    }
}
