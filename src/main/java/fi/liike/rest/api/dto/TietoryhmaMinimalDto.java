package fi.liike.rest.api.dto;

public class TietoryhmaMinimalDto {

    private Integer tunnus;

    private String nimi;

    // Used by the ResultTransformer in getMatchingTietoryhma in TietojarjestelmapalveluDaoImpl
    public TietoryhmaMinimalDto() {
    }

    public TietoryhmaMinimalDto(Integer tunnus, String nimi) {
        this.tunnus = tunnus;
        this.nimi = nimi;
    }

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
