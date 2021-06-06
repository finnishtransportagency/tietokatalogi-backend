package fi.liike.rest;

import fi.liike.rest.Controller.MainController;
import fi.liike.rest.Service.Service;
import fi.liike.rest.api.Catalogue;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.ContentDtoWithRights;
import fi.liike.testutils.TestRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class MainControllerTest {
    private MainRestTester mainRestTester;
    private MainController mainController;
    private Service service;

    @Before
    public void setUp() {
        this.mainRestTester = new MainRestTester(Catalogue.LOOGINEN);
        this.mainController = Mockito.mock(MainController.class, Mockito.CALLS_REAL_METHODS);
        this.service = Mockito.mock(Service.class);
    }

    @After
    public void tearDown() {
        mainRestTester.clear();
    }


    @Test
    public void putReturnsClientErrorOnNullName() {
        ContentDtoWithRights contentDto = Mockito.mock(ContentDtoWithRights.class);
        Mockito.when(contentDto.getNimi()).then(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) {
                return null;
            }
        });
        Response response = mainController.updateWithRights(this.service, contentDto, new TestRequest());
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void putReturnsClientErrorOnEmptyStringName() {
        ContentDto contentDto = Mockito.mock(ContentDto.class);
        Mockito.when(contentDto.getNimi()).then(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) {
                return "";
            }
        });
        Response response = mainController.updateWithRights(this.service, contentDto, new TestRequest());
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}
