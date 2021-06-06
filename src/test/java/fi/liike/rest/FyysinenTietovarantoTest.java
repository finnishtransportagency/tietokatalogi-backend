package fi.liike.rest;

import org.codehaus.jettison.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fi.liike.rest.api.Catalogue;

import java.io.IOException;

public class FyysinenTietovarantoTest {

	private MainRestTester mainTester;

	@Before
	public void setUp() {
		mainTester = new MainRestTester(Catalogue.FYYSINEN);
	}

	@After
	public void tearDown() {
		mainTester.clear();
	}

	// Common tests begin here.
	@Test
	public void testFyysinenPaging() throws JSONException {
		mainTester.testPaging(Catalogue.FYYSINEN);
	}

	@Test
	public void testFyysinenGetEntry() throws JSONException {
		mainTester.testGetEntry(Catalogue.FYYSINEN);
	}

	@Test
	public void testFyysinenCreateNew() throws JSONException, IOException {
		mainTester.testCreateNew(Catalogue.FYYSINEN);
	}

	@Test
	public void TestFyysinenUpdate() throws JSONException, IOException {
		mainTester.testUpdate(Catalogue.FYYSINEN);
	}

	@Test
	public void testFyysinenDelete() throws JSONException {
		mainTester.testDelete(Catalogue.FYYSINEN);
	}
	// Common tests end here.
}
