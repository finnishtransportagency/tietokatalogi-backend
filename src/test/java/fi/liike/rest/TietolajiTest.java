package fi.liike.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fi.liike.rest.Model.JoinTietolajiLooginen;
import fi.liike.rest.Model.JoinTietolajiLooginenHistory;
import fi.liike.rest.Model.JoinTietolajiTietoryhma;
import fi.liike.rest.Model.JoinTietolajiTietoryhmaHistory;
import fi.liike.rest.api.Catalogue;
import fi.liike.testutils.TestDbUtil;
import fi.liike.testutils.TestUtil;

public class TietolajiTest {

	private MainRestTester mainTester;

	@Before
	public void setUp() {
		mainTester = new MainRestTester(Catalogue.TIETOLAJI);
	}

	@After
	public void tearDown() {
		mainTester.clear();
	}

	// Common tests begin here.
	@Test
	public void testTietolajiPaging() throws JSONException {
		mainTester.testPaging(Catalogue.TIETOLAJI);
	}

	@Test
	public void testTietolajiGetEntry() throws JSONException {
		mainTester.testGetEntry(Catalogue.TIETOLAJI);
	}

	@Test
	public void testTietolajiCreateNew() throws JSONException, IOException {
		mainTester.testCreateNew(Catalogue.TIETOLAJI);
	}

	@Test
	public void TestTietolajiUpdate() throws JSONException, IOException {
		mainTester.testUpdate(Catalogue.TIETOLAJI);
	}

	@Test
	public void testTietolajiDelete() throws JSONException {
		mainTester.testDelete(Catalogue.TIETOLAJI);
	}

	@Test
	public void testTietolajiGetKasite() {
		TestDbUtil.writeToDb(TestUtil.createKasiteEntriesInJson(Catalogue.LOOGINEN));
		TestDbUtil.writeToDb(TestUtil.createKasiteEntriesInJson(Catalogue.TIETORYHMA));
		List<String> testWords = new ArrayList<String>();
		testWords.add(Catalogue.LOOGINEN.toString());
		testWords.add(Catalogue.TIETORYHMA.toString());
		mainTester.getKasite(Catalogue.TIETOLAJI, false, testWords);
	}

	@Test
	public void testCreateLooginenLink() throws IOException {
		mainTester.testCreateLink(Catalogue.TIETOLAJI, "looginenTietovarantoTunnus",
				JoinTietolajiLooginenHistory.class);
	}

	@Test
	public void testUpdateLooginenLink() throws IOException {
		mainTester.testUpdateParentLink(Catalogue.TIETOLAJI, "looginenTietovarantoTunnus",
				JoinTietolajiLooginenHistory.class);
	}

	@Test
	public void testDeleteLooginenLink() throws IOException {
		mainTester.deleteParentLink(Catalogue.TIETOLAJI, "looginenTietovarantoTunnus",
				JoinTietolajiLooginenHistory.class, JoinTietolajiLooginen.class);

	}

	@Test
	public void testCreateTietoryhmaLink() throws IOException {
		mainTester.testCreateLink(Catalogue.TIETOLAJI, "tietoryhmatunnus",
				JoinTietolajiTietoryhmaHistory.class);
	}

	@Test
	public void testUpdateTietoryhmaLink() throws IOException {
		mainTester.testUpdateParentLink(Catalogue.TIETOLAJI, "tietoryhmatunnus",
				JoinTietolajiTietoryhmaHistory.class);
	}

	@Test
	public void testDeleteTietoryhmaLink() throws IOException {
		mainTester.deleteParentLink(Catalogue.TIETOLAJI, "tietoryhmatunnus", JoinTietolajiTietoryhmaHistory.class,
				JoinTietolajiTietoryhma.class);

	}
	// Common tests end here.
}
