package fi.liike.rest.Model;

import fi.liike.rest.api.HistoryType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "TIETOJARJPALVELUTIETOHISTORIA")
public class JoinTietojarjestelmapalveluTietolajiHistory implements java.io.Serializable, JoinHistory {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "tietojarjpalvtietohist_id_seq")
    @Column(name = "rivitunnus", unique = true, nullable = false)
    private Long rivitunnus;

    @Column(name = "TIETOJARJESTELMAPALVELUTUNNUS")
    private Integer childNode;

    @Column(name = "TIETOLAJITUNNUS")
    private Integer parentNode;

    @Column(name = "rivitila")
    private String rivitila = "A";

    @Column(name = "HISTORIATYYPPI")
    @Enumerated(EnumType.STRING)
    private HistoryType historiatyyppi;

    @Column(name = "riviluotupvm")
    private Timestamp riviluotupvm;

    @Column(name = "rivimuokkaajatunnus")
    private String rivimuokkaajatunnus;

    public JoinTietojarjestelmapalveluTietolajiHistory(Integer childNode, Integer parentNode) {
        this.childNode = childNode;
        this.parentNode = parentNode;
    }

    public JoinTietojarjestelmapalveluTietolajiHistory() {
    }

    @Override
    public Long getRivitunnus() {
        return rivitunnus;
    }

    public void setRivitunnus(Long rivitunnus) {
        this.rivitunnus = rivitunnus;
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

    public String getRivitila() {
        return rivitila;
    }

    public void setRivitila(String rivitila) {
        this.rivitila = rivitila;
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

}
