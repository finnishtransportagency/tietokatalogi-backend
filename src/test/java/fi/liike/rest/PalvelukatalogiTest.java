package fi.liike.rest;

import static org.junit.Assert.assertEquals;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fi.liike.rest.Controller.PalveluController;
import fi.liike.rest.api.Catalogue;
import fi.liike.testutils.TestDbUtil;
import fi.liike.testutils.TestUtil;

import java.io.IOException;

public class PalvelukatalogiTest {

	private PalveluController rest;
	private MainRestTester mainTester;

	@Before
	public void setUp() {
		rest = new PalveluController();
		mainTester = new MainRestTester(Catalogue.PALVELU);
	}

	@After
	public void tearDown() {
		mainTester.clear();
	}

	// Common tests begin here.
	@Test
	public void testPalveluPaging() throws JSONException {
		mainTester.testPaging(Catalogue.PALVELU);
	}

	@Test
	public void testPalveluGetEntry() throws JSONException {
		mainTester.testGetEntry(Catalogue.PALVELU);
	}

	@Test
	public void testPalveluCreateNew() throws JSONException, IOException {
		mainTester.testCreateNew(Catalogue.PALVELU);
	}

	@Test
	public void TestPalveluUpdate() throws JSONException, IOException {
		mainTester.testUpdate(Catalogue.PALVELU);
	}

	@Test
	public void testPalveluDelete() throws JSONException {
		mainTester.testDelete(Catalogue.PALVELU);
	}

	@Test
	public void testPalveluGetKasite() {
		mainTester.getKasite(Catalogue.PALVELU, true, null);
	}
	// Common tests end here.

	@Test
	public void testFilter() throws ParseException, JSONException {
		createTestData();

		ExtractedResponse response = new ExtractedResponse(
				rest.getAll("100", "0", "ICT", "asc", null, null));
		JSONArray items = response.getItems();
		assertEquals(1, items.length());
		JSONObject item = items.getJSONObject(0);
		assertEquals("ICT-järjestelmäpäällikköpalvelu", item.get("nimi"));

		response = new ExtractedResponse(rest.getAll("100", "0", "PÄÄLLIKKÖ", "asc", null, null));
		items = response.getItems();
		assertEquals(2, items.length());
		item = items.getJSONObject(0);
		assertEquals(2, response.getValue("count"));

		response = new ExtractedResponse(rest.getAll("100", "0", "zzz", "desc", null, null));
		assertEquals(0, response.getItems().length());
		assertEquals(0, response.getValue("count"));
	}

	private void createTestData() {
		JSONArray testData = TestUtil.getJsonArray("src/test/resources/test-material/palvelukatalogi1.json");
		TestDbUtil.writeDb(testData);
	}

}
