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
@Table(name = "TIETOVARANTOREKISRYHMAHISTORIA")
public class JoinTietovarantoRekisteroityRyhmaHistory extends JoinTietovarantoAttributeHistory implements JoinHistory, java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "TIETOVARANTREKRYHMAHIST_ID_SEQ")
    @Column(name = "rivi_id", unique = true, nullable = false)
    private Integer rivi_id;

    @Column(name = "tietovarantotunnus")
    private Integer parentNode;

    @Column(name = "rekisteroityryhma")
    private String attribuuttiarvo;

    @Column(name = "historiatyyppi")
    @Enumerated(EnumType.STRING)
    private HistoryType historiatyyppi;

    @Column(name = "riviluotupvm")
    private Timestamp riviluotupvm;

    @Column(name = "rivimuokkaajatunnus")
    private String rivimuokkaajatunnus;

    public JoinTietovarantoRekisteroityRyhmaHistory() {
    }

    public JoinTietovarantoRekisteroityRyhmaHistory(Integer parentNode, String attribuuttiarvo) {
        this.parentNode = parentNode;
        this.attribuuttiarvo = attribuuttiarvo;
    }

    @Override
    public Integer getRivi_id() {
        return rivi_id;
    }

    @Override
    public void setRivi_id(Integer rivi_id) {
        this.rivi_id = rivi_id;
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
    public String getAttribuuttiarvo() {
        return attribuuttiarvo;
    }

    @Override
    public void setAttribuuttiarvo(String attribuuttiarvo) {
        this.attribuuttiarvo = attribuuttiarvo;
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
        return null;
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
    public void setRivitila(String rivitila) {
        // Not used
    }

    @Override
    public String getRivitila() {
        // Not used
        return null;
    }
}
