package fi.liike.rest;

import static fi.liike.service.HenkiloServiceTest.createTestHenkiloList;
import static fi.liike.service.HenkiloServiceTest.createTestJarjestelmaWithHenkiloRooliList;
import static fi.liike.service.HenkiloServiceTest.createTestRooliList;
import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response;

import fi.liike.rest.Model.*;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.JarjestelmaLinkkausDto;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fi.liike.rest.Controller.JarjestelmaController;
import fi.liike.rest.Service.JarjestelmaService;
import fi.liike.rest.api.Catalogue;
import fi.liike.rest.api.ModelResults;
import fi.liike.rest.api.dto.JarjestelmaDto;
import fi.liike.testutils.TestDbUtil;
import fi.liike.testutils.TestRequest;
import fi.liike.testutils.TestUtil;

//@RunWith(MockitoJUnitRunner.class)
public class JarjestelmasalkkuTest {

//    @Mock
//    JarjestelmaService jarjestelmaServiceMock;
//
//    @InjectMocks
//    JarjestelmaController restMock = new JarjestelmaController();

    private static JarjestelmaController rest;

    private MainRestTester mainTester;

    private static Gson gson;

    @Before
    public void setUp() throws Exception {
        rest = new JarjestelmaController();
        mainTester = new MainRestTester(Catalogue.JARJESTELMA);
        gson = new Gson();
    }

    @After
    public void tearDown() throws Exception {
        mainTester.clear();
    }

    // Common tests begin here.
    @Test
    public void testJarjestelmaCreateNew() throws JSONException, IOException {
        mainTester.testCreateNew(Catalogue.JARJESTELMA);
    }

    @Test
    public void testJarjestelmaCreateNewWithLinks() throws IOException, JSONException {
        JarjestelmaLinkkausDto jarjestelmaLinkkausDto = createJarjLinkEntry();
        JarjestelmaDto jarjestelmaWithLinks = createEntry("Järj1");
        List<JarjestelmaLinkkausDto> jarjestelmaLinkkausList = Collections.singletonList(jarjestelmaLinkkausDto);
        jarjestelmaWithLinks.setJarjestelmaLinkkausList(jarjestelmaLinkkausList);
        assertEquals(jarjestelmaWithLinks.getJarjestelmaLinkkausList().size(), 1);
        mainTester.testCreateNew(Catalogue.JARJESTELMA, jarjestelmaWithLinks, false);
    }


    @Test
    public void testJarjestelmaUpdateNewWithDuplicateLinks() throws IOException {
        ContentDto jarjestelmaWithLinks = new JarjestelmaDto();
        jarjestelmaWithLinks.setNimi("testiJärjestelmä1");
        JarjestelmaDto createdJarjestelma = createJarjestelma(jarjestelmaWithLinks);

        //luodaan järjestelmälle kaksi duplikaattilinkkiä
        JarjestelmaLinkkausDto link = new JarjestelmaLinkkausDto();
        link.setTietojarjestelmaTunnus(createdJarjestelma.getTunnus());
        link.setLinkattavaTunnus(999);
        link.setSuunta("Kirjoitus");
        link.setTietovirta("1");
        link.setTyyppi("Järjestelmä");

        JarjestelmaLinkkausDto link2 = new JarjestelmaLinkkausDto();
        link2.setTietojarjestelmaTunnus(createdJarjestelma.getTunnus());
        link2.setLinkattavaTunnus(999);
        link2.setSuunta("Kirjoitus");
        link2.setTietovirta("1");
        link2.setTyyppi("Järjestelmä");

        List<JarjestelmaLinkkausDto> jarjestelmaLinkkausList = new ArrayList<JarjestelmaLinkkausDto>();
        jarjestelmaLinkkausList.add(link);
        jarjestelmaLinkkausList.add(link2);
        createdJarjestelma.setJarjestelmaLinkkausList(jarjestelmaLinkkausList);
        JarjestelmaDto updatedJarjestelma = updateJarjestelma(createdJarjestelma);
        List<JarjestelmaLinkkausDto> updatedLinks = updatedJarjestelma.getJarjestelmaLinkkausList();
        assertEquals(1, updatedLinks.size());
        assertEquals(link, updatedLinks.get(0));
    }

    @Test
    public void testJarjestelmaUpdateNewWithInvalidLinks1() throws IOException, JSONException {
        ContentDto jarjestelmaWithLinks = new JarjestelmaDto();
        jarjestelmaWithLinks.setNimi("testiJärjestelmä1");
        JarjestelmaDto createdJarjestelma = createJarjestelma(jarjestelmaWithLinks);

        //luodaan järjestelmälle linkit ja linkitetään järjestelmä itsensä kanssa
        JarjestelmaLinkkausDto link = new JarjestelmaLinkkausDto();
        link.setTietojarjestelmaTunnus(createdJarjestelma.getTunnus());
        link.setLinkattavaTunnus(createdJarjestelma.getTunnus());
        link.setSuunta("Luku");
        link.setTietovirta("Tietovirta");
        link.setTyyppi("Järjestelmä");

        List<JarjestelmaLinkkausDto> jarjestelmaLinkkausList = Collections.singletonList(link);
        createdJarjestelma.setJarjestelmaLinkkausList(jarjestelmaLinkkausList);
        ExtractedResponse response = getUpdateResponse(createdJarjestelma);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }

    @Test
    public void testJarjestelmaUpdateNewWithInvalidLinks2() throws IOException {
        ContentDto jarjestelmaWithLinks = new JarjestelmaDto();
        jarjestelmaWithLinks.setNimi("testiJärjestelmä1");
        JarjestelmaDto createdJarjestelma = createJarjestelma(jarjestelmaWithLinks);

        //luodaan järjestelmälle tyhjä linkki
        JarjestelmaLinkkausDto link = new JarjestelmaLinkkausDto();
        link.setTietojarjestelmaTunnus(null);
        link.setLinkattavaTunnus(null);
        link.setSuunta("Luku");
        link.setTietovirta("Tietovirta");
        link.setTyyppi("Järjestelmä");

        List<JarjestelmaLinkkausDto> jarjestelmaLinkkausList = Collections.singletonList(link);
        createdJarjestelma.setJarjestelmaLinkkausList(jarjestelmaLinkkausList);
        ExtractedResponse response = getUpdateResponse(createdJarjestelma);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }

    @Test
    public void testJarjestelmaUpdateNewWithSimilarLinks() throws IOException {
        ContentDto jarjestelmaWithLinks = new JarjestelmaDto();
        jarjestelmaWithLinks.setNimi("testiJärjestelmä1");
        JarjestelmaDto createdJarjestelma = createJarjestelma(jarjestelmaWithLinks);

        //luodaan järjestelmälle kaksi vastaavaa linkkiä eri tietovirroilla
        JarjestelmaLinkkausDto link = new JarjestelmaLinkkausDto();
        link.setTietojarjestelmaTunnus(createdJarjestelma.getTunnus());
        link.setLinkattavaTunnus(999);
        link.setSuunta("Kirjoitus");
        link.setTietovirta("Tietovirta");
        link.setTyyppi("Järjestelmä");

        JarjestelmaLinkkausDto link2 = new JarjestelmaLinkkausDto();
        link2.setTietojarjestelmaTunnus(createdJarjestelma.getTunnus());
        link2.setLinkattavaTunnus(999);
        link2.setSuunta("Kirjoitus");
        link2.setTietovirta("Tietovirta2");
        link2.setTyyppi("Järjestelmä");

        List<JarjestelmaLinkkausDto> jarjestelmaLinkkausList = new ArrayList<JarjestelmaLinkkausDto>();
        jarjestelmaLinkkausList.add(link);
        jarjestelmaLinkkausList.add(link2);
        createdJarjestelma.setJarjestelmaLinkkausList(jarjestelmaLinkkausList);
        assertEquals(updateJarjestelma(createdJarjestelma).getJarjestelmaLinkkausList().size(), 2);
    }

    /**
     * Test updating jarjestelma links.
     * The first link is modified and the other link is not changed.
     * The link that is not modified should remain unchanged in the database.
     */
    @Test
    public void testJarjestelmaUpdateWithPreexistingLinks1() throws IOException {
        ContentDto jarjestelmaWithLinks = new JarjestelmaDto();
        jarjestelmaWithLinks.setNimi("testiJärjestelmä1");
        JarjestelmaDto createdJarjestelma = createJarjestelma(jarjestelmaWithLinks);

        // Create initial links
        JarjestelmaLinkkausDto link1 = new JarjestelmaLinkkausDto();
        link1.setTietojarjestelmaTunnus(createdJarjestelma.getTunnus());
        link1.setLinkattavaTunnus(999);
        link1.setSuunta("Kirjoitus");
        link1.setTietovirta("Tietovirta");
        link1.setTyyppi("Järjestelmä");

        // This link is saved as if it would have been created from jarjestelma 999 to this jarjestelma
        JarjestelmaLinkkausDto link2 = new JarjestelmaLinkkausDto();
        link2.setLinkattavaTunnus(createdJarjestelma.getTunnus());
        link2.setTietojarjestelmaTunnus(999);
        link2.setSuunta("Kirjoitus");
        link2.setTietovirta("Tietovirta2");
        link2.setTyyppi("Järjestelmä");

        List<JarjestelmaLinkkausDto> jarjestelmaLinkkausList = new ArrayList<JarjestelmaLinkkausDto>();
        jarjestelmaLinkkausList.add(link1);
        jarjestelmaLinkkausList.add(link2);
        createdJarjestelma.setJarjestelmaLinkkausList(jarjestelmaLinkkausList);
        // Save links to the database
        List<JarjestelmaLinkkausDto> links = updateJarjestelma(createdJarjestelma).getJarjestelmaLinkkausList();

        if (links.size() != 2) {
            fail("Did not create two new links");
        }

        // Find saved version of the first link
        JarjestelmaLinkkausDto savedLink1 = findMatchingJarjestelmaLink(link1, links);
        assertNotNull(savedLink1);
        // The first link is edited
        savedLink1.setLinkattavaTunnus(900);

        // Find saved version of the second link
        JarjestelmaLinkkausDto savedLink2 = findMatchingJarjestelmaLink(link2, links);

        // Create new link
        JarjestelmaLinkkausDto link3 = new JarjestelmaLinkkausDto();
        link3.setLinkattavaTunnus(998);
        link3.setTietojarjestelmaTunnus(createdJarjestelma.getTunnus());
        link3.setSuunta("Luku");
        link3.setTietovirta("Tietovirta3");
        link3.setTyyppi("Järjestelmä");

        jarjestelmaLinkkausList.clear();
        jarjestelmaLinkkausList.add(savedLink1);
        jarjestelmaLinkkausList.add(savedLink2);
        jarjestelmaLinkkausList.add(link3);

        createdJarjestelma.setJarjestelmaLinkkausList(jarjestelmaLinkkausList);
        // Update links in the database
        List<JarjestelmaLinkkausDto> updatedLinks = updateJarjestelma(createdJarjestelma).getJarjestelmaLinkkausList();


        assertEquals("the initial links should remain and one new link should be added", 3, updatedLinks.size());
        // Check updates to the first link
        JarjestelmaLinkkausDto updatedLink1 = findMatchingJarjestelmaLink(savedLink1, updatedLinks);
        assertNotNull(updatedLink1);
        assertEquals(savedLink1.getId(), updatedLink1.getId());
        assertEquals("The linkattava value should be updated", 900, updatedLink1.getLinkattavaTunnus().intValue());
        assertEquals("Field should remain unchanged", "Tietovirta", updatedLink1.getTietovirta());
        assertEquals("Field should remain unchanged", "Kirjoitus", updatedLink1.getSuunta());

        // Check that the second link is unchanged
        JarjestelmaLinkkausDto updatedLink2 = findMatchingJarjestelmaLink(savedLink2, updatedLinks);
        assertNotNull(updatedLink2);
        // Note that the equals comparison doesn't check the id
        assertEquals("the second link should remain unchanged", link2, updatedLink2);

        // Check that the third link is correctly added
        JarjestelmaLinkkausDto updatedLink3 = findMatchingJarjestelmaLink(link3, updatedLinks);
        assertNotNull(updatedLink3);
        assertEquals(link3, updatedLink3);
    }

    /**
     * Test updating jarjestelma links.
     * The first link is modified and the second link is omitted.
     * This should update the first link and delete the second link in the database.
     */
    @Test
    public void testJarjestelmaUpdateWithPreexistingLinks2() throws IOException {
        ContentDto jarjestelmaWithLinks = new JarjestelmaDto();
        jarjestelmaWithLinks.setNimi("testiJärjestelmä1");
        JarjestelmaDto createdJarjestelma = createJarjestelma(jarjestelmaWithLinks);

        // Create initial links
        JarjestelmaLinkkausDto link = new JarjestelmaLinkkausDto();
        link.setTietojarjestelmaTunnus(createdJarjestelma.getTunnus());
        link.setLinkattavaTunnus(999);
        link.setSuunta("Kirjoitus");
        link.setTietovirta("Tietovirta");
        link.setTyyppi("Järjestelmä");

        // This link is saved as if it would have been created from jarjestelma 999 to this jarjestelma
        JarjestelmaLinkkausDto link2 = new JarjestelmaLinkkausDto();
        link2.setLinkattavaTunnus(createdJarjestelma.getTunnus());
        link2.setTietojarjestelmaTunnus(999);
        link2.setSuunta("Kirjoitus");
        link2.setTietovirta("Tietovirta2");
        link2.setTyyppi("Järjestelmä");

        List<JarjestelmaLinkkausDto> jarjestelmaLinkkausList = new ArrayList<JarjestelmaLinkkausDto>();
        jarjestelmaLinkkausList.add(link);
        jarjestelmaLinkkausList.add(link2);
        createdJarjestelma.setJarjestelmaLinkkausList(jarjestelmaLinkkausList);
        // Save links to the database
        List<JarjestelmaLinkkausDto> links = updateJarjestelma(createdJarjestelma).getJarjestelmaLinkkausList();

        if (links.size() != 2) {
            fail("Did not create two new links");
        }

        // Find saved version of the first link
        JarjestelmaLinkkausDto savedLink = findMatchingJarjestelmaLink(link, links);
        assertNotNull(savedLink);
        // Modify the link
        savedLink.setLinkattavaTunnus(900);

        jarjestelmaLinkkausList.clear();
        jarjestelmaLinkkausList.add(savedLink);
        // The second link is omitted
        createdJarjestelma.setJarjestelmaLinkkausList(jarjestelmaLinkkausList);
        // Update links in the database
        List<JarjestelmaLinkkausDto> updatedLinks = updateJarjestelma(createdJarjestelma).getJarjestelmaLinkkausList();


        assertEquals("the second link should be deleted", 1, updatedLinks.size());
        // Check updates to the first link
        JarjestelmaLinkkausDto updatedLink = findMatchingJarjestelmaLink(savedLink, updatedLinks);
        assertNotNull(updatedLink);
        assertEquals(savedLink.getId(), updatedLink.getId());
        assertEquals("The linkattava value should be updated", 900, updatedLink.getLinkattavaTunnus().intValue());
        assertEquals("Field should remain unchanged", "Tietovirta", updatedLink.getTietovirta());
        assertEquals("Field should remain unchanged", "Kirjoitus", updatedLink.getSuunta());
    }

    private JarjestelmaLinkkausDto findMatchingJarjestelmaLink(JarjestelmaLinkkausDto link, List<JarjestelmaLinkkausDto> links) {
        for (JarjestelmaLinkkausDto l : links) {
            if (l.equals(link)) { // note that equals doesn't compare ids
                return l;
            }
        }
        return null;
    }

    @Test
    public void testSimpleJarjestelmaUpdate() throws IOException {
        ContentDto jarj = new JarjestelmaDto();
        jarj.setNimi("test");
        JarjestelmaDto created = createJarjestelma(jarj);
        assertNotNull(created);
        assertNotNull(created.getTunnus());

        JarjestelmaDto updateJarj = created;
        String expectedKuvaus = "Test kuvaus";
        updateJarj.setKuvaus(expectedKuvaus);

        assertEquals(updateJarjestelma(updateJarj).getKuvaus(), expectedKuvaus);
    }

    public static JarjestelmaDto updateJarjestelma(ContentDto jarjUpdate) {
        ExtractedResponse response = new ExtractedResponse(rest.update(new TestRequest(), jarjUpdate));
        Response resp = response.getResponse();
        return gson.fromJson(resp.getEntity().toString(), new TypeToken<JarjestelmaDto>() {
        }.getType());
    }

    public static ExtractedResponse getUpdateResponse(ContentDto jarjUpdate) {
            return new ExtractedResponse(rest.update(new TestRequest(), jarjUpdate));
    }

    public static JarjestelmaDto createJarjestelma(ContentDto jarjUpdate) throws IOException {
        if (rest == null) {
            rest = new JarjestelmaController();
            gson = new Gson();
        }
        ExtractedResponse response = new ExtractedResponse(rest.create(new TestRequest(), jarjUpdate));
        Response resp = response.getResponse();
        return gson.fromJson(resp.getEntity().toString(), new TypeToken<JarjestelmaDto>() {}.getType());
    }

    private JarjestelmaDto getJarjestelma(Integer id) {
        ExtractedResponse response = new ExtractedResponse(rest.get(new TestRequest(), id.toString()));
        Response resp = response.getResponse();
        return gson.fromJson(resp.getEntity().toString(), new TypeToken<JarjestelmaDto>() {
        }.getType());
    }

    @Test
    public void testJarjestelmaGetEntry() throws JSONException {
        mainTester.testGetEntry(Catalogue.JARJESTELMA);
    }

    @Test
    public void testJarjestelmaPaging() throws JSONException {
        mainTester.testPaging(Catalogue.JARJESTELMA);
    }

    @Test
    public void TestJarjestelmaUpdate() throws JSONException, IOException {
        mainTester.testUpdate(Catalogue.JARJESTELMA);
    }

    @Test
    public void testJarjestelmaDelete() throws JSONException {
        mainTester.testDelete(Catalogue.JARJESTELMA);
    }

    @Test
    public void testJarjestelmaDeleteWithLinks() throws IOException {
        // create 2 jarjestelmas
        JarjestelmaDto jarjestelmaA = new JarjestelmaDto();
        jarjestelmaA.setNimi("a");
        JarjestelmaDto jarjestelmaB = new JarjestelmaDto();
        jarjestelmaB.setNimi("b");
        jarjestelmaA = createJarjestelma(jarjestelmaA);
        jarjestelmaB = createJarjestelma(jarjestelmaB);
        // link them to each other
        JarjestelmaLinkkausDto link = new JarjestelmaLinkkausDto();
        link.setTietojarjestelmaTunnus(jarjestelmaA.getTunnus());
        link.setLinkattavaTunnus(jarjestelmaB.getTunnus());
        link.setTyyppi("Järjestelmä");
        link.setSuunta("Kirjoitus");
        jarjestelmaA.setJarjestelmaLinkkausList(Collections.singletonList(link));
        jarjestelmaA = updateJarjestelma(jarjestelmaA);
        // check that links were created to jarjestelma _B_
        jarjestelmaB = getJarjestelma(jarjestelmaB.getTunnus());
        assertEquals(1, jarjestelmaB.getJarjestelmaLinkkausList().size());
        // delete first jarjestelma
        rest.delete(new TestRequest(), jarjestelmaA.getTunnus().toString());
        // assert that links are gone from the other one
        JarjestelmaDto b2 = getJarjestelma(jarjestelmaB.getTunnus());
        assertEquals(0, b2.getJarjestelmaLinkkausList().size());
    }

    @Test
    public void testJarjestelmaGetKasite() {
        mainTester.getKasite(Catalogue.JARJESTELMA, true, null);
    }
    // Common tests end here.

    @Test
    public void testSorting() throws JSONException {
        TestDbUtil.writeToDb(TestUtil.createEntriesInJson(Catalogue.JARJESTELMA, 25));

        Response response = rest.getAll("25", "0", null, "asc", null, null, null, null);
        JSONObject responseInJson = getJson(response);
        JSONArray itemsArray = (JSONArray) responseInJson.get("items");

        assertEquals("a01", itemsArray.getJSONObject(0).get("nimi"));
        assertEquals("a25", itemsArray.getJSONObject(24).get("nimi"));

        response = rest.getAll("25", "0", null, "desc", null, null, null, null);
        responseInJson = getJson(response);
        itemsArray = (JSONArray) responseInJson.get("items");

        assertEquals("a25", itemsArray.getJSONObject(0).get("nimi"));
        assertEquals("a01", itemsArray.getJSONObject(24).get("nimi"));
    }

    @Test
    public void testSortinMinimal() throws JSONException {
        TestDbUtil.writeToDb(TestUtil.createEntriesInJson(Catalogue.JARJESTELMA, 25));

        Response response = rest.getAllMinimal("25", "0", null, "asc", null, null, null);
        JSONObject responseInJson = getJson(response);
        JSONArray itemsArray = (JSONArray) responseInJson.get("items");

        assertEquals("a01", itemsArray.getJSONObject(0).get("nimi"));
        assertEquals("a25", itemsArray.getJSONObject(24).get("nimi"));

        response = rest.getAll("25", "0", null, "desc", null, null, null, null);
        responseInJson = getJson(response);
        itemsArray = (JSONArray) responseInJson.get("items");

        assertEquals("a25", itemsArray.getJSONObject(0).get("nimi"));
        assertEquals("a01", itemsArray.getJSONObject(24).get("nimi"));
    }

    @Test
    public void testFilter() throws JSONException {
        setupDbWithTestData();

        ExtractedResponse response = new ExtractedResponse(
                rest.getAll("100", "0", "Digiroad", "asc", null, null, null, null));
        JSONArray items = response.getItems();
        assertEquals(1, items.length());
        JSONObject item = items.getJSONObject(0);
        assertEquals("Digiroad 2: Väyläverkonhallintasovellus", item.get("nimi"));
        assertEquals(1, response.getValue("count"));

        response = new ExtractedResponse(rest.getAll("100", "0", "liikenteen kamera", null, null, null, null, null));
        items = response.getItems();
        assertEquals(3, items.length());
        item = items.getJSONObject(0);
        assertEquals("Junaliikenteen valtakunnallinen kameravalvontajärjestelmä", item.get("nimi"));
        assertEquals(3, response.getValue("count"));

        response = new ExtractedResponse(rest.getAll("1", "0", "liikenteen kamera", "desc", null, null, null, null));
        items = response.getItems();
        assertEquals(1, items.length());
        item = items.getJSONObject(0);
        assertEquals("Vesiliikenteen valtakunnallinen kameravalvontajärjestelmä", item.get("nimi"));
        assertEquals(3, response.getValue("count"));

        response = new ExtractedResponse(
                rest.getAll("100", "0", "liikenteen", "desc", Arrays.asList("Kehityksessä"),
                        Arrays.asList("tietojärjestelmä"), Arrays.asList("VTJ - Meri"), null));
        items = response.getItems();
        assertEquals(1, items.length());
        item = items.getJSONObject(0);
        assertEquals(400, item.get("tunnus"));
        assertEquals(1, response.getValue("count"));

        response = new ExtractedResponse(rest.getAll("100", "0", "zzz", "desc", null, null, null, null));
        assertEquals(0, response.getItems().length());
        assertEquals(0, response.getValue("count"));
    }

    @Test
    public void testPersonFilter() throws SQLException, IOException, JSONException {
        JSONArray testData = TestUtil.getJsonArray("src/test/resources/test-material/tietojarjestelmasalkku1.json");
        TestDbUtil.writeToDb(testData);


        List<String> rooliNimiList = Arrays.asList("OMISTAJA");
        List<Rooli> rooliList = createTestRooliList(rooliNimiList);
        List<Henkilo> henkiloList = createTestHenkiloList(rooliNimiList);
        List<JarjestelmaHenkiloRooli> henkiloRooliList = createTestJarjestelmaWithHenkiloRooliList(rooliList, henkiloList);

        assertEquals(henkiloRooliList.size(), 1);

        JarjestelmaHenkiloRooli jarjestelmaHenkiloRooli = henkiloRooliList.get(0);
        Henkilo henkilo = henkiloList.get(0);
        assertEquals(henkilo.getTunnus(), jarjestelmaHenkiloRooli.getHenkiloId());
        String henkiloNamePartly = henkilo.getEtunimi().substring(0, 3);

        ExtractedResponse response = new ExtractedResponse(
                new JarjestelmaController().getAll("100", "0", henkiloNamePartly, "asc", null,
                        null, null, null));

        assertEquals(response.getItems().getJSONObject(0).get("tunnus"), jarjestelmaHenkiloRooli.getSysteemiId());
    }

    @Test
    public void testFilterLifeSpan() throws IOException {
        // create Jarjestelma items with different life spans and save
        saveItems(createItemsWithAllLifeSpans(5));
        // get list from rest with different life spans
        assertLifeSpan(Arrays.asList("Suunnitteilla"));
        assertLifeSpan(Arrays.asList("Kehityksessä"));
        assertLifeSpan(Arrays.asList("Tuotannossa"));
        assertLifeSpan(Arrays.asList("Poistumassa"));
        assertLifeSpan(Arrays.asList("Poistunut"));
        assertLifeSpan(Arrays.asList("Poistunut", "Tuotannossa"));
    }

    @Test
    public void testFilterType() throws IOException {
        // create Jarjestelma items with different types and save
        saveItems(createItemsWithAllTypes(5));
        // get list from rest with different type
        assertType(Arrays.asList("lisenssi"));
        assertType(Arrays.asList("palvelu"));
        assertType(Arrays.asList("sovellus"));
        assertType(Arrays.asList("sulautettu järjestelmä"));
        assertType(Arrays.asList("tietojärjestelmä"));
        assertType(Arrays.asList("tietojärjestelmä", "lisenssi"));
    }

    @Test
    public void testFilterRegion() throws IOException {
        // create Jarjestelma items with different regions and save
        int count = 5;
        saveItems(createItemsWithAllRegions(count));
        // get list from rest with different regions
        assertRegion(Arrays.asList("LiHa - Meri"), count);
        assertRegion(Arrays.asList("LiHa - Rata"), count);
        assertRegion(Arrays.asList("LiHa - Tie"), count);
        assertRegion(Arrays.asList("Liikenteen palvelut"), count);
        assertRegion(Arrays.asList("VTJ - Meri"), count);
        assertRegion(Arrays.asList("VTJ - Rata"), count);
        assertRegion(Arrays.asList("VTJ - Tie"), count);
        assertRegion(Arrays.asList("Yhteinen ICT"), count);
        assertRegion(Arrays.asList("Yhteinen ICT", "Liikenteen palvelut"), count * 2);
    }

    @Test
    public void testFilterOwningOrganization() throws IOException {
        // create Jarjestelma items with different organizations and save
        int count = 5;
        saveItems(createItemsWithOwningOrganizations(count));
        // get list from rest with different organizations
        assertOwningOrganization(Arrays.asList("Org1"), count);
        assertOwningOrganization(Arrays.asList("Org2"), count);
        assertOwningOrganization(Arrays.asList("Org3"), count);
        assertOwningOrganization(Arrays.asList("Org1", "Org2"), count * 2);
    }

    @Test
    public void testCreateJarjestelmaLink() throws IOException {
        mainTester.testCreateJarjestelmaLink();
    }

    private void assertLifeSpan(List<String> list) {
        ExtractedResponse response = new ExtractedResponse(rest.getAll("30", "0", null, "asc", list, null, null, null));
        List<Object> lifeSpans = response.getValues("items", "elinkaaritila");
        assertFalse(lifeSpans.isEmpty());
        for (Object lifeSpan : lifeSpans) {
            String text = (String) lifeSpan;
            assertTrue(list.contains(text));
        }
    }

    private void assertType(List<String> list) {
        ExtractedResponse response = new ExtractedResponse(rest.getAll("30", "0", null, "asc", null, list, null, null));
        List<Object> types = response.getValues("items", "jarjestelmatyyppi");
        assertFalse(types.isEmpty());
        for (Object type : types) {
            String text = (String) type;
            assertTrue(list.contains(text));
        }
    }

    private void assertRegion(List<String> list, int count) {
        ExtractedResponse response = new ExtractedResponse(rest.getAll("30", "0", null, "asc", null, null, list, null));
        List<Object> regions = response.getValues("items", "jarjestelmaalue");
        assertEquals(count, regions.size());
        for (Object region : regions) {
            String text = (String) region;
            assertTrue(list.contains(text));
        }
    }

    private void assertOwningOrganization(List<String> list, int count) {
        ExtractedResponse response = new ExtractedResponse(rest.getAll("30", "0", null, "asc", null, null, null, list));
        List<Object> organizations = response.getValues("items", "omistava_organisaatio");
        assertEquals(count, organizations.size());
        for (Object organization : organizations) {
            String text = (String) organization;
            assertTrue(list.contains(text));
        }
    }

    private void setupDbWithTestData() {
        JSONArray testData = TestUtil.getJsonArray("src/test/resources/test-material/tietojarjestelmasalkku1.json");
        TestDbUtil.writeToDb(testData);
    }

    private void saveItems(List<JarjestelmaDto> items) throws IOException {
        for (JarjestelmaDto jarjestelma : items) {
            rest.create(new TestRequest(), (ContentDto) jarjestelma);
        }
    }

    private void setupDbWithTestDataWhereSameIdsAndActive() {
        JSONArray testData = TestUtil.getJsonArray("src/test/resources/test-material/tietojarjestelmasalkku3.json");
        TestDbUtil.writeToDb(testData);
    }

    @SuppressWarnings("unused")
    private ModelResults createJarjestelmaEntries(int count) {
        ArrayList<String> names = TestUtil.createRandomNameList(count);

        List<Haettava> jarjestelmaEntries = new ArrayList<Haettava>();
        for (int i = 1; i <= count; i++) {
            Jarjestelma jarjestelma = new Jarjestelma();
            jarjestelma.setTunnus(i);
            jarjestelma.setNimi(names.get(i - 1));
            jarjestelmaEntries.add(jarjestelma);
        }
        return new ModelResults(jarjestelmaEntries, jarjestelmaEntries.size());
    }

    private JarjestelmaDto createEntry(String name) {
        JarjestelmaDto jarjestelma = new JarjestelmaDto();
        jarjestelma.setNimi(name);
        return jarjestelma;
    }

    private JarjestelmaLinkkausDto createJarjLinkEntry() throws JSONException {
        JarjestelmaDto jarjestelma = createEntry("Järj2");
        Integer tunnus = (Integer) getJson(rest.create(new TestRequest(), (ContentDto) jarjestelma)).get("tunnus");
        JarjestelmaLinkkausDto link = new JarjestelmaLinkkausDto();
        link.setLinkattavaTunnus(tunnus);
        link.setSuunta("Luku");
        link.setTietovirta("123");
        link.setTyyppi("Järjestelmä");

        return link;
    }

    private JSONObject getJson(Response response) {
        return (JSONObject) response.getEntity();
    }

    private List<JarjestelmaDto> createItemsWithAllLifeSpans(int countPerLifeSpan) {
        List<String> lifeSpans = Arrays.asList("Suunnitteilla", "Kehityksessä", "Tuotannossa", "Poistumassa",
                "Poistunut");
        JarjestelmaMother mother = JarjestelmaMother.def();
        List<JarjestelmaDto> items = new ArrayList<JarjestelmaDto>();
        for (String lifeSpan : lifeSpans) {
            for (int i = 0; i < countPerLifeSpan; i++) {
                JarjestelmaDto jarjestelma = mother.nimi(lifeSpan + i).elinkaaritila(lifeSpan).build();
                items.add(jarjestelma);
            }
        }
        return items;
    }

    private List<JarjestelmaDto> createItemsWithAllTypes(int countPerType) {
        List<String> types = Arrays.asList("lisenssi", "palvelu", "sovellus", "sulautettu järjestelmä",
                "tietojärjestelmä");
        JarjestelmaMother mother = JarjestelmaMother.def();
        List<JarjestelmaDto> items = new ArrayList<JarjestelmaDto>();
        for (String type : types) {
            for (int i = 0; i < countPerType; i++) {
                JarjestelmaDto jarjestelma = mother.nimi(type + i).jarjestelmatyyppi(type).build();
                items.add(jarjestelma);
            }
        }
        return items;
    }

    private List<JarjestelmaDto> createItemsWithAllRegions(int countPerRegion) {
        List<String> regions = Arrays.asList("LiHa - Meri", "LiHa - Rata", "LiHa - Tie", "Liikenteen palvelut",
                "VTJ - Meri", "VTJ - Rata", "VTJ - Tie", "Yhteinen ICT");
        JarjestelmaMother mother = JarjestelmaMother.def();
        List<JarjestelmaDto> items = new ArrayList<JarjestelmaDto>();
        for (String region : regions) {
            for (int i = 0; i < countPerRegion; i++) {
                JarjestelmaDto jarjestelma = mother.nimi(region + i).jarjestelmaalue(region).build();
                items.add(jarjestelma);
            }
        }
        return items;
    }

    private List<JarjestelmaDto> createItemsWithOwningOrganizations(int countPerOrganization) {
        List<String> organizations = Arrays.asList("Org1", "Org2", "Org3");
        JarjestelmaMother mother = JarjestelmaMother.def();
        List<JarjestelmaDto> items = new ArrayList<>();
        for (String organization : organizations) {
            for (int i = 0; i < countPerOrganization; i++) {
                JarjestelmaDto jarjestelma = mother.nimi(organization + i).owningOrganization(organization).build();
                items.add(jarjestelma);
            }
        }
        return items;
    }

}