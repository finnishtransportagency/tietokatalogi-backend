package fi.liike.rest.Model;


import fi.liike.rest.api.HistoryType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "JARJESTELMALOOGINENHISTORIA")
public class JoinJarjestelmaLooginenHistory implements JoinHistory, java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "JARJESTELMALOOGINENHIST_ID_SEQ")
    @Column(name = "rivi_id", unique = true, nullable = false)
    private Integer rivi_id;


    @Column(name = "jarjestelmatunnus")
    private Integer jarjestelmatunnus;

    @Column(name = "looginentunnus")
    private Integer looginentunnus;

    @Column(name = "HISTORIATYYPPI")
    @Enumerated(EnumType.STRING)
    private HistoryType historiatyyppi;

    @Column(name = "riviluotupvm")
    private Timestamp riviluotupvm;

    @Column(name = "rivimuokkaajatunnus")
    private String rivimuokkaajatunnus;

    public JoinJarjestelmaLooginenHistory() {
    }

    public Integer getRivi_id() {
        return rivi_id;
    }

    public void setRivi_id(Integer rivi_id) {
        this.rivi_id = rivi_id;
    }

    public Integer getJarjestelmatunnus() {
        return jarjestelmatunnus;
    }

    public void setJarjestelmatunnus(Integer jarjestelmatunnus) {
        this.jarjestelmatunnus = jarjestelmatunnus;
    }

    public Integer getLooginentunnus() {
        return looginentunnus;
    }

    public void setLooginentunnus(Integer looginentunnus) {
        this.looginentunnus = looginentunnus;
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
    public Integer getChildNode() {
        return getLooginentunnus();
    }

    @Override
    public void setChildNode(Integer childId) {
        setLooginentunnus(childId);
    }

    @Override
    public Integer getParentNode() {
        return getJarjestelmatunnus();
    }

    @Override
    public void setParentNode(Integer parentId) {
        setJarjestelmatunnus(parentId);
    }

    @Override
    public Long getRivitunnus() {
        return null;
    }
}
