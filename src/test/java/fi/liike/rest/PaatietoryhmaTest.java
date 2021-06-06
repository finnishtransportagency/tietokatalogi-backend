package fi.liike.rest;

import org.codehaus.jettison.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fi.liike.rest.api.Catalogue;

import java.io.IOException;

public class PaatietoryhmaTest {

	private MainRestTester mainTester;

	@Before
	public void setUp() {
		mainTester = new MainRestTester(Catalogue.PAATIETORYHMA);
	}

	@After
	public void tearDown() {
		mainTester.clear();
	}

	// Common tests begin here.
	@Test
	public void testPaatietoryhmaPaging() throws JSONException {
		mainTester.testPaging(Catalogue.PAATIETORYHMA);
	}

	@Test
	public void testPaatietoryhmaGetEntry() throws JSONException {
		mainTester.testGetEntry(Catalogue.PAATIETORYHMA);
	}

	@Test
	public void testPaatietoryhmaCreateNew() throws JSONException, IOException {
		mainTester.testCreateNew(Catalogue.PAATIETORYHMA);
	}

	@Test
	public void TestPaatietoryhmaUpdate() throws JSONException, IOException {
		mainTester.testUpdate(Catalogue.PAATIETORYHMA);
	}

	@Test
	public void testPaatietoryhmaDelete() throws JSONException {
		mainTester.testDelete(Catalogue.PAATIETORYHMA);
	}
	// Common tests end here.
}
