package fi.liike.rest.api.dto;

import fi.liike.rest.api.ContentDto;


public class TietojarjestelmapalveluFetchMinimalDto extends ContentDto {
    private Integer tunnus;

    private String nimi;

    private String kuvaus;

    @Override
    public void setRivimuokkaajatunnus(String header) {

    }

    @Override
    public String getRivimuokkaajatunnus() {
        return null;
    }


    @Override
    public Integer getTunnus() {
        return tunnus;
    }

    @Override
    public void setTunnus(Integer tunnus) {
        this.tunnus = tunnus;
    }

    @Override
    public String getNimi() {
        return nimi;
    }

    @Override
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }
}
