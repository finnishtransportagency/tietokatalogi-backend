package fi.liike.rest;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import fi.liike.externalApi.ExternalJarjestelmaController;
import fi.liike.rest.Model.Henkilo;
import fi.liike.rest.Model.Rooli;
import fi.liike.rest.Service.HenkiloService;
import fi.liike.rest.api.Catalogue;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.JarjestelmaDto;
import fi.liike.rest.api.dto.JarjestelmaSahkeDto;
import fi.liike.rest.util.Utils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static fi.liike.rest.JarjestelmasalkkuTest.createJarjestelma;
import static fi.liike.rest.JarjestelmasalkkuTest.updateJarjestelma;
import static fi.liike.service.HenkiloServiceTest.*;
import static org.junit.Assert.assertEquals;

public class ExternalJarjestelmaControllerTest {
    private ExternalJarjestelmaController rest;
    private HenkiloService henkiloService;
    private MainRestTester mainTester;
    private Gson gson;

    @Before
    public void setUp() {
        henkiloService = new HenkiloService();
        rest = new ExternalJarjestelmaController();
        mainTester = new MainRestTester(Catalogue.JARJESTELMA);
        gson = new Gson();
    }

    @After
    public void tearDown() throws Exception {
        mainTester.clear();
    }

    @Test
    public void filterDisabledPersonsFromExternalJarjContrTest() throws IOException, SQLException, JSONException {
        String firstName1 = "Test";
        String lastName1 = "Person";
        String firstName2 = "Removed";
        String lastName2 = "Person";

        List<Object[]> fullnameList = Arrays.asList(new Object[]{firstName1, lastName1}, new Object[]{firstName2, lastName2});
        List<Object[]> henkiloObjList = getHenkiloObjListByFullname(fullnameList);
        List<Henkilo> henkiloSaveList = initializeHenkiloSaveList(henkiloObjList);

        Henkilo testPerson = henkiloSaveList.get(0);
        Henkilo removedPerson = henkiloSaveList.get(1);
        removedPerson.setPoistunut(1);

        List<Henkilo> henkiloList = henkiloService.saveHenkiloList(Arrays.asList(testPerson, removedPerson));
        List<String> rooliNimiList = Arrays.asList("OMISTAJA", "VASTAAVA");
        List<Rooli> rooliList = createTestRooliList(rooliNimiList);

        createTestJarjestelmaWithHenkiloRooliList(rooliList, henkiloList);
        Response response = rest.getFilteredForSahke(-1, 0, "", "", "");
        JarjestelmaSahkeDto fetchedJarjestelma = responseToJarj(response, 0, 1);

        String vastaavaObjectId = fetchedJarjestelma.getJarjestelmavastaavaId();
        String omistajaObjectId = fetchedJarjestelma.getOmistajaId();
        assertEquals(omistajaObjectId, testPerson.getObjectID());
        assertEquals(vastaavaObjectId, null);
        assertEquals(fetchedJarjestelma.getJarjestelmaSijaistenIdt().size(), 0);
    }

    @Test
    public void filterModifiedAfterDateTest() throws IOException, JSONException, InterruptedException {
        createTestJarjAndUpdatePaasynhallinta("Some other Paasynhallinta");
        TimeUnit.SECONDS.sleep(1);
        String dateTimeNow = Utils.dateToUrlParam(new Date(Calendar.getInstance().getTime().getTime()));
        JarjestelmaDto updatedJarj = createTestJarjAndUpdatePaasynhallinta("Expected Paasynhallinta");

        Response response = rest.getFilteredForSahke(-1, 0, "", "", dateTimeNow);

        JarjestelmaSahkeDto fetchedJarjestelma = responseToJarj(response, 0, 1);
        assertEquals(fetchedJarjestelma.getTunnus(), updatedJarj.getTunnus());
        assertEquals(fetchedJarjestelma.getPaasynhallinta(), updatedJarj.getPaasynhallinta());
    }

    private JarjestelmaSahkeDto responseToJarj(Response response, Integer idx, Integer expectedSize) throws JSONException {
        JSONObject responseInJSON = (JSONObject) response.getEntity();
        JSONArray itemsArray = (JSONArray) responseInJSON.get("items");
        assertEquals((Integer) itemsArray.length(), expectedSize);
        JSONObject jsonObject = itemsArray.getJSONObject(idx);
        return gson.fromJson(jsonObject.toString(), new TypeToken<JarjestelmaSahkeDto>(){}.getType());
    }

    private JarjestelmaDto createTestJarjAndUpdatePaasynhallinta(String updatedPaasynhallinta) throws IOException {
        ContentDto jarj = new JarjestelmaDto();
        jarj.setNimi("Test Paasynhallinta");
        jarj = createJarjestelma(jarj);
        ((JarjestelmaDto) jarj).setPaasynhallinta(updatedPaasynhallinta);
        JarjestelmaDto updatedJarj = updateJarjestelma(jarj);
        assertEquals(updatedJarj.getPaasynhallinta(), updatedPaasynhallinta);
        return updatedJarj;
    }

}
