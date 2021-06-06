package fi.liike.rest.Model;

import fi.liike.rest.api.HistoryType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.persistence.JoinTable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "TIETOJARJPALVELUHISTORIA")
public class TietojarjestelmapalveluHistory implements java.io.Serializable, HaettavaHistory {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "RIVI_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "tietojarjpalveluhist_id_seq")
    private int rivi_id;

    @Column(name = "TUNNUS")
    private Integer tunnus;

    @Column(name = "HISTORIATYYPPI")
    @Enumerated(EnumType.STRING)
    private HistoryType historiatyyppi;

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

    @Column(name="RIVILUOTUPVM")
    private Timestamp riviluotupvm;

    @Column(name = "RIVIMUOKATTUPVM")
    private Timestamp rivimuokattupvm;

    @Column(name="RIVIMUOKKAAJATUNNUS")
    private String rivimuokkaajatunnus;

    @Override
    public HistoryType getHistoriatyyppi() {
        return this.historiatyyppi;
    }

    @Override
    public void setHistoriatyyppi(HistoryType historiatyyppi) {
        this.historiatyyppi = historiatyyppi;
    }

    @Override
    public Timestamp getRiviluotupvm() {
        return this.riviluotupvm;
    }

    @Override
    public void setRiviluotupvm(Timestamp riviluotupvm) {
        this.riviluotupvm = riviluotupvm;
    }

    @Override
    public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
        this.rivimuokkaajatunnus = rivimuokkaajatunnus;
    }

    @Override
    public String getRivimuokkaajatunnus() {
        return this.rivimuokkaajatunnus;
    }

    public int getRivi_id() {
        return rivi_id;
    }

    public void setRivi_id(int rivi_id) {
        this.rivi_id = rivi_id;
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

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public String getKayttajaroolit() {
        return kayttajaroolit;
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

    public Timestamp getRivimuokattupvm() {
        return rivimuokattupvm;
    }

    public void setRivimuokattupvm(Timestamp rivimuokattupvm) {
        this.rivimuokattupvm = rivimuokattupvm;
    }

}
