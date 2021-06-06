package fi.liike.rest.Model;

import fi.liike.rest.api.HistoryType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "TIETORYHMATIETOVARANTOHISTORIA")
public class JoinTietoryhmaTietovarantoHistory implements java.io.Serializable, JoinHistory {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "rivi_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "TIETORYHMATIETOVARAHIST_ID_SEQ")
    private Long rivitunnus;

    @Column(name = "tietoryhmatunnus")
    private Integer childNode;

    @Column(name = "tietovarantotunnus")
    private Integer parentNode;

    @Column(name = "historiatyyppi")
    @Enumerated(EnumType.STRING)
    private HistoryType historiatyyppi;

    @Column(name = "riviluotupvm")
    private Timestamp riviluotupvm;

    @Column(name = "rivimuokkaajatunnus")
    private String rivimuokkaajatunnus;

    public JoinTietoryhmaTietovarantoHistory() {
    }

    public JoinTietoryhmaTietovarantoHistory(int childNode) {
        this.childNode = childNode;
    }

    public JoinTietoryhmaTietovarantoHistory(int childNode, int parentNode) {
        this.childNode = childNode;
        this.parentNode = parentNode;
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
    public HistoryType getHistoriatyyppi() {
        return historiatyyppi;
    }

    @Override
    public void setHistoriatyyppi(HistoryType historiatyyppi) {
        this.historiatyyppi = historiatyyppi;
    }

    @Override
    public String toString() {
        return "JoinTietoryhmaTietovarantoHistory[childNode=" + childNode + ", parentNode=" + parentNode
                + ", historyType=" + historiatyyppi + "]";
    }
}
