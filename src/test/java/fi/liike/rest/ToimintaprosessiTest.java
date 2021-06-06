package fi.liike.rest;

import fi.liike.rest.api.Catalogue;
import org.codehaus.jettison.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

public class ToimintaprosessiTest {
    private MainRestTester mainTester;

    @Before
    public void setUp() {
        mainTester = new MainRestTester(Catalogue.TOIMINTAPROSESSI);
    }

    @After
    public void tearDown() {
        mainTester.clear();
    }

    @Test
    public void testToimintaprosessiGet() {
        mainTester.testGetEntry(Catalogue.TOIMINTAPROSESSI);
    }

    @Test
    public void testToimintaprosessiPaging() {
        mainTester.testPaging(Catalogue.TOIMINTAPROSESSI);
    }

    @Test
    public void testToimintaprosessiCreateNew() {
        try {
            mainTester.testCreateNew(Catalogue.TOIMINTAPROSESSI);
        }
        catch (IOException e) {
            fail("Failing test due to thrown exception: " + e.getMessage());
        }
    }

    @Test
    public void testToimintaprosessiDelete() {
        mainTester.testDelete(Catalogue.TOIMINTAPROSESSI);
    }

    @Test
    public void testToimintaprosessiUpdate() {
        try {
            mainTester.testUpdate(Catalogue.TOIMINTAPROSESSI);
        } catch (JSONException | IOException e) {
            fail("Failing test due to thrown exception: " + e.getMessage());
        }
    }
}
