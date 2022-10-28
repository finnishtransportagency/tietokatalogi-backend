package fi.liike.rest.util.http;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import static java.lang.String.format;


public class HttpClient {

    private final Logger LOG = LoggerFactory.getLogger(HttpClient.class);
    private final String fimUsername;
    private final String fimPassword;

    public HttpClient(String fimUsername, String fimPassword) {
        LOG.debug("Creating httpclient with username " + fimUsername);
        this.fimUsername = fimUsername;
        this.fimPassword = fimPassword;
    }

    public LiikeHttpResponse get(String uri) throws IOException {
        HttpGet request = new HttpGet(URI.create(uri));
        return executeRequest(request);
    }

    public LiikeHttpResponse post(URI uri, Map<String, String> headers, String content) throws IOException {
        HttpPost request = new HttpPost(uri);
        if (headers != null) {
        		for (String headerName : headers.keySet()) {
                request.setHeader(headerName, headers.get(headerName));
            }
        }
        
        if (content != null) {
            StringEntity xmlEntity = new StringEntity(content);
            request.setEntity(xmlEntity);
        }
        return executeRequest(request);
    }

    private LiikeHttpResponse executeRequest(HttpUriRequest request){
        Integer statusCode = null;
        String response = "";
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(this.fimUsername, this.fimPassword);
        LOG.debug("Will execute request with username: " + this.fimUsername);
        provider.setCredentials(AuthScope.ANY, credentials);
        try {
            CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
            HttpResponse httpResponse = client.execute(request);
            response = EntityUtils.toString(httpResponse.getEntity());
            statusCode = httpResponse.getStatusLine().getStatusCode();
        }catch (IOException e){
            LOG.error(format("HTTP request %s failed: %s", request, e.getMessage()));
        }
        return new LiikeHttpResponse(response, statusCode);
    }
}
