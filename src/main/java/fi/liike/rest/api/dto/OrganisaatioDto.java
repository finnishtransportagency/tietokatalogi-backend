package fi.liike.rest.api.dto;

public class OrganisaatioDto extends ContentDtoWithRights {

    private String nimi;
    private Integer tunnus;
    private String rivimuokkaajatunnus;
    private String osoite;
    private String sahkoposti;
    private String puhelinnumero;

    @Override
    public void setNimi(String name) {
        this.nimi = name;
    }

    @Override
    public void setTunnus(Integer id) {
        this.tunnus = id;
    }

    @Override
    public Integer getTunnus() {
        return this.tunnus;
    }

    @Override
    public String getNimi() {
        return this.nimi;
    }

    @Override
    public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
        this.rivimuokkaajatunnus = rivimuokkaajatunnus;
    }

    @Override
    public String getRivimuokkaajatunnus() {
        return this.rivimuokkaajatunnus;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    public String getPuhelinnumero() {
        return puhelinnumero;
    }

    public void setPuhelinnumero(String puhelinnumero) {
        this.puhelinnumero = puhelinnumero;
    }
}
