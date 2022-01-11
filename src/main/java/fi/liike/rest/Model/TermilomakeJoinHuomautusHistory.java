package fi.liike.rest.Model;


import fi.liike.rest.api.HistoryType;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "JOINHUOMAUTUSHISTORIA")
public class TermilomakeJoinHuomautusHistory implements JoinHistory, java.io.Serializable {

    private static  final long serialVersionUID = 1L;
    public static final String seq_name = "JOINHUOMAUTUSHIST_ID_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = seq_name)
    @Column(name = "rivi_id", unique = true, nullable = false)
    private Long rivi_id;

    @Column(name = "TERMILOMAKETUNNUS")
    private Integer parentNode;

    @Column(name = "HUOMAUTUS")
    private String huomautus;

    @Column(name = "HISTORIATYYPPI")
    @Enumerated(EnumType.STRING)
    private HistoryType historiatyyppi;

    @Column(name = "riviluotupvm")
    private Timestamp riviluotupvm;

    @Column(name = "rivimuokkaajatunnus")
    private String rivimuokkaajatunnus;

    public TermilomakeJoinHuomautusHistory() {
    }

    public TermilomakeJoinHuomautusHistory(Integer parentNode, String huomautus) {
        this.parentNode = parentNode;
        this.huomautus = huomautus;
    }

    @Override
    public Long getRivitunnus() {
        return rivi_id;
    }

    @Override
    public Integer getParentNode() {
        return parentNode;
    }

    @Override
    public void setParentNode(Integer parentNode) {
        this.parentNode = parentNode;
    }

    public String getHuomautus() {
        return huomautus;
    }

    public void setHuomautus(String huomautus) {
        this.huomautus = huomautus;
    }

    @Override
    public Integer getChildNode() {
        // Not used
        return null;
    }

    @Override
    public void setChildNode(Integer childNode) {
        // Not used
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
