package fi.liike.rest;

import fi.liike.rest.api.Catalogue;
import org.codehaus.jettison.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TermilomakeTest {

    private MainRestTester mainTester;

    @Before
    public void setUp() {
        mainTester = new MainRestTester(Catalogue.TERMILOMAKE);
    }

    @After
    public void tearDown() {
        mainTester.clear();
    }

    // Common tests begin here.
    @Test
    public void testTermilomakePaging() throws JSONException {
        mainTester.testPaging(Catalogue.TERMILOMAKE);
    }

    @Test
    public void testTermilomakeGetEntry() throws JSONException {
        mainTester.testGetEntry(Catalogue.TERMILOMAKE);
    }

    @Test
    public void testTermilomakeCreateNew() throws JSONException, IOException {
        mainTester.testCreateNew(Catalogue.TERMILOMAKE);
    }

    @Test
    public void TestTermilomakeUpdate() throws JSONException, IOException {
        mainTester.testUpdate(Catalogue.TERMILOMAKE);
    }

    @Test
    public void testTermilomakeDelete() throws JSONException {
        mainTester.testDelete(Catalogue.TERMILOMAKE);
    }

    @Test
    public void testTermilomakeGetKasite() {
        // TODO
    }

}
