package fi.liike.rest.api;

public class AreaCodeKasiteArvoContent extends KasiteArvoContent {
    private final String areaCode;

    public AreaCodeKasiteArvoContent(Integer id, String resourceName, String value, String areaCode) {
        super(id, resourceName, value);
        this.areaCode = areaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }
}
