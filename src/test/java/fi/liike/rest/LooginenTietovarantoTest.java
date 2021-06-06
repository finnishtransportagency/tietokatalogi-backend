package fi.liike.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fi.liike.rest.Model.JoinLooginenFyysinen;
import fi.liike.rest.Model.JoinLooginenFyysinenHistory;
import fi.liike.rest.api.Catalogue;
import fi.liike.testutils.TestDbUtil;
import fi.liike.testutils.TestUtil;

public class LooginenTietovarantoTest {

	private MainRestTester mainTester;

	@Before
	public void setUp() {
		mainTester = new MainRestTester(Catalogue.LOOGINEN);
	}

	@After
	public void tearDown() {
		mainTester.clear();
	}

	// Common tests begin here.
	@Test
	public void testLooginenPaging() throws JSONException {
		mainTester.testPaging(Catalogue.LOOGINEN);
	}

	@Test
	public void testLooginenGetEntry() throws JSONException {
		mainTester.testGetEntry(Catalogue.LOOGINEN);
	}

	@Test
	public void testLooginenCreateNew() throws JSONException, IOException {
		mainTester.testCreateNew(Catalogue.LOOGINEN);
	}

	@Test
	public void TestLooginenUpdate() throws JSONException, IOException {
		mainTester.testUpdate(Catalogue.LOOGINEN);
	}

	@Test
	public void testLooginenDelete() throws JSONException {
		mainTester.testDelete(Catalogue.LOOGINEN);
	}

	@Test
	public void testLooginenGetKasite() {
		TestDbUtil.writeToDb(TestUtil.createKasiteEntriesInJson(Catalogue.FYYSINEN));
		List<String> testWords = new ArrayList<String>();
		testWords.add(Catalogue.FYYSINEN.toString());
		mainTester.getKasite(Catalogue.LOOGINEN, false, testWords);
	}

	@Test
	public void testLooginenCreateFyysinenLink() throws IOException {
		mainTester.testCreateLink(Catalogue.LOOGINEN, "fyysinenTietovarantoId", JoinLooginenFyysinenHistory.class);
	}

	@Test
	public void testUpdateFyysinenLink() throws IOException {
		mainTester.testUpdateParentLink(Catalogue.LOOGINEN, "fyysinenTietovarantoId",
				JoinLooginenFyysinenHistory.class);
	}

	@Test
	public void testDeleteFyysinenLink() throws IOException {
		mainTester.deleteParentLink(Catalogue.LOOGINEN, "fyysinenTietovarantoId",
				JoinLooginenFyysinenHistory.class, JoinLooginenFyysinen.class);
		
	}
	// Common tests end here.

}
