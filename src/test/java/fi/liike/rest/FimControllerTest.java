package fi.liike.rest;

import fi.liike.config.Configurations;
import fi.liike.rest.Model.HenkiloTemp;
import fi.liike.rest.Service.FimHenkiloService;
import fi.liike.rest.Service.HenkiloService;
import fi.liike.rest.util.http.HttpClient;
import fi.liike.rest.util.http.LiikeHttpResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class FimControllerTest {
    private HttpClient mockHttpClient;
    private FimHenkiloService henkiloService;
    private String fimBaseUrl;
    private String fimUri;

    @Before
    public void setUp() {
        Configurations.readConfigurations();
        fimBaseUrl = Configurations.baseFIMurl;
        fimUri = fimBaseUrl + "Person.svc/getsimplesql";
        this.mockHttpClient = mock(HttpClient.class);
        this.henkiloService = new FimHenkiloService(fimBaseUrl, mockHttpClient);
    }

    @Test(expected = IOException.class)
    public void fetchAllPersonsFromFIMFailsTest() throws IOException {
        LiikeHttpResponse expectedResponse = new LiikeHttpResponse(null, 500);
        when(mockHttpClient.get(fimUri)).thenReturn(expectedResponse);
        henkiloService.fetchAndSaveAllPersonsFromFIM();
    }

    @Test(expected = IOException.class)
    public void fetchAllPersonsFromFIMFails2Test() throws IOException {
        LiikeHttpResponse expectedResponse = new LiikeHttpResponse(null, 200);
        when(mockHttpClient.get(fimUri)).thenReturn(expectedResponse);
        henkiloService.fetchAndSaveAllPersonsFromFIM();
    }

    @Test
    @Ignore
    // requires pgsql
    public void fetchAllPersonsFromFIMTest() throws IOException {
        String firstName = "Test";
        String lastName = "Name";
        String content = "<person><person>\n" +
                "<ObjectID>12345a5b-9290-483c-9da3-734341728d0e</ObjectID>\n" +
                "<DisplayName>jdsfjskdlfdfj</DisplayName>\n" +
                "<Accounttype>LX-tunnus</Accounttype>\n" +
                "<Disabled>False</Disabled>\n" +
                "<AccountName>LX123456</AccountName>\n" +
                "<Company>jdksdj</Company>\n" +
                "<Yritystunnus>5555555-5</Yritystunnus>\n" +
                "<FirstName>" + firstName + "</FirstName>\n" +
                "<LastName>" + lastName + "</LastName>\n" +
                "<Email>fsjdkf@example.com</Email>\n" +
                "<MobilePhone>0401234567</MobilePhone>\n" +
                "</person></person>";

        LiikeHttpResponse expectedResponse = new LiikeHttpResponse(content, 200);
        when(mockHttpClient.get(fimUri)).thenReturn(expectedResponse);
        List<HenkiloTemp> list = henkiloService.fetchAndSaveAllPersonsFromFIM();
        assertEquals(list.size(), 1);
        HenkiloTemp henk = list.get(0);
        assertEquals(henk.getEtunimi(), firstName);
        assertEquals(henk.getSukunimi(), lastName);
    }
}
