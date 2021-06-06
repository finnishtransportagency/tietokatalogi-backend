package fi.liike.rest.Model;

import fi.liike.rest.api.HistoryType;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

import static java.lang.String.format;

@Entity
@Table(name = "jarjestelmalinkkaushistoria")
public class JoinJarjestelmaLinkkausHistory extends JoinTable implements Serializable, JoinHistory {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "rivitunnus", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "joinjlinkhist_id_seq")
    private Long rivitunnus;

    @Column(name = "TIETOJARJESTELMATUNNUS")
    private Integer parentNode;

    @Column(name = "LINKATTAVA_ID")
    private Integer childNode;

    @Column(name = "HISTORIATYYPPI")
    @Enumerated(EnumType.STRING)
    private HistoryType historiatyyppi;

    @Column(name = "rivitila")
    private String rivitila = "A";

    @Column(name = "riviluotupvm")
    private Timestamp riviluotupvm;

    @Column(name = "rivimuokkaajatunnus")
    private String rivimuokkaajatunnus;

    @Column(name = "SUUNTA")
    private String suunta;

    @Column(name = "TIETOJARJESTELMAPALVELUTUNNUS")
    private Integer tietojarjestelmapalveluTunnus;

    @Column(name = "TYYPPI")
    private String tyyppi;

    @Column(name = "KUVAUS")
    private String kuvaus;

    public JoinJarjestelmaLinkkausHistory() {
    }

    public JoinJarjestelmaLinkkausHistory(String suunta, Integer tietojarjestelmapalveluTunnus, String tyyppi, String kuvaus) {
        this.suunta = suunta;
        this.tietojarjestelmapalveluTunnus = tietojarjestelmapalveluTunnus;
        this.tyyppi = tyyppi;
        this.kuvaus = kuvaus;
    }

    @Override
    public String toString() {
        return format("JoinJarjestelmaLinkkausHistory[rivitunnus=%s, parentNode=%s, childNode=%s, historiatyyppi=%s, " +
                "rivimuokkaajatunnus=%s, suunta=%s, tietojarjestelmapalveluTunnus=%s, tyyppi=%s, kuvaus=%s", rivitunnus, parentNode, childNode, historiatyyppi,
                rivimuokkaajatunnus, suunta, tietojarjestelmapalveluTunnus, tyyppi, kuvaus);
    }

    @Override
    public Integer getChildNode() {
        return childNode;
    }

    @Override
    public void setChildNode(Integer childNode) {
        this.childNode = childNode;
    }

    @Override
    public Integer getParentNode() {
        return parentNode;
    }

    @Override
    public void setParentNode(Integer parentNode) {
        this.parentNode = parentNode;
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
    public String getRivimuokkaajatunnus() {
        return rivimuokkaajatunnus;
    }

    @Override
    public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
        this.rivimuokkaajatunnus = rivimuokkaajatunnus;
    }

    @Override
    public Long getRivitunnus() {
        return rivitunnus;
    }

    public String getSuunta() {
        return suunta;
    }

    public Integer getTietojarjestelmapalveluTunnus() {
        return tietojarjestelmapalveluTunnus;
    }

    public String getTyyppi() {
        return tyyppi;
    }

    @Override
    public HistoryType getHistoriatyyppi() {
        return historiatyyppi;
    }

    @Override
    public void setHistoriatyyppi(HistoryType historiatyyppi) {
        this.historiatyyppi = historiatyyppi;
    }

    @Override
    public void setRivitila(String rivitila) {
        this.rivitila = rivitila;
    }

    @Override
    public String getRivitila() {
        return rivitila;
    }

    public String getKuvaus() {
        return kuvaus;
    }

}

