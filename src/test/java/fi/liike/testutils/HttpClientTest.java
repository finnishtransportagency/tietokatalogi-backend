package fi.liike.testutils;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fi.liike.rest.util.http.HttpClient;
import fi.liike.rest.util.http.LiikeHttpResponse;
import org.codehaus.jettison.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Map;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

public class HttpClientTest {
    private HttpClient httpClient;
    private Gson gson;
    private static String testBaseUrl = "https://httpbin.org/";

    @Before
    public void setUp() {
        this.httpClient = new HttpClient();
        this.gson = new Gson();
    }

    @Test
    public void testHttpClientGetSuccess() throws IOException, JSONException {
        Integer expectedStatusCode = 200;
        String expectedValue = "works";
        LiikeHttpResponse response = httpClient.get(format("%sget?test=%s", testBaseUrl, expectedValue));
        String fetched = getValueFromResponse(response, "args", "test", String.class);
        assertEquals(fetched, expectedValue);
        assertStatusCode(response.getStatusCode(), expectedStatusCode);
    }

    @Test
    public void testHttpClientGetFail() throws IOException {
        Integer expectedStatusCode = 400;
        LiikeHttpResponse response = httpClient.get(format("%sstatus/%d", testBaseUrl, expectedStatusCode));
        assertStatusCode(response.getStatusCode(), expectedStatusCode);
    }

    @Test
    public void testHttpClientPostSuccess() throws IOException {
        Integer expectedStatusCode = 200;
        Map<String, String> expectedHeaders = ImmutableMap.of("Test", "works", "Another", "test");
        String expectedContent = "<some><xml></xml></some>";
        LiikeHttpResponse response = httpClient.post(URI.create(format("%spost", testBaseUrl)), expectedHeaders, expectedContent);
        for (String headerName : expectedHeaders.keySet()) {
            String fetchedHeaderValue = getValueFromResponse(response, "headers", headerName, String.class);
            assertEquals(fetchedHeaderValue, expectedHeaders.get(headerName));
        }
        assertStatusCode(response.getStatusCode(), expectedStatusCode);
        assertEquals(getValueFromResponse(response, "data", null, String.class), expectedContent);
    }

    private <T> T getValueFromResponse(LiikeHttpResponse response, String dataKey, String dataValue, Class<T> returnType) {
        Type stringStringMap = new TypeToken<Map<String, T>>(){}.getType();
        if (dataValue != null) {
            Map<String, Map<String, T>> map = gson.fromJson(response.getResponse(), stringStringMap);
            return map.get(dataKey).get(dataValue);
        } else {
            Map<String, T> map = gson.fromJson(response.getResponse(), stringStringMap);
            return map.get(dataKey);
        }
    }

    private void assertStatusCode(Integer fetchedStatusCode, Integer expectedStatusCode) {
        assertEquals(fetchedStatusCode, expectedStatusCode);
    }
}
