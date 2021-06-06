package fi.liike.rest;

import com.google.common.collect.ImmutableMap;
import fi.liike.rest.Model.Dto.MolekyyliLinkkiDto;
import fi.liike.rest.Service.MolekyyliLinkkiService;
import fi.liike.rest.api.Catalogue;
import fi.liike.testutils.TestDbUtil;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static fi.liike.testutils.TestUtil.*;
import static org.junit.Assert.assertEquals;

public class MolekyyliLinkkiTest {
    private static String paatietoryhmaName;
    private static String tietoryhmaName1;
    private static String tietoryhmaName2;
    private static String looginenName;
    private static String tietolajiName1;
    private static String tietolajiName2;
    private static String fyysinenName;
    private static Map<String, Integer> mapNameToTunnus;
    private static Map<String, String> mapNameToContent;
    private static Map<String, MolekyyliLinkkiDto.MolekyyliKasite> mapNameToMolekyyliKasite;

    private static final Logger LOG = LoggerFactory.getLogger(MolekyyliLinkkiTest.class);

    private MolekyyliLinkkiService rest;
    private MainRestTester mainTester;

    @Before
    public void setUp() throws Exception {
        rest = new MolekyyliLinkkiService();
        mainTester = new MainRestTester(Catalogue.MOLEKYYLILINKKI);
        setUpLinks();
        setUpContentMap();
        setUpMolekyyliKasiteMap();
        LOG.info("\nending setUp()\n");
    }

    @After
    public void tearDown() throws Exception {
        LOG.info("\nstarting tearDown()\n");
        mainTester.clear();
    }

    private void setUpContentMap() {
        mapNameToContent = new HashMap<String, String>();
        mapNameToContent.put(paatietoryhmaName, "paatietoryhma");
        mapNameToContent.put(tietoryhmaName1, "tietoryhma");
        mapNameToContent.put(tietoryhmaName2, "tietoryhma");
        mapNameToContent.put(tietolajiName1, "tietolaji");
        mapNameToContent.put(tietolajiName2, "tietolaji");
        mapNameToContent.put(looginenName, "looginen");
        mapNameToContent.put(fyysinenName, "fyysinen");

    }

    private void setUpMolekyyliKasiteMap() {
        mapNameToMolekyyliKasite = new HashMap<String, MolekyyliLinkkiDto.MolekyyliKasite>();
        for (String molekyyliName : mapNameToTunnus.keySet()) {
            mapNameToMolekyyliKasite.put(molekyyliName, getMolekyyliKasiteByName(molekyyliName));
        }
    }

    private MolekyyliLinkkiDto.MolekyyliKasite getMolekyyliKasiteByName(String name) {
        return new MolekyyliLinkkiDto.MolekyyliKasite(name, mapNameToContent.get(name), mapNameToTunnus.get(name));
    }

    private void setUpLinks() throws JSONException {
        paatietoryhmaName = "Päätietoryhmä";
        tietoryhmaName1 = "Tietoryhmä1";
        tietoryhmaName2 = "Tietoryhmä2";
        looginenName = "Looginen";
        tietolajiName1 = "Tietolaji1";
        tietolajiName2 = "Tietolaji2";
        fyysinenName = "Fyysinen";

        Integer currTunnus = 1;

        mapNameToTunnus = new HashMap<String, Integer>();
        mapNameToTunnus.put(paatietoryhmaName, currTunnus++);
        mapNameToTunnus.put(tietoryhmaName1, currTunnus++);
        mapNameToTunnus.put(tietoryhmaName2, currTunnus++);
        mapNameToTunnus.put(tietolajiName1, currTunnus++);
        mapNameToTunnus.put(tietolajiName2, currTunnus++);
        mapNameToTunnus.put(looginenName, currTunnus++);
        mapNameToTunnus.put(fyysinenName, currTunnus);

        JSONArray dbJsonArray = new JSONArray();

        JSONObject paatietoryhma = createEntry("PAATIETORYHMA",
                ImmutableMap.of(
                        "PAATIETORYHMATUNNUS", mapNameToTunnus.get(paatietoryhmaName),
                        "NIMI", (Object) paatietoryhmaName,
                        "RIVITUNNUS", TestDbUtil.getNextSequenceVal("PAATIETORYHMA_ID_SEQ")));
        dbJsonArray.put(paatietoryhma);

        JSONArray tietoryhma1 = createEntryWithJoin("TIETORYHMA",
                ImmutableMap.of(
                        "NIMI", (Object) tietoryhmaName1,
                        "TIETORYHMATUNNUS", mapNameToTunnus.get(tietoryhmaName1),
                        "PAATIETORYHMATUNNUS", mapNameToTunnus.get(paatietoryhmaName),
                        "RIVITUNNUS", TestDbUtil.getNextSequenceVal("TIETORYHMA_ID_SEQ")));
        putAll(dbJsonArray, tietoryhma1);

        JSONArray tietoryhma2 = createEntryWithJoin("TIETORYHMA",
                ImmutableMap.of(
                        "NIMI", (Object) tietoryhmaName2,
                        "TIETORYHMATUNNUS", mapNameToTunnus.get(tietoryhmaName2),
                        "PAATIETORYHMATUNNUS", mapNameToTunnus.get(paatietoryhmaName),
                        "RIVITUNNUS", TestDbUtil.getNextSequenceVal("TIETORYHMA_ID_SEQ")));
        putAll(dbJsonArray, tietoryhma2);

        JSONObject looginen = createEntry("LOOGINENTIETOVARANTO",
                ImmutableMap.of(
                        "NIMI", (Object) looginenName,
                        "LOOGINENTIETOVARANTOTUNNUS", mapNameToTunnus.get(looginenName)));
        dbJsonArray.put(looginen);

        JSONArray tietolaji1 = createEntryWithJoin("TIETO",
                ImmutableMap.of(
                        "NIMI", (Object) tietolajiName1,
                        "TIETOTUNNUS", mapNameToTunnus.get(tietolajiName1),
                        "TIETORYHMATUNNUS", mapNameToTunnus.get(tietoryhmaName1),
                        "RIVITUNNUS", TestDbUtil.getNextSequenceVal("tietolaji_id_seq"),
                        "LOOGINENTIETOVARANTOTUNNUS", mapNameToTunnus.get(looginenName)));
        putAll(dbJsonArray, tietolaji1);

        JSONArray tietolaji2 = createEntryWithJoin("TIETO",
                ImmutableMap.of(
                        "NIMI", (Object) tietolajiName2,
                        "TIETOTUNNUS", mapNameToTunnus.get(tietolajiName2),
                        "TIETORYHMATUNNUS", mapNameToTunnus.get(tietoryhmaName1),
                        "LOOGINENTIETOVARANTOTUNNUS", mapNameToTunnus.get(looginenName),
                        "RIVITUNNUS", TestDbUtil.getNextSequenceVal("tietolaji_id_seq")));
        putAll(dbJsonArray, tietolaji2);

        JSONObject fyysinen = createEntry("FYYSINENTIETOVARANTO",
                ImmutableMap.of(
                        "NIMI", (Object) fyysinenName,
                        "FYYSINENTIETOVARANTOTUNNUS", mapNameToTunnus.get(fyysinenName)));
        dbJsonArray.put(fyysinen);

        JSONObject joinFyysinenLooginen = createEntry("LOOGINENTIETOVARANTOFYYSINENTI",
                ImmutableMap.of(
                        "FYYSINENTIETOVARANTOTUNNUS", (Object) mapNameToTunnus.get(fyysinenName),
                        "LOOGINENTIETOVARANTOTUNNUS", mapNameToTunnus.get(looginenName),
                        "RIVITILA", "A",
                        "RIVITUNNUS", TestDbUtil.getNextSequenceVal("joinlooginenfyysinen_id_seq")));
        dbJsonArray.put(joinFyysinenLooginen);

        TestDbUtil.writeToDb(dbJsonArray);
    }

    private JSONArray putAll(JSONArray original, JSONArray other) throws JSONException {
        for (int i = 0; i < other.length(); i++) {
            original.put(other.getJSONObject(i));
        }
        return original;
    }

    @Test
    public void testLinksPaatietoryhma() {
        ExtractedResponse response = new ExtractedResponse(
                rest.haePaatietoryhmaTunnuksella(mapNameToTunnus.get(paatietoryhmaName).toString(), ""));

        MolekyyliLinkkiDto fetchedMolekyyliLinkkiDto = (MolekyyliLinkkiDto) response.getResponse().getEntity();

        //Order is relevant
        List<MolekyyliLinkkiDto.MolekyyliKasite> expectedNodes = Arrays.asList(
                mapNameToMolekyyliKasite.get(tietoryhmaName1),
                mapNameToMolekyyliKasite.get(paatietoryhmaName),
                mapNameToMolekyyliKasite.get(tietoryhmaName2),
                mapNameToMolekyyliKasite.get(tietolajiName1),
                mapNameToMolekyyliKasite.get(tietolajiName2));
        //Order is relevant
        List<MolekyyliLinkkiDto.MolekyyliLinkitys> expectedMolekyyliLinkitysList = Arrays.asList(
                getMolekyyliLinkitys(0, 1),
                getMolekyyliLinkitys(2, 1),
                getMolekyyliLinkitys(3, 0),
                getMolekyyliLinkitys(4, 0));

        MolekyyliLinkkiDto expectedMolekyyliLinkkiDto = new MolekyyliLinkkiDto();
        expectedMolekyyliLinkkiDto.setNodes(expectedNodes);
        expectedMolekyyliLinkkiDto.setLinks(expectedMolekyyliLinkitysList);

        assertEquals(fetchedMolekyyliLinkkiDto, expectedMolekyyliLinkkiDto);
    }

    @Test
    public void testLinksTietoryhma1() {
        ExtractedResponse response = new ExtractedResponse(
                rest.haeTietoryhmaTunnuksella(mapNameToTunnus.get(tietoryhmaName1).toString(), ""));

        MolekyyliLinkkiDto fetchedMolekyyliLinkkiDto = (MolekyyliLinkkiDto) response.getResponse().getEntity();

        //Order is relevant
        List<MolekyyliLinkkiDto.MolekyyliKasite> expectedNodes = Arrays.asList(
                mapNameToMolekyyliKasite.get(tietoryhmaName1),
                mapNameToMolekyyliKasite.get(paatietoryhmaName),
                mapNameToMolekyyliKasite.get(tietolajiName1),
                mapNameToMolekyyliKasite.get(tietolajiName2),
                mapNameToMolekyyliKasite.get(looginenName));
        //Order is relevant
        List<MolekyyliLinkkiDto.MolekyyliLinkitys> expectedMolekyyliLinkitysList = Arrays.asList(
                getMolekyyliLinkitys(0, 1),
                getMolekyyliLinkitys(2, 0),
                getMolekyyliLinkitys(3, 0),
                getMolekyyliLinkitys(2, 4),
                getMolekyyliLinkitys(3, 4));

        MolekyyliLinkkiDto expectedMolekyyliLinkkiDto = new MolekyyliLinkkiDto();
        expectedMolekyyliLinkkiDto.setNodes(expectedNodes);
        expectedMolekyyliLinkkiDto.setLinks(expectedMolekyyliLinkitysList);

        assertEquals(fetchedMolekyyliLinkkiDto, expectedMolekyyliLinkkiDto);
    }

    @Test
    public void testLinksTietoryhma2() {
        ExtractedResponse response = new ExtractedResponse(
                rest.haeTietoryhmaTunnuksella(mapNameToTunnus.get(tietoryhmaName2).toString(), ""));

        MolekyyliLinkkiDto fetchedMolekyyliLinkkiDto = (MolekyyliLinkkiDto) response.getResponse().getEntity();

        //Order is relevant
        List<MolekyyliLinkkiDto.MolekyyliKasite> expectedNodes = Arrays.asList(
                mapNameToMolekyyliKasite.get(tietoryhmaName2),
                mapNameToMolekyyliKasite.get(paatietoryhmaName));
        //Order is relevant
        List<MolekyyliLinkkiDto.MolekyyliLinkitys> expectedMolekyyliLinkitysList = Arrays.asList(
                getMolekyyliLinkitys(0, 1));

        MolekyyliLinkkiDto expectedMolekyyliLinkkiDto = new MolekyyliLinkkiDto();
        expectedMolekyyliLinkkiDto.setNodes(expectedNodes);
        expectedMolekyyliLinkkiDto.setLinks(expectedMolekyyliLinkitysList);

        assertEquals(fetchedMolekyyliLinkkiDto, expectedMolekyyliLinkkiDto);
    }

    @Test
    public void testLinksTietolaji() {
        ExtractedResponse response = new ExtractedResponse(
                rest.haeTietolajiTunnuksella(mapNameToTunnus.get(tietolajiName1).toString(), ""));

        MolekyyliLinkkiDto fetchedMolekyyliLinkkiDto = (MolekyyliLinkkiDto) response.getResponse().getEntity();

        //Order is relevant
        List<MolekyyliLinkkiDto.MolekyyliKasite> expectedNodes = Arrays.asList(
                mapNameToMolekyyliKasite.get(tietolajiName1),
                mapNameToMolekyyliKasite.get(tietoryhmaName1),
                mapNameToMolekyyliKasite.get(looginenName),
                mapNameToMolekyyliKasite.get(paatietoryhmaName),
                mapNameToMolekyyliKasite.get(fyysinenName));
        //Order is relevant
        List<MolekyyliLinkkiDto.MolekyyliLinkitys> expectedMolekyyliLinkitysList = Arrays.asList(
                getMolekyyliLinkitys(0, 1),
                getMolekyyliLinkitys(0, 2),
                getMolekyyliLinkitys(1, 3),
                getMolekyyliLinkitys(2, 4));


        MolekyyliLinkkiDto expectedMolekyyliLinkkiDto = new MolekyyliLinkkiDto();
        expectedMolekyyliLinkkiDto.setNodes(expectedNodes);
        expectedMolekyyliLinkkiDto.setLinks(expectedMolekyyliLinkitysList);

        assertEquals(fetchedMolekyyliLinkkiDto, expectedMolekyyliLinkkiDto);
    }

    @Test
    public void testLinksLooginen() {
        ExtractedResponse response = new ExtractedResponse(
                rest.haeLooginenTietovarantoTunnuksella(mapNameToTunnus.get(looginenName).toString(), ""));

        MolekyyliLinkkiDto fetchedMolekyyliLinkkiDto = (MolekyyliLinkkiDto) response.getResponse().getEntity();

        //Order is relevant
        List<MolekyyliLinkkiDto.MolekyyliKasite> expectedNodes = Arrays.asList(
                mapNameToMolekyyliKasite.get(tietolajiName1),
                mapNameToMolekyyliKasite.get(looginenName),
                mapNameToMolekyyliKasite.get(tietolajiName2),
                mapNameToMolekyyliKasite.get(fyysinenName),
                mapNameToMolekyyliKasite.get(tietoryhmaName1));
        //Order is relevant
        List<MolekyyliLinkkiDto.MolekyyliLinkitys> expectedMolekyyliLinkitysList = Arrays.asList(
                getMolekyyliLinkitys(0, 1),
                getMolekyyliLinkitys(2, 1),
                getMolekyyliLinkitys(1, 3),
                getMolekyyliLinkitys(0, 4),
                getMolekyyliLinkitys(2, 4));


        MolekyyliLinkkiDto expectedMolekyyliLinkkiDto = new MolekyyliLinkkiDto();
        expectedMolekyyliLinkkiDto.setNodes(expectedNodes);
        expectedMolekyyliLinkkiDto.setLinks(expectedMolekyyliLinkitysList);

        assertEquals(fetchedMolekyyliLinkkiDto, expectedMolekyyliLinkkiDto);
    }

    @Test
    public void testLinksFyysinen() {
        ExtractedResponse response = new ExtractedResponse(
                rest.haefyysinenTietovarantoTunnuksella(mapNameToTunnus.get(fyysinenName).toString(), ""));

        MolekyyliLinkkiDto fetchedMolekyyliLinkkiDto = (MolekyyliLinkkiDto) response.getResponse().getEntity();

        //Order is relevant
        List<MolekyyliLinkkiDto.MolekyyliKasite> expectedNodes = Arrays.asList(
                mapNameToMolekyyliKasite.get(looginenName),
                mapNameToMolekyyliKasite.get(fyysinenName),
                mapNameToMolekyyliKasite.get(tietolajiName1),
                mapNameToMolekyyliKasite.get(tietolajiName2),
                mapNameToMolekyyliKasite.get(tietoryhmaName1),
                mapNameToMolekyyliKasite.get(paatietoryhmaName));
        //Order is relevant
        List<MolekyyliLinkkiDto.MolekyyliLinkitys> expectedMolekyyliLinkitysList = Arrays.asList(
                getMolekyyliLinkitys(0, 1),
                getMolekyyliLinkitys(2, 0),
                getMolekyyliLinkitys(3, 0),
                getMolekyyliLinkitys(2, 4),
                getMolekyyliLinkitys(4, 5),
                getMolekyyliLinkitys(3, 4),
                getMolekyyliLinkitys(4, 5));

        MolekyyliLinkkiDto expectedMolekyyliLinkkiDto = new MolekyyliLinkkiDto();
        expectedMolekyyliLinkkiDto.setNodes(expectedNodes);
        expectedMolekyyliLinkkiDto.setLinks(expectedMolekyyliLinkitysList);

        //TODO fix
        assertEquals(fetchedMolekyyliLinkkiDto, expectedMolekyyliLinkkiDto);
    }

    private MolekyyliLinkkiDto.MolekyyliLinkitys getMolekyyliLinkitys(long s, long t) {
        return new MolekyyliLinkkiDto.MolekyyliLinkitys(s, t);
    }
}
