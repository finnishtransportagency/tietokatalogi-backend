package fi.liike.rest.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "HENKILO_TEMP")
public class HenkiloTemp implements Serializable, Cloneable {

    @Id
    @Column(name = "TUNNUS", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "henkilo_temp_id_seq")
    private Integer tunnus;

    @Column(name = "OBJECT_ID")
    private String objectID;

    @Column(name = "NAYTTONIMI")
    private String nayttonimi;

    @Column(name = "TUNNUSTYYPPI")
    private String tunnustyyppi;

    @Column(name = "POISTUNUT")
    private Integer poistunut;

    @Column(name = "KAYTTAJATUNNUS")
    private String kayttajatunnus;

    @Column(name = "YRITYS")
    private String yritys;

    @Column(name = "YRITYSTUNNUS")
    private String yritystunnus;

    @Column(name = "ETUNIMI")
    private String etunimi;

    @Column(name = "SUKUNIMI")
    private String sukunimi;

    @Column(name = "SAHKOPOSTI")
    private String sahkoposti;

    @Column(name = "MATKAPUHELIN")
    private String matkapuhelin;

    public HenkiloTemp() {}

    public HenkiloTemp(Integer tunnus, String objectID, String nayttonimi, String tunnustyyppi, Integer poistunut, String kayttajatunnus,
                   String yritys, String yritystunnus, String etunimi, String sukunimi, String sahkoposti, String matkapuhelin) {
        this.tunnus = tunnus;
        this.objectID = objectID;
        this.nayttonimi = nayttonimi;
        this.tunnustyyppi = tunnustyyppi;
        this.poistunut = poistunut;
        this.kayttajatunnus = kayttajatunnus;
        this.yritys = yritys;
        this.yritystunnus = yritystunnus;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.sahkoposti = sahkoposti;
        this.matkapuhelin = matkapuhelin;
    }

    public HenkiloTemp(String objectID, String nayttonimi, String tunnustyyppi, Integer poistunut, String kayttajatunnus,
                   String yritys, String yritystunnus, String etunimi, String sukunimi, String sahkoposti, String matkapuhelin) {
        this.objectID = objectID;
        this.nayttonimi = nayttonimi;
        this.tunnustyyppi = tunnustyyppi;
        this.poistunut = poistunut;
        this.kayttajatunnus = kayttajatunnus;
        this.yritys = yritys;
        this.yritystunnus = yritystunnus;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.sahkoposti = sahkoposti;
        this.matkapuhelin = matkapuhelin;
    }

    public Integer getTunnus() {
        return tunnus;
    }

    public void setTunnus(Integer tunnus) {
        this.tunnus = tunnus;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public String getNayttonimi() {
        return nayttonimi;
    }

    public void setNayttonimi(String nayttonimi) {
        this.nayttonimi = nayttonimi;
    }

    public String getTunnustyyppi() {
        return tunnustyyppi;
    }

    public void setTunnustyyppi(String tunnustyyppi) {
        this.tunnustyyppi = tunnustyyppi;
    }

    public Integer getPoistunut() {
        return poistunut;
    }

    public void setPoistunut(Integer poistunut) {
        this.poistunut = poistunut;
    }

    public String getKayttajatunnus() {
        return kayttajatunnus;
    }

    public void setKayttajatunnus(String kayttajatunnus) {
        this.kayttajatunnus = kayttajatunnus;
    }

    public String getYritys() {
        return yritys;
    }

    public void setYritys(String yritys) {
        this.yritys = yritys;
    }

    public String getYritystunnus() {
        return yritystunnus;
    }

    public void setYritystunnus(String yritystunnus) {
        this.yritystunnus = yritystunnus;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    public String getMatkapuhelin() {
        return matkapuhelin;
    }

    public void setMatkapuhelin(String matkapuhelin) {
        this.matkapuhelin = matkapuhelin;
    }

    @Override
    public String toString() {
        return "Henkilo{" +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HenkiloTemp henkilo = (HenkiloTemp) o;

        if (!tunnus.equals(henkilo.tunnus)) return false;
        if (!objectID.equals(henkilo.objectID)) return false;
        if (nayttonimi != null ? !nayttonimi.equals(henkilo.nayttonimi) : henkilo.nayttonimi != null) return false;
        if (tunnustyyppi != null ? !tunnustyyppi.equals(henkilo.tunnustyyppi) : henkilo.tunnustyyppi != null)
            return false;
        if (poistunut != null ? !poistunut.equals(henkilo.poistunut) : henkilo.poistunut != null) return false;
        if (kayttajatunnus != null ? !kayttajatunnus.equals(henkilo.kayttajatunnus) : henkilo.kayttajatunnus != null)
            return false;
        if (yritys != null ? !yritys.equals(henkilo.yritys) : henkilo.yritys != null) return false;
        if (yritystunnus != null ? !yritystunnus.equals(henkilo.yritystunnus) : henkilo.yritystunnus != null)
            return false;
        if (!etunimi.equals(henkilo.etunimi)) return false;
        if (!sukunimi.equals(henkilo.sukunimi)) return false;
        if (sahkoposti != null ? !sahkoposti.equals(henkilo.sahkoposti) : henkilo.sahkoposti != null) return false;
        return matkapuhelin != null ? matkapuhelin.equals(henkilo.matkapuhelin) : henkilo.matkapuhelin == null;
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
}
