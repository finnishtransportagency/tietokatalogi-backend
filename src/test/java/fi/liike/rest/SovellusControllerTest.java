package fi.liike.rest;


import fi.liike.rest.api.Catalogue;

import org.junit.After;
import org.junit.Before;

public class SovellusControllerTest {
    private MainRestTester mainTester;

    @Before
    public void setUp() {
        this.mainTester = new MainRestTester(Catalogue.SOVELLUS);
    }

    @After
    public void tearDown() {
        mainTester.clear();
    }

    // TODO: The sovellus section has CRUD functionality which should be tested.
    // All previous tests were integration related, which are removed.
}
