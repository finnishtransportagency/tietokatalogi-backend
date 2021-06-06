package fi.liike.rest.api;

public class SelitysKasiteArvoContent extends KasiteArvoContent {
    private final String selitys;

    public SelitysKasiteArvoContent(Integer id, String resourceName, String value, String selitys) {
        super(id, resourceName, value);
        this.selitys = selitys;
    }

    public String getSelitys() {
        return selitys;
    }
}
