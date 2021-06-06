package fi.liike.testutils;

import static org.junit.Assert.assertEquals;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestTestUtil {
	private final Logger LOG = LoggerFactory.getLogger(TestTestUtil.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetJsonArray() throws JSONException {
		JSONArray salkku = TestUtil.getJsonArray("src/test/resources/test-material/tietojarjestelmasalkku1.json");
		assertEquals(4, salkku.length());
		for (int i = 0; i < salkku.length(); i++) {
			JSONObject jarjestelma = salkku.getJSONObject(i);
			assertEquals("tietojarjestelmasalkku", jarjestelma.get("nimi"));
		}
	}

	@Test
	public void testGetJsonObject() {
		JSONObject json = TestUtil.getJsonObject("src/test/resources/test-material/tietojarjestelmasalkku1.json", 1);
		String taulunNimi = null;
		try {
			taulunNimi = (String) json.get("nimi");
		} catch (JSONException e) {
			LOG.error("There was an error: " + e.getMessage());
		}
		assertEquals("tietojarjestelmasalkku", taulunNimi);
	}

	@Test
	public void testJsonToSql() {
		JSONObject json = TestUtil.getJsonObject("src/test/resources/test-material/tietojarjestelmasalkku2.json", 1);
		String sql = TestUtil.jsonToSql(json);
		assertEquals(
				"insert into tietojarjestelmasalkku (TIETOJARJESTELMATUNNUS, JARJESTELMAN_NIMI, KUVAUS_MAARITELMA, ELINKAARITILA, JARJESTELMAVASTAAVA_LIVI, RIVIMUOKKAAJATUNNUS, DOCUMENT_ID, YHTEISK_KRIIT_JARJ, JARJESTELMAALUE) values (164, 'Digiroad 2: Väyläverkonhallintasovellus', 'Digiroad2 ?järjestelmän VVH-sovellus ylläpitää tie- ja katuverkkoa linkkisolmumallin mukaisessa tietomallissa. Sovelluksen avulla voidaan muokata tieverkkoa, lisätä uusia tieosuuksia, linkkejä eli risteyksiä sekä ylläpitää linkkien ja solmujen geometriaa. Järjestelmä tarjoaa ajantasaisen tieverkon tiedot muille järjestelmille. VVH-sovellus hallinnoi ja ylläpitää tieverkkoon liittyviä paikkatietoja. Jatkossa VVH-sovelluksen alle siirretään mahdollisesti myös tieosoiteverkon mukaiset tiedot (Tieosoiteviitekehys)', 'Kehityksessä', 'Matti Testi1', 'K255741', '3d71ae308272a6f894db7e2f75aa33b91984d252', 'ei', 'VTJ -Tie')",
				sql);
		JSONObject json2 = TestUtil.getJsonObject("src/test/resources/test-material/parametrit1.json", 1);
		String sql2 = TestUtil.jsonToSql(json2);
		assertEquals(
				"insert into parametrit (AVAIN, ARVO, RIVIMUOKKAAJATUNNUS, KUVAUS, OTSIKKO) values ('jarjestelma', 'https://extranet.liikennevirasto.fi/forms/fr/Tietokatalogi/jarjestelmatieto_yllapito', 'solita', 'Lomake tietojen muokkaamiseen.', 'Jarjestelmatieto')",
				sql2);

	}
}
