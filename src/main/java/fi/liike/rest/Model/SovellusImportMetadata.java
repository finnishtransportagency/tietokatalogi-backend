package fi.liike.rest.Model;

public class SovellusImportMetadata {
    private final String latestImport;
    private final String latestSuccessfulImport;

    public SovellusImportMetadata(String latestImport, String latestSuccessfulImport) {
        this.latestImport = latestImport;
        this.latestSuccessfulImport = latestSuccessfulImport;
    }

    public String getLatestImport() {
        return latestImport;
    }

    public String getLatestSuccessfulImport() {
        return latestSuccessfulImport;
    }
}
