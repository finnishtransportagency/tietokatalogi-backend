package fi.liike.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fi.liike.rest.Controller.JarjestelmaController;
import fi.liike.rest.Controller.MainController;
import fi.liike.rest.Controller.TietojarjestelmapalveluController;
import fi.liike.rest.api.Catalogue;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.AnnotatedTietolajiDto;
import fi.liike.rest.api.dto.JarjestelmaDto;
import fi.liike.rest.api.dto.JarjestelmaLinkkausDto;
import fi.liike.rest.api.dto.TietojarjestelmapalveluDto;
import fi.liike.testutils.TestDbUtil;
import fi.liike.testutils.TestRequest;
import fi.liike.testutils.TestUtil;
import org.codehaus.jettison.json.JSONException;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Collections;

public class TietojarjestelmapalveluTest {
    private JarjestelmaController jarjestelmaController;
    private TietojarjestelmapalveluController tietojarjestelmapalveluController;
    private MainRestTester mainTester;
    private static Gson gson;

    @Before
    public void setUp() {
        jarjestelmaController = new JarjestelmaController();
        tietojarjestelmapalveluController = new TietojarjestelmapalveluController();
        gson = new Gson();
        mainTester = new MainRestTester(Catalogue.TIETOJARJESTELMAPALVELU);
    }

    @After
    public void tearDown() {
        mainTester.clear();
    }

    @Test
    public void testTietojarjestelmapalveluCreateNew() throws IOException {
        mainTester.testCreateNew(Catalogue.TIETOJARJESTELMAPALVELU);
    }

    @Test
    public void TestTietojarjestelmapalveluUpdate() throws JSONException, IOException {
        mainTester.testUpdate(Catalogue.TIETOJARJESTELMAPALVELU);
    }

    @Test
    public void testTietojarjestelmapalveluDelete() {
        mainTester.testDelete(Catalogue.TIETOJARJESTELMAPALVELU);
    }

    @Test
    public void testDeleteSetsNullInJarjestelmaLinkkaus() {
        TestDbUtil.writeToDb(TestUtil.createEntriesInJson(Catalogue.TIETOJARJESTELMAPALVELU, 1));
        JarjestelmaDto jDto1 = new JarjestelmaDto();
        jDto1.setNimi("j1");
        JarjestelmaDto jDto2 = new JarjestelmaDto();
        jDto2.setNimi("j2");
        createJarjestelma(jDto1);
        createJarjestelma(jDto2);

        JarjestelmaLinkkausDto link = new JarjestelmaLinkkausDto();
        link.setTietojarjestelmaTunnus(1);
        link.setLinkattavaTunnus(2);
        link.setTyyppi("Järjestelmä");
        link.setSuunta("Kirjoitus");
        link.setTietojarjestelmapalveluTunnus(1);
        JarjestelmaDto j1 = getJarjestelma(1);
        j1.setJarjestelmaLinkkausList(Collections.singletonList(link));
        j1 = updateJarjestelma(j1);
        assertEquals(1, j1.getJarjestelmaLinkkausList().get(0).getTietojarjestelmapalveluTunnus().intValue());

        MainController rest = TestUtil.getRest(Catalogue.TIETOJARJESTELMAPALVELU);
        assert rest != null;
        rest.delete(new TestRequest(), "1");

        JarjestelmaDto updatedJarjestelma = getJarjestelma(1);
        assertNull(updatedJarjestelma.getJarjestelmaLinkkausList().get(0).getTietojarjestelmapalveluTunnus());
    }

    @Test
    public void testUpdateTietolajiSetsNullInJarjestelmaLinkkaus() throws IOException {
        TestDbUtil.writeToDb(TestUtil.createEntriesInJson(Catalogue.TIETOLAJI, 1));
        TietojarjestelmapalveluDto firstEntry = (TietojarjestelmapalveluDto) TestUtil.createEntry(Catalogue.TIETOJARJESTELMAPALVELU);
        Integer tietolajiId = 1;
        AnnotatedTietolajiDto tietolajiDto = new AnnotatedTietolajiDto();
        tietolajiDto.setTunnus(tietolajiId);
        firstEntry.setTietolajit(Collections.singleton(tietolajiDto));
        TietojarjestelmapalveluDto createdTjp = createTietojarjestelmapalvelu(firstEntry);

        // create jarjestelma link
        JarjestelmaDto jDto1 = new JarjestelmaDto();
        jDto1.setNimi("j1");
        JarjestelmaDto jDto2 = new JarjestelmaDto();
        jDto2.setNimi("j2");
        createJarjestelma(jDto1);
        createJarjestelma(jDto2);

        JarjestelmaLinkkausDto link = new JarjestelmaLinkkausDto();
        link.setTietojarjestelmaTunnus(1);
        link.setLinkattavaTunnus(2);
        link.setTyyppi("Järjestelmä");
        link.setSuunta("Kirjoitus");
        link.setTietojarjestelmapalveluTunnus(1);
        link.setTietovirta(tietolajiId.toString()); // set tietolaji to jarjestelma link
        JarjestelmaDto j1 = getJarjestelma(1);
        j1.setJarjestelmaLinkkausList(Collections.singletonList(link));
        updateJarjestelma(j1);

        // delete tietolaji from tjp
        createdTjp.setTietolajit(Collections.<AnnotatedTietolajiDto>emptySet());
        tietojarjestelmapalveluController.update(new TestRequest(), createdTjp);

        // fetch jarjestelma link and assert
        JarjestelmaDto updatedJ1 = getJarjestelma(1);
        assertNull(updatedJ1.getJarjestelmaLinkkausList().get(0).getTietovirta());
    }

    private JarjestelmaDto getJarjestelma(Integer id) {
        ExtractedResponse response = new ExtractedResponse(jarjestelmaController.get(new TestRequest(), id.toString()));
        Response resp = response.getResponse();
        return gson.fromJson(resp.getEntity().toString(), new TypeToken<JarjestelmaDto>() {
        }.getType());
    }

    private JarjestelmaDto updateJarjestelma(ContentDto jarjUpdate) {
        ExtractedResponse response = new ExtractedResponse(jarjestelmaController.update(new TestRequest(), jarjUpdate));
        Response resp = response.getResponse();
        return gson.fromJson(resp.getEntity().toString(), new TypeToken<JarjestelmaDto>() {
        }.getType());
    }

    private JarjestelmaDto createJarjestelma(ContentDto jarjUpdate) {
        ExtractedResponse response = new ExtractedResponse(jarjestelmaController.create(new TestRequest(), jarjUpdate));
        Response resp = response.getResponse();
        return gson.fromJson(resp.getEntity().toString(), new TypeToken<JarjestelmaDto>() {}.getType());
    }

    private TietojarjestelmapalveluDto createTietojarjestelmapalvelu(ContentDto tjp) throws IOException {
        ExtractedResponse response = new ExtractedResponse(tietojarjestelmapalveluController.create(new TestRequest(), tjp));
        Response resp = response.getResponse();
        return gson.fromJson(resp.getEntity().toString(), new TypeToken<TietojarjestelmapalveluDto>() {}.getType());
    }
}
