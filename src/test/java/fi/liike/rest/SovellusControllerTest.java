package fi.liike.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fi.liike.externalApi.ExternalSovellusController;
import fi.liike.rest.Controller.SovellusController;
import fi.liike.rest.api.Catalogue;
import fi.liike.rest.api.DtoSovellusResults;
import fi.liike.rest.api.dto.SovellusDto;
import fi.liike.testutils.TestRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SovellusControllerTest {
    private MainRestTester mainTester;
    private SovellusController controller;
    private ExternalSovellusController externalSovellusController;
    private Gson gson;

    @Before
    public void setUp() {
        this.controller = new SovellusController();
        this.externalSovellusController = new ExternalSovellusController();
        this.mainTester = new MainRestTester(Catalogue.SOVELLUS);
        this.gson = new Gson();
    }

    @After
    public void tearDown() {
        mainTester.clear();
    }

    @Test
    @Ignore
    // requires pgsql
    public void deactiveSovellusTest() throws IllegalAccessException, NoSuchFieldException, IOException, SQLException {
        Integer tunnus = createAndDisableTestSovellus();

        Response response = controller.get(tunnus.toString(), false);
        SovellusDto deleted = gson.fromJson(response.getEntity().toString(),
                new TypeToken<SovellusDto>(){}.getType());

        assertEquals((Integer) 1, deleted.getPoistunut());
    }

    private Integer createAndDisableTestSovellus() throws IllegalAccessException, NoSuchFieldException, IOException, SQLException {
        String csv = "\"ADGroup\",\"Tier1\",\"Tier2\",\"Tier3\",\"Model\",\"ConfigVersion\",\"ConfigurationBasicNumber\"," +
                "\"Criticality\",\"Manufacturer\",\"Version\",\"Person Role\",\"Signature0\",\"Misc\",\"Language\",\"ForOS\"," +
                "\"ForArchitecture\",\"Platfor\",\"Dependecies\",\"Relations\",\"StandardizedDate\",\"AcceptanceDate\",\"Company\"," +
                "\"Full_Name\",\"Login_Name\",\"PeopleGroup_Form_Entry_ID\"\n" +
                "\"Test sovellus\",\"Software\",\"Application\",\"Other Type of Software\",\"Test\",\"NULL\",\"Individual\",\"N/A\"," +
                "\"Test\",\"200.0.103.0\",\"MainUser\",\"{123-ABC}\",\"NULL\",\"Eng\",\"W7x86, W7x64, W10x64\",\"NULL\"," +
                "\"Workstation\",\"NULL\",\"Lisenssipalvelin 123\",\"NULL\",\"NULL\",\"Liikennevirasto\"," +
                "\"Teppo Testi\",\"lirs.EILOYDY\",\"PPLND0000725144\"";

        externalSovellusController.importExternalSovellusList(new TestRequest(), csv);

        Integer expectedId = 1;
        Response response = controller.get(expectedId.toString());
        SovellusDto created = gson.fromJson(response.getEntity().toString(),
                new TypeToken<SovellusDto>(){}.getType());
        assertEquals((Integer) 1, created.getTunnus());
        assertEquals((Integer) 0, created.getPoistunut());

        controller.delete(new TestRequest(), created.getTunnus().toString());
        return created.getTunnus();
    }

    @Test
    @Ignore
    // requires pgsql
    public void getInactiveSovellusErrorTest() throws IllegalAccessException, NoSuchFieldException, IOException, SQLException {
        Integer tunnus = createAndDisableTestSovellus();

        Response response = controller.get(tunnus.toString(), true);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertEquals(null, response.getEntity());
    }

    private class ImportMetadata {
        String latest;
        String successful;
        ImportMetadata(String l, String s) {
            latest = l;
            successful = s;
        }
    }

    private ImportMetadata getImportMetadata() {
        Response initialResponse = this.controller.getAll("0", "0", "", "asc", new ArrayList<String>(),
                new ArrayList<String>(), new ArrayList<String>());
        DtoSovellusResults dtoSovellusResults = gson.fromJson(initialResponse.getEntity().toString(),
                new TypeToken<DtoSovellusResults>(){}.getType());
        return new ImportMetadata(dtoSovellusResults.getLatestImport(), dtoSovellusResults.getLatestSuccessfulImport());
    }

    @Test
    @Ignore
    // requires pgsql
    public void testImportMetadataWithSuccessfulImport() throws IOException, SQLException {
        // Get initial import metadata

        ImportMetadata initialImportMetadata = this.getImportMetadata();
        String initialLatest = initialImportMetadata.latest;
        String initialSuccessful = initialImportMetadata.successful;

        // Import new valid csv
        String csv = "\"ADGroup\",\"Tier1\",\"Tier2\",\"Tier3\",\"Model\",\"ConfigVersion\",\"ConfigurationBasicNumber\"," +
                "\"Criticality\",\"Manufacturer\",\"Version\",\"Person Role\",\"Signature0\",\"Misc\",\"Language\",\"ForOS\"," +
                "\"ForArchitecture\",\"Platfor\",\"Dependecies\",\"Relations\",\"StandardizedDate\",\"AcceptanceDate\",\"Company\"," +
                "\"Full_Name\",\"Login_Name\",\"PeopleGroup_Form_Entry_ID\"\n" +
                "\"Test sovellus (valid)\",\"Software\",\"Application\",\"Other Type of Software\",\"Test\",\"NULL\",\"Individual\",\"N/A\"," +
                "\"Test\",\"200.0.103.0\",\"MainUser\",\"{123-ABC}\",\"NULL\",\"Eng\",\"W7x86, W7x64, W10x64\",\"NULL\"," +
                "\"Workstation\",\"NULL\",\"Lisenssipalvelin 123\",\"NULL\",\"NULL\",\"Liikennevirasto\"," +
                "\"Teppo Testi\",\"lirs.EILOYDY\",\"PPLND0000725144\"";
        externalSovellusController.importExternalSovellusList(new TestRequest(), csv);

        // Get new import data
        ImportMetadata newImportMetadata = this.getImportMetadata();
        String latestAfterImport = newImportMetadata.latest;
        String successfulAfterImport = newImportMetadata.successful;

        // Assert that there is a new import and that it's successful
        assertNotEquals("latest import should be different from initial import", initialLatest, latestAfterImport);
        assertNotEquals("latest successful import should be different from initial import", initialSuccessful, successfulAfterImport);
        assertEquals("latest successful import should be the latest import", latestAfterImport, successfulAfterImport);
    }

    @Test
    @Ignore
    // requires pgsql
    public void testImportMetadataWithFailedImport() throws IOException, SQLException {
        // Get initial import metadata
        ImportMetadata initialImportMetadata = this.getImportMetadata();
        String initialLatest = initialImportMetadata.latest;
        String initialSuccessful = initialImportMetadata.successful;

        // Import new invalid csv
        String csv = "\"ADGroup\",\"Tier1\",\"Tier2\",\"Tier3\",\"Model\",\"ConfigVersion\",\"ConfigurationBasicNumber\"," +
                "\"Criticality\",\"Manufacturer\",\"Version\",\"Person Role\",\"Signature0\",\"Misc\",\"Language\",\"ForOS\"," +
                "\"ForArchitecture\",\"Platfor\",\"Dependecies\",\"Relations\",\"StandardizedDate\",\"AcceptanceDate\",\"Company\"," +
                "\"Full_Name\",\"Login_Name\",\"PeopleGroup_Form_Entry_ID\"\n" +
                "\"Test sovellus (invalid)\",\"Software\",\"Application\",\"Other Type of Software\",\"Test\",\"NULL\",\"Individual\",\"N/A\"," +
                "\"Test\",\"200.0.103.0\",\"MainUser\",\"{123-ABC}\",\"NULL\",\"Eng\",\"W7x86, W7x64, W10x64\",\"NULL\"," +
                "\"Workstation\",\"NULL\",\"Lisenssipalvelin 123\",\"NULL\",\"NULL\",\"Liikennevirasto\"," +
                "\"Teppo Testi\",\"lirs.EILOYDY\",\"PPLND0000725144\",\"these fields\",\"aren't supported\"";
        externalSovellusController.importExternalSovellusList(new TestRequest(), csv);

        // Get new import data
        ImportMetadata newImportMetadata = this.getImportMetadata();
        String latestAfterImport = newImportMetadata.latest;
        String successfulAfterImport = newImportMetadata.successful;

        // Assert that there is a new import but it's not successful
        assertNotEquals("latest import should be different from initial import", initialLatest, latestAfterImport);
        assertEquals("latest successful import should be the initial import", initialSuccessful, successfulAfterImport);
        assertNotEquals("latest successful import should be different from latest import", latestAfterImport, successfulAfterImport);
    }
}
