package fi.liike.rest.Model;

import fi.liike.rest.api.HistoryType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

import static java.lang.String.format;

@Entity
@Table(name = "TERMILOMAKEASKASIHISTORIA")
public class TermilomakeJoinAssosiatiivinenKasiteHistory implements JoinHistory, java.io.Serializable {

    private static final long serialVersionUID = 1L;
    public static final String seq_name = "TERMILOMAKEASKAHISTORIA_ID_SEQ";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = seq_name)
    @Column(name = "rivi_id", unique = true, nullable = false)
    private Long rivi_id;

    @Column(name = "assosiatiivinen_ylakasite")
    private Integer parentNode;

    @Column(name = "assosiatiivinen_alakasite")
    private Integer childNode;

    @Column(name = "HISTORIATYYPPI")
    @Enumerated(EnumType.STRING)
    private HistoryType historiatyyppi;

    @Column(name = "riviluotupvm")
    private Timestamp riviluotupvm;

    @Column(name = "rivimuokkaajatunnus")
    private String rivimuokkaajatunnus;


    @Override
    public Integer getParentNode() {
        return parentNode;
    }

    @Override
    public void setParentNode(Integer parentNode) {
        this.parentNode = parentNode;
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

    @Override
    public Long getRivitunnus() {
        return this.rivi_id;
    }
}
