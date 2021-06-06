package fi.liike.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fi.liike.rest.Model.JoinTietoryhmaPaatietoryhma;
import fi.liike.rest.Model.JoinTietoryhmaPaatietoryhmaHistory;
import fi.liike.rest.api.Catalogue;
import fi.liike.testutils.TestDbUtil;
import fi.liike.testutils.TestUtil;

public class TietoryhmaTest {

	private MainRestTester mainTester;

	@Before
	public void setUp() {
		mainTester = new MainRestTester(Catalogue.TIETORYHMA);
	}

	@After
	public void tearDown() {
		mainTester.clear();
	}

	// Common tests begin here.
	@Test
	public void testTietoryhmaPaging() throws JSONException {
		mainTester.testPaging(Catalogue.TIETORYHMA);
	}

	@Test
	public void testTietoryhmaGetEntry() throws JSONException {
		mainTester.testGetEntry(Catalogue.TIETORYHMA);
	}

	@Test
	public void testTietoryhmaCreateNew() throws JSONException, IOException {
		mainTester.testCreateNew(Catalogue.TIETORYHMA);
	}

	@Test
	public void TestTietoryhmaUpdate() throws JSONException, IOException {
		mainTester.testUpdate(Catalogue.TIETORYHMA);
	}

	@Test
	public void testTietoryhmaDelete() throws JSONException {
		mainTester.testDelete(Catalogue.TIETORYHMA);
	}

	@Test
	public void testTietoryhmaGetKasite() {
		TestDbUtil.writeToDb(TestUtil.createKasiteEntriesInJson(Catalogue.PAATIETORYHMA));
		List<String> testWords = new ArrayList<String>();
		testWords.add(Catalogue.PAATIETORYHMA.toString());
		mainTester.getKasite(Catalogue.TIETORYHMA, false, testWords);
	}

	@Test
	public void testCreatePaatietoryhmaLink() throws IOException {
		mainTester.testCreateLink(Catalogue.TIETORYHMA, "paatietoryhma", JoinTietoryhmaPaatietoryhmaHistory.class);
	}

	@Test
	public void testUpdatePaatietoryhmaLink() throws IOException {
		mainTester.testUpdateParentLink(Catalogue.TIETORYHMA, "paatietoryhma",
				JoinTietoryhmaPaatietoryhmaHistory.class);
	}

	@Test
	public void testDeletePaatietoryhmaLink() throws IOException {
		mainTester.deleteParentLink(Catalogue.TIETORYHMA, "paatietoryhma", JoinTietoryhmaPaatietoryhmaHistory.class,
				JoinTietoryhmaPaatietoryhma.class);

	}
	// Common tests end here.
}
