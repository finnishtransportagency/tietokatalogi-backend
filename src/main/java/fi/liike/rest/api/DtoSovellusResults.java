package fi.liike.rest.api;

import java.util.List;

public class DtoSovellusResults extends DtoResults {
    private String latestImport;
    private String latestSuccessfulImport;

    public DtoSovellusResults(List<ContentDto> haettavat, int totalCount, String latestImport, String latestSuccessfulImport) {
        super(haettavat, totalCount);
        this.latestImport = latestImport;
        this.latestSuccessfulImport = latestSuccessfulImport;
    }

    public String getLatestImport() {
        return latestImport;
    }

    public String getLatestSuccessfulImport() {
        return latestSuccessfulImport;
    }

    public void setLatestImport(String latestImport) {
        this.latestImport = latestImport;
    }

    public void setLatestSuccessfulImport(String latestSuccessfulImport) {
        this.latestSuccessfulImport = latestSuccessfulImport;
    }
}
