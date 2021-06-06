package fi.liike.rest;

import fi.liike.rest.api.Catalogue;
import org.codehaus.jettison.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

public class TietovarantoTest {
    private MainRestTester mainTester;

    @Before
    public void setUp() {
        mainTester = new MainRestTester(Catalogue.TIETOVARANTO);
    }

    @After
    public void tearDown() {
        mainTester.clear();
    }

    @Test
    public void testTietovarantoGet() {
        mainTester.testGetEntry(Catalogue.TIETOVARANTO);
    }

    @Test
    public void testTietovarantoPaging() {
        mainTester.testPaging(Catalogue.TIETOVARANTO);
    }

    @Test
    public void testTietovarantoCreateNew() {
        try {
            mainTester.testCreateNew(Catalogue.TIETOVARANTO);
        }
        catch (IOException e) {
            fail("Failing test due to thrown exception: " + e.getMessage());
        }
    }

    @Test
    public void testTietovarantoDelete() {
        mainTester.testDelete(Catalogue.TIETOVARANTO);
    }

    @Test
    public void testTietovarantoUpdate() {
        try {
            mainTester.testUpdate(Catalogue.TIETOVARANTO);
        } catch (JSONException | IOException e) {
            fail("Failing test due to thrown exception: " + e.getMessage());
        }
    }
}
