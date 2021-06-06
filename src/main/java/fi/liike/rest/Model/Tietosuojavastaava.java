package fi.liike.rest.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "TIETOSUOJAVASTAAVA")
public class Tietosuojavastaava extends Haettava implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String seq_name = "tietosuojavastaava_id_seq";

    @Id
    @Column(name = "tietosuojavastaavatunnus", unique = true, nullable = false)
    private Integer tunnus;

    @Column(name = "nimi")
    private String nimi;

    @Column(name = "osoite")
    private String osoite;

    @Column(name = "sahkoposti")
    private String sahkoposti;

    @Column(name = "puhelinnumero")
    private String puhelinnumero;

    @Column(name = "rivimuokkaajatunnus")
    private String rivimuokkaajatunnus;

    @Column(name = "rivimuokattupvm")
    private Timestamp rivimuokattupvm;

    @Column(name = "riviluotupvm")
    private Timestamp riviluotupvm;

    public Tietosuojavastaava() {
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

    public void setNimi(String nimi) {
        this.nimi = nimi;
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

    @Override
    public String getRivimuokkaajatunnus() {
        return rivimuokkaajatunnus;
    }

    @Override
    public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
        this.rivimuokkaajatunnus = rivimuokkaajatunnus;
    }

    @Override
    public Timestamp getRivimuokattupvm() {
        return rivimuokattupvm;
    }

    @Override
    public void setRivimuokattupvm(Timestamp rivimuokattupvm) {
        this.rivimuokattupvm = rivimuokattupvm;
    }

    @Override
    public Timestamp getRiviluotupvm() {
        return riviluotupvm;
    }

    @Override
    public void setRiviluotupvm(Timestamp riviluotupvm) {
        this.riviluotupvm = riviluotupvm;
    }
}
