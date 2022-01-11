package fi.liike.rest.api.dto;

import fi.liike.rest.api.ContentDto;

//Use this dto only for fetching Jarjestelma data with minimal info
public class JarjestelmaFetchMinimalDto extends ContentDto {

    private Integer tunnus;

    private String nimi;

    private String kuvaus;

    private String elinkaaritila;

    private String jarjestelmaalue;

    private String jarjestelmatyyppi;

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

    public String getElinkaaritila() {
        return elinkaaritila;
    }

    public void setElinkaaritila(String elinkaaritila) {
        this.elinkaaritila = elinkaaritila;
    }

    public String getJarjestelmaalue() {
        return jarjestelmaalue;
    }

    public void setJarjestelmaalue(String jarjestelmaalue) {
        this.jarjestelmaalue = jarjestelmaalue;
    }

    public String getJarjestelmatyyppi() {
        return jarjestelmatyyppi;
    }

    public void setJarjestelmatyyppi(String jarjestelmatyyppi) {
        this.jarjestelmatyyppi = jarjestelmatyyppi;
    }

    @Override
    public void setRivimuokkaajatunnus(String header) {

    }

    @Override
    public String getRivimuokkaajatunnus() {
        return null;
    }

}
