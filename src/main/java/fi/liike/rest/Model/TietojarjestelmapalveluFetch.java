package fi.liike.rest.Model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.persistence.JoinTable;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name="TIETOJARJESTELMAPALVELU")
public class TietojarjestelmapalveluFetch extends Haettava implements Serializable {
    private static final String seqName = "TIETOJARJESTELMAPALVELU_ID_SEQ";
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "TUNNUS", unique = true, nullable = false)
    private Integer tunnus;

    @Column(name="NIMI")
    private String nimi;

    @Column(name="KUVAUS")
    private String kuvaus;

    @Column(name="KAYTTAJAROOLIT")
    private String kayttajaroolit;

    @Column(name="TIETOJARJESTELMATUNNUS")
    private Integer tietojarjestelmatunnus;

    @Column(name="ELINKAARI")
    private String elinkaari;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT) // ensures that fetch returns no duplicates
    @JoinTable(
        name = "TIETOJARJPALVELU_TIETOLAJI",
        joinColumns = @JoinColumn(name="TIETOJARJESTELMAPALVELUTUNNUS"),
        inverseJoinColumns = @JoinColumn(name="TIETOLAJITUNNUS")
    )
    private Set<Tietolaji> tietolajit;

    @Column(name="RIVILUOTUPVM")
    private Timestamp riviluotupvm;

    @Column(name = "RIVIMUOKATTUPVM")
    private Timestamp rivimuokattupvm;

    @Column(name="RIVIMUOKKAAJATUNNUS")
    private String rivimuokkaajatunnus;

    public Set<Tietolaji> getTietolajit() {
        if (this.tietolajit == null) {
            return new HashSet<>();
        }
        return tietolajit;
    }

    public void setTietolajit(Set<Tietolaji> tietolajit) {
        this.tietolajit = tietolajit;
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

    public String getKayttajaroolit() {
        return kayttajaroolit;
    }

    @Override
    public Timestamp getRiviluotupvm() {
        return riviluotupvm;
    }

    @Override
    public void setRiviluotupvm(Timestamp riviluotupvm) {
        this.riviluotupvm = riviluotupvm;
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
    public String getRivimuokkaajatunnus() {
        return rivimuokkaajatunnus;
    }

    @Override
    public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
        this.rivimuokkaajatunnus = rivimuokkaajatunnus;
    }

    public void setKayttajaroolit(String kayttajaroolit) {
        this.kayttajaroolit = kayttajaroolit;
    }

    public Integer getTietojarjestelmatunnus() {
        return tietojarjestelmatunnus;
    }

    public void setTietojarjestelmatunnus(Integer tietojarjestelmatunnus) {
        this.tietojarjestelmatunnus = tietojarjestelmatunnus;
    }

    public String getElinkaari() {
        return elinkaari;
    }

    public void setElinkaari(String elinkaari) {
        this.elinkaari = elinkaari;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }
}
