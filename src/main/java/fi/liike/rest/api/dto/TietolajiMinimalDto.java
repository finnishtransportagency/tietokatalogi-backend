package fi.liike.rest.api.dto;

public class TietolajiMinimalDto {
    private Integer tunnus;
    private String nimi;

    public Integer getTunnus() {
        return tunnus;
    }

    public void setTunnus(Integer tunnus) {
        this.tunnus = tunnus;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
}
