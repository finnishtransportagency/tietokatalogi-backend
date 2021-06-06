package fi.liike.rest.util.http;

public class LiikeHttpResponse {
    private final String response;
    private final Integer statusCode;

    public LiikeHttpResponse(String response, Integer statusCode) {
        this.response = response;
        this.statusCode = statusCode;
    }

    public String getResponse() {
        return response;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LiikeHttpResponse that = (LiikeHttpResponse) o;

        if (response != null ? !response.equals(that.response) : that.response != null) return false;
        return statusCode != null ? statusCode.equals(that.statusCode) : that.statusCode == null;
    }

    @Override
    public int hashCode() {
        int result = response != null ? response.hashCode() : 0;
        result = 31 * result + (statusCode != null ? statusCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LiikeHttpResponse{" +
                "response='" + response + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }
}
