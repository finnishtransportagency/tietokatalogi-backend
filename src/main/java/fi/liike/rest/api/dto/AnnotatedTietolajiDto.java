package fi.liike.rest.api.dto;

public class AnnotatedTietolajiDto {
    private Integer tunnus;
    private String nimi;
    private Integer liittyvaJarjestelmaTunnus;
    private String liittyvaJarjestelmaNimi;

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

    public Integer getLiittyvaJarjestelmaTunnus() {
        return liittyvaJarjestelmaTunnus;
    }

    public void setLiittyvaJarjestelmaTunnus(Integer liittyvaJarjestelmaTunnus) {
        this.liittyvaJarjestelmaTunnus = liittyvaJarjestelmaTunnus;
    }

    public String getLiittyvaJarjestelmaNimi() {
        return liittyvaJarjestelmaNimi;
    }

    public void setLiittyvaJarjestelmaNimi(String liittyvaJarjestelmaNimi) {
        this.liittyvaJarjestelmaNimi = liittyvaJarjestelmaNimi;
    }
}
