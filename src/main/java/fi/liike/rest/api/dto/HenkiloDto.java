package fi.liike.rest.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class HenkiloDto {
    private Integer tunnus;
    private String objectID;
    private String nayttonimi;
    private String tunnustyyppi;
    private Boolean poistunut;
    private String kayttajatunnus;
    private String yritys;
    private String yritystunnus;
    private String etunimi;
    private String sukunimi;
    private String sahkoposti;
    private String matkapuhelin;

    public String getObjectID() {
        return objectID;
    }

    public String getTunnustyyppi() {
        return tunnustyyppi;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public String getNayttonimi() {
        return nayttonimi;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public Boolean getPoistunut() {
        return poistunut;
    }

    public String getKayttajatunnus() {
        return kayttajatunnus;
    }

    public String getYritys() {
        return yritys;
    }

    public String getYritystunnus() {
        return yritystunnus;
    }

    public String getMatkapuhelin() {
        return matkapuhelin;
    }

    public HenkiloDto() {
    }

    public Integer getTunnus() {
        return tunnus;
    }

    public void setTunnus(Integer tunnus) {
        this.tunnus = tunnus;
    }

    @JacksonXmlProperty(localName="ObjectID")
    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    @JacksonXmlProperty(localName="Accounttype")
    public void setTunnustyyppi(String tunnustyyppi) {
        this.tunnustyyppi = tunnustyyppi;
    }

    @JacksonXmlProperty(localName="Disabled")
    public void setPoistunut(Boolean poistunut) {
        this.poistunut = poistunut;
    }

    @JacksonXmlProperty(localName="AccountName")
    public void setKayttajatunnus(String kayttajatunnus) {
        this.kayttajatunnus = kayttajatunnus;
    }

    @JacksonXmlProperty(localName="FirstName")
    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    @JacksonXmlProperty(localName="LastName")
    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    @JacksonXmlProperty(localName="DisplayName")
    public void setNayttonimi(String nayttonimi) {
        this.nayttonimi = nayttonimi;
    }

    @JacksonXmlProperty(localName="Company")
    public void setYritys(String yritys) {
        this.yritys = yritys;
    }

    @JacksonXmlProperty(localName="Yritystunnus")
    public void setYritystunnus(String yritystunnus) {
        this.yritystunnus = yritystunnus;
    }

    @JacksonXmlProperty(localName="Email")
    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    @JacksonXmlProperty(localName="MobilePhone")
    public void setMatkapuhelin(String matkapuhelin) {
        this.matkapuhelin = matkapuhelin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HenkiloDto that = (HenkiloDto) o;

        if (!tunnus.equals(that.tunnus)) return false;
        if (!objectID.equals(that.objectID)) return false;
        if (nayttonimi != null ? !nayttonimi.equals(that.nayttonimi) : that.nayttonimi != null) return false;
        if (tunnustyyppi != null ? !tunnustyyppi.equals(that.tunnustyyppi) : that.tunnustyyppi != null) return false;
        if (poistunut != null ? !poistunut.equals(that.poistunut) : that.poistunut != null) return false;
        if (kayttajatunnus != null ? !kayttajatunnus.equals(that.kayttajatunnus) : that.kayttajatunnus != null)
            return false;
        if (yritys != null ? !yritys.equals(that.yritys) : that.yritys != null) return false;
        if (yritystunnus != null ? !yritystunnus.equals(that.yritystunnus) : that.yritystunnus != null) return false;
        if (!etunimi.equals(that.etunimi)) return false;
        if (!sukunimi.equals(that.sukunimi)) return false;
        if (sahkoposti != null ? !sahkoposti.equals(that.sahkoposti) : that.sahkoposti != null) return false;
        return matkapuhelin != null ? matkapuhelin.equals(that.matkapuhelin) : that.matkapuhelin == null;
    }

    @Override
    public int hashCode() {
        int result = tunnus.hashCode();
        result = 31 * result + objectID.hashCode();
        result = 31 * result + (nayttonimi != null ? nayttonimi.hashCode() : 0);
        result = 31 * result + (tunnustyyppi != null ? tunnustyyppi.hashCode() : 0);
        result = 31 * result + (poistunut != null ? poistunut.hashCode() : 0);
        result = 31 * result + (kayttajatunnus != null ? kayttajatunnus.hashCode() : 0);
        result = 31 * result + (yritys != null ? yritys.hashCode() : 0);
        result = 31 * result + (yritystunnus != null ? yritystunnus.hashCode() : 0);
        result = 31 * result + etunimi.hashCode();
        result = 31 * result + sukunimi.hashCode();
        result = 31 * result + (sahkoposti != null ? sahkoposti.hashCode() : 0);
        result = 31 * result + (matkapuhelin != null ? matkapuhelin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HenkiloDto{" +
                "tunnus=" + tunnus +
                ", objectID='" + objectID + '\'' +
                ", nayttonimi='" + nayttonimi + '\'' +
                ", tunnustyyppi='" + tunnustyyppi + '\'' +
                ", poistunut=" + poistunut +
                ", kayttajatunnus='" + kayttajatunnus + '\'' +
                ", yritys='" + yritys + '\'' +
                ", yritystunnus='" + yritystunnus + '\'' +
                ", etunimi='" + etunimi + '\'' +
                ", sukunimi='" + sukunimi + '\'' +
                ", sahkoposti='" + sahkoposti + '\'' +
                ", matkapuhelin='" + matkapuhelin + '\'' +
                '}';
    }
}
