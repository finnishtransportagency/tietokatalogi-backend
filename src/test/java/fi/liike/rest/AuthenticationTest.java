package fi.liike.rest;

import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fi.liike.rest.Controller.*;
import fi.liike.rest.api.Catalogue;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.*;
import fi.liike.testutils.TestRequest;
import fi.liike.testutils.TestUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.EnumSet;

import static org.junit.Assert.*;

public class AuthenticationTest {
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationTest.class);

    private JarjestelmaController jarjestelmaController;
    private MainRestTester mainTester;
    private Gson gson;

    private FyysinenController fyysinenController;
    private LooginenController looginenController;
    private TietolajiController tietolajiController;
    private TietoryhmaController tietoryhmaController;
    private PaatietoryhmaController paatietoryhmaController;

    private SovellusController sovellusController;
    private PalveluController palveluController;
    private TietojarjestelmapalveluController tietojarjestelmapalveluController;
    private TietoomaisuusController tietoomaisuusController;

    private EnumSet<Catalogue> cataloguesWithController;

    @Before
    public void setUp() {
        jarjestelmaController = new JarjestelmaController();
        mainTester = new MainRestTester(Catalogue.JARJESTELMA);
        this.gson = new Gson();

        fyysinenController = new FyysinenController();
        looginenController = new LooginenController();
        tietolajiController = new TietolajiController();
        tietoryhmaController = new TietoryhmaController();
        paatietoryhmaController = new PaatietoryhmaController();

        sovellusController = new SovellusController();
        palveluController = new PalveluController();
        tietojarjestelmapalveluController = new TietojarjestelmapalveluController();
        tietoomaisuusController = new TietoomaisuusController();

        cataloguesWithController = EnumSet.complementOf(
                EnumSet.of(Catalogue.HENKILO, Catalogue.MOLEKYYLILINKKI, Catalogue.TIETOARKKITEHTUURI)
        );
    }

    @After
    public void tearDown() throws Exception {
        mainTester.clear();
    }

    @Test
    public void testCreateCatalogueWithInsufficientRights() {
        // no header
        TestRequest testRequest = new TestRequest();
        testRequest.removeHeader("OAM_GROUPS");
        int status = Response.Status.FORBIDDEN.getStatusCode();
        this.testCreateCatalogue(testRequest, status);

        // insufficient header
        testRequest.addHeader("OAM_GROUPS", "group1,group2,group3");
        this.testCreateCatalogue(testRequest, status);
    }

    @Test
    public void testCreateCatalogueWithSufficientRights() {
        TestRequest testRequest = new TestRequest();
        testRequest.addHeader("OAM_GROUPS", "tk_muokkaus");
        int status = Response.Status.OK.getStatusCode();
        this.testCreateCatalogue(testRequest, status);

        testRequest.addHeader("OAM_GROUPS", "tk_tietoturva");
        this.testCreateCatalogue(testRequest, status);

        testRequest.addHeader("OAM_GROUPS", "tk_muokkaus,tk_tietoturva");
        this.testCreateCatalogue(testRequest, status);

        testRequest.addHeader("OAM_GROUPS", "redundantGroup1,tk_muokkaus,redundantGroup2,tk_tietoturva");
        this.testCreateCatalogue(testRequest, status);
    }

    private void testCreateCatalogue(TestRequest testRequest, int expectedStatus) {
        ExtractedResponse response;
        try {
            // fyysinen
            response = this.createTestEntity(testRequest, new FyysinenTietovarantoDto(), this.fyysinenController);
            assertEquals(expectedStatus, response.getStatus());

            // looginen
            response = this.createTestEntity(testRequest, new LooginenTietovarantoDto(), this.looginenController);
            assertEquals(expectedStatus, response.getStatus());

            // tietolaji
            response = this.createTestEntity(testRequest, new TietolajiDto(), this.tietolajiController);
            assertEquals(expectedStatus, response.getStatus());

            // tietoryhma
            // Tietoryhma needs a paatietoryhma id to be set separately
            TietoryhmaDto tietoryhmaDto = new TietoryhmaDto();
            tietoryhmaDto.setNimi("name");
            tietoryhmaDto.setPaatietoryhma(1);
            response = new ExtractedResponse(this.tietoryhmaController.create(testRequest, tietoryhmaDto));
            assertEquals(expectedStatus, response.getStatus());

            // paatietoryhma
            response = this.createTestEntity(testRequest, new PaatietoryhmaDto(), this.paatietoryhmaController);
            assertEquals(expectedStatus, response.getStatus());

            // sovellus
            response = this.createTestEntity(testRequest, new SovellusDto(), this.sovellusController);
            // Sovellus post is not implemented and thus returns 404
            if (expectedStatus == Response.Status.OK.getStatusCode()) {
                assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
            }
            else {
                assertEquals(expectedStatus, response.getStatus());
            }

            // palvelu
            response = this.createTestEntity(testRequest, new PalveluDto(), this.palveluController);
            assertEquals(expectedStatus, response.getStatus());

            // tietojarjestelmapalvelu
            response = this.createTestEntity(testRequest, new TietojarjestelmapalveluDto(), this.tietojarjestelmapalveluController);
            assertEquals(expectedStatus, response.getStatus());

            // tieto-omaisuus
            // Tieto-omaisuus needs tietojarjestelma id set separately
            TietoomaisuusDto tietoomaisuusDto = new TietoomaisuusDto();
            tietoomaisuusDto.setTietojarjestelma_tunnus(1);
            response = new ExtractedResponse(this.tietoomaisuusController.create(testRequest, tietoomaisuusDto));
            assertEquals(expectedStatus, response.getStatus());

        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testDeleteCatalogueWithInsufficientRights() {
        // no header
        TestRequest testRequest = new TestRequest();
        testRequest.removeHeader("OAM_GROUPS");
        this.testDeleteCatalogue(testRequest, false);

        // insufficient header
        testRequest.addHeader("OAM_GROUPS", "group1,group2,group3");
        this.testDeleteCatalogue(testRequest, false);
    }

    /**
     * Tests that the DELETE request does not return 403 FORBIDDEN.
     * This does not check that the request is otherwise successful.
     */
    @Test
    public void testDeleteCatalogueWithSufficientRights() {
        TestRequest testRequest = new TestRequest();
        testRequest.addHeader("OAM_GROUPS", "tk_muokkaus");
        this.testDeleteCatalogue(testRequest, true);

        testRequest.addHeader("OAM_GROUPS", "tk_tietoturva");
        this.testDeleteCatalogue(testRequest, true);

        testRequest.addHeader("OAM_GROUPS", "tk_muokkaus,tk_tietoturva");
        this.testDeleteCatalogue(testRequest, true);

        testRequest.addHeader("OAM_GROUPS", "redundantGroup1,tk_muokkaus,redundantGroup2,tk_tietoturva");
        this.testDeleteCatalogue(testRequest, true);
    }

    private void testDeleteCatalogue(TestRequest testRequest, Boolean shouldHaveRights) {
        for (Catalogue catalogue : this.cataloguesWithController) {
            LOG.info("Testing catalogue " + catalogue.toString());
            MainController controller = TestUtil.getRest(catalogue);
            Response response = controller.delete(testRequest, "999");
            this.checkForbiddenStatus(response.getStatus(), shouldHaveRights);
        }
    }

    private void checkForbiddenStatus(int statusCode, Boolean shouldHaveRights) {
        int forbiddenStatusCode = Response.Status.FORBIDDEN.getStatusCode();
        if (shouldHaveRights) {
            assertNotEquals(forbiddenStatusCode, statusCode);
        }
        else {
            assertEquals(forbiddenStatusCode, statusCode);
        }
    }

    @Test
    public void testUpdateCatalogueWithInsufficientRights() {
        // no header
        TestRequest testRequest = new TestRequest();
        testRequest.removeHeader("OAM_GROUPS");
        this.testUpdateCatalogue(testRequest, false);

        // insufficient header
        testRequest.addHeader("OAM_GROUPS", "group1,group2,group3");
        this.testUpdateCatalogue(testRequest, false);
    }

    /**
     * Tests that the PUT request does not return 403 FORBIDDEN.
     * This does not check that the request is otherwise successful.
     */
    @Test
    public void testUpdateCatalogueWithSufficientRights() {
        TestRequest testRequest = new TestRequest();
        testRequest.addHeader("OAM_GROUPS", "tk_muokkaus");
        this.testUpdateCatalogue(testRequest, true);

        testRequest.addHeader("OAM_GROUPS", "tk_tietoturva");
        this.testUpdateCatalogue(testRequest, true);

        testRequest.addHeader("OAM_GROUPS", "tk_muokkaus,tk_tietoturva");
        this.testUpdateCatalogue(testRequest, true);

        testRequest.addHeader("OAM_GROUPS", "redundantGroup1,tk_muokkaus,redundantGroup2,tk_tietoturva");
        this.testUpdateCatalogue(testRequest, true);
    }

    private void testUpdateCatalogue(TestRequest testRequest, Boolean shouldHaveRights) {
        for (Catalogue catalogue : this.cataloguesWithController) {
            LOG.info("Testing catalogue " + catalogue.toString());
            MainController controller = TestUtil.getRest(catalogue);
            try {
                ContentDto dto = TestUtil.createEntry(catalogue);
                Response response = controller.update(testRequest, dto);
                this.checkForbiddenStatus(response.getStatus(), shouldHaveRights);
            }
            catch (IOException e) {
                fail(e.getLocalizedMessage());
            }
        }
    }

    @Test
    public void testCreateJarjestelmaWithSecuredFieldFAILS() throws IOException {
        ExtractedResponse response = createTestJarjestelma(new TestRequest());
        assertEquals(response.getStatus(), Response.Status.FORBIDDEN.getStatusCode());
    }

    @Test
    public void testCreateJarjestelmaWithSecuredFieldSUCCESS() throws IOException {
        TestRequest validTestRequest = new TestRequest();
        validTestRequest.addHeader("OAM_GROUPS", "tk_tietoturva");
        ExtractedResponse response = createTestJarjestelma(validTestRequest);
        assertEquals(response.getValue("tunnus") != null, true);
        assertEquals(response.getValue("tietoturvasopimus"), Boolean.TRUE);
    }

    @Test
    public void testUpdateJarjestelmaWithSecuredFieldFAILS() throws IOException {
        TestRequest validTestRequest = new TestRequest();
        validTestRequest.addHeader("OAM_GROUPS", "tk_tietoturva");
        ExtractedResponse response = createTestJarjestelma(validTestRequest);
        JarjestelmaDto modifiedJarjestelma = getJarjestelmaFromResponse(response);
        modifiedJarjestelma.setTietoturvasopimus(!modifiedJarjestelma.getTietoturvasopimus());
        response = new ExtractedResponse(jarjestelmaController.update(new TestRequest(), (ContentDto) modifiedJarjestelma));
        assertEquals(response.getStatus(), Response.Status.FORBIDDEN.getStatusCode());
    }

    @Test
    public void testUpdateJarjestelmaWithSecuredFieldSUCCESS() throws IOException {
        TestRequest validTestRequest = new TestRequest();
        validTestRequest.addHeader("OAM_GROUPS", "tk_tietoturva");
        ExtractedResponse response = createTestJarjestelma(validTestRequest);
        JarjestelmaDto createdJarjestelma = getJarjestelmaFromResponse(response);
        Boolean tietoturvaSopimus = !createdJarjestelma.getTietoturvasopimus();
        createdJarjestelma.setTietoturvasopimus(tietoturvaSopimus);
        response = new ExtractedResponse(jarjestelmaController.update(validTestRequest, (ContentDto) createdJarjestelma));
        JarjestelmaDto modifiedJarjestelma = getJarjestelmaFromResponse(response);
        assertEquals(modifiedJarjestelma.getTietoturvasopimus(), tietoturvaSopimus);
    }

    private ExtractedResponse createTestJarjestelma(TestRequest testRequest) {
        JarjestelmaDto jarjestelma = new JarjestelmaDto();
        jarjestelma.setNimi("Uusi järjestelmä");
        jarjestelma.setTietoturvasopimus(true);
        return new ExtractedResponse(jarjestelmaController.create(testRequest, (ContentDto) jarjestelma));
    }

    private ExtractedResponse createTestEntity(TestRequest testRequest, ContentDto dto, MainController controller)
            throws IOException {
        dto.setNimi("name");
        return new ExtractedResponse(controller.create(testRequest, dto));
    }

    @Test
    public void testGetJarjestelma() {
        TestRequest validTestRequest = new TestRequest();
        validTestRequest.addHeader("OAM_GROUPS", "tk_tietoturva");
        ExtractedResponse response = createTestJarjestelma(validTestRequest);
        JarjestelmaDto jarjestelma = this.getJarjestelmaFromResponse(response);
        String jarjestelmaTunnus = jarjestelma.getTunnus().toString();

        response = new ExtractedResponse(jarjestelmaController.get(validTestRequest, jarjestelmaTunnus));
        jarjestelma = getJarjestelmaFromResponse(response);
        assertEquals(jarjestelma.getNoRightsToModify().size(), 0);

        TestRequest requestWithNoRights = new TestRequest();
        requestWithNoRights.removeHeader("OAM_GROUPS");
        response = new ExtractedResponse(jarjestelmaController.get(requestWithNoRights, jarjestelmaTunnus));
        jarjestelma = getJarjestelmaFromResponse(response);
        assertEquals(ImmutableSet.of("ALL_FIELDS", "tietoturvasopimus"), ImmutableSet.copyOf(jarjestelma.getNoRightsToModify()));
    }

    private JarjestelmaDto getJarjestelmaFromResponse(ExtractedResponse response) {
        return gson.fromJson(response.getResponse().getEntity().toString(),
                new TypeToken<JarjestelmaDto>(){}.getType());
    }

}
