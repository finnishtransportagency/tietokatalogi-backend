package fi.liike.rest.Model;


import fi.liike.rest.api.HistoryType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "TOIMINTAPROSTIETOVARAHISTORIA")
public class JoinToimintaprosessiTietovarantoHistory implements JoinHistory, java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "TOIMINTAPROSTIETOVAHIST_ID_SEQ")
    @Column(name = "rivi_id", unique = true, nullable = false)
    private Integer rivi_id;

    @Column(name = "toimintaprosessitunnus")
    private Integer parentNode;

    @Column(name = "tietovarantotunnus")
    private Integer childNode;

    @Column(name = "HISTORIATYYPPI")
    @Enumerated(EnumType.STRING)
    private HistoryType historiatyyppi;

    @Column(name = "riviluotupvm")
    private Timestamp riviluotupvm;

    @Column(name = "rivimuokkaajatunnus")
    private String rivimuokkaajatunnus;

    public JoinToimintaprosessiTietovarantoHistory() {
    }

    public Integer getRivi_id() {
        return rivi_id;
    }

    public void setRivi_id(Integer rivi_id) {
        this.rivi_id = rivi_id;
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
        return this.childNode;
    }

    @Override
    public void setChildNode(Integer childNode) {
        this.childNode = childNode;
    }

    @Override
    public Integer getParentNode() {
        return this.parentNode;
    }

    @Override
    public void setParentNode(Integer parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public Long getRivitunnus() {
        return null;
    }
}
