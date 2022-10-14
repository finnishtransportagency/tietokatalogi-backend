package fi.liike.rest;

import com.google.common.collect.ImmutableList;
import fi.liike.rest.Controller.MainController;
import fi.liike.rest.api.Catalogue;
import fi.liike.rest.api.ContentDto;
import fi.liike.testutils.TestRequest;
import fi.liike.testutils.TestUtil;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CommonTest {

    private final ImmutableList<Catalogue> formTypes = ImmutableList.of(
            Catalogue.JARJESTELMA, Catalogue.FYYSINEN,
            Catalogue.LOOGINEN, Catalogue.TIETOLAJI,
            Catalogue.TIETORYHMA, Catalogue.PAATIETORYHMA,
            Catalogue.TERMILOMAKE, Catalogue.TIETOVARANTO,
            Catalogue.PALVELU, Catalogue.TIETOJARJESTELMAPALVELU,
            Catalogue.TIETOOMAISUUS, Catalogue.TOIMINTAPROSESSI,
            Catalogue.ORGANISAATIO, Catalogue.TIETOSUOJAVASTAAVA
    );

    @Test
    public void testModifierUsername() {
        formTypes.forEach(type -> {
            MainController controller = TestUtil.getRest(type);
            assertNotNull("Controller for " + type + " was null.", controller);

            ContentDto firstEntry = TestUtil.createEntry(type);
            ExtractedResponse response = null;
            try {
                response = new ExtractedResponse(controller.create(new TestRequest(), firstEntry));
            } catch (IOException e) {
                fail("Test threw exception in controller.create: " + e);
            }
            assertEquals(response.getValue("rivimuokkaajatunnus"), "TestUser");
        });
    }

    @Test
    public void testCreationDate() {
        formTypes.forEach(type -> {
            MainController controller = TestUtil.getRest(type);
            assertNotNull("Controller for " + type + " was null.", controller);

            ContentDto firstEntry = TestUtil.createEntry(type);
            ExtractedResponse response = null;
            try {
                response = new ExtractedResponse(controller.create(new TestRequest(), firstEntry));
            } catch (IOException e) {
                fail("Test threw exception in controller.create: " + e);
            }
            assertNotNull("Response from " + type + " does not have riviluotupvm. Response: "
                    + response.getItems(), response.getValue("riviluotupvm"));
        });
    }

    @Test
    public void testModificationDate() {
        formTypes.forEach(type -> {
            MainController controller = TestUtil.getRest(type);
            assertNotNull("Controller for " + type + " was null.", controller);

            ContentDto firstEntry = TestUtil.createEntry(type);
            ExtractedResponse response = null;
            try {
                response = new ExtractedResponse(controller.create(new TestRequest(), firstEntry));
            } catch (IOException e) {
                fail("Test threw exception in controller.create: " + e);
            }
            firstEntry.setTunnus((Integer) response.getValue("tunnus"));
            try {
                response = new ExtractedResponse(controller.update(new TestRequest(), firstEntry));
            } catch (IOException e) {
                fail("Test threw exception in controller.create: " + e);
            }
            assertNotNull("Response from " + type + " does not not have rivimuokattupvm. Response: "
                    + response.getItems(), response.getValue("rivimuokattupvm"));
        });
    }
}
