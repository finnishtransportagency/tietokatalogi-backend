package fi.liike.rest.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "TIETOVARANTOREKISTEROITYRYHMA")
public class JoinTietovarantoRekisteroityRyhma extends JoinTietovarantoAttribute implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "TIETOVARANTOREKISTRYHMA_ID_SEQ")
    @Column(name = "rivitunnus", unique = true, nullable = false)
    private Integer rivi_id;

    @Column(name = "tietovarantotunnus")
    private Integer parentNode;

    @Column(name = "rekisteroityryhma")
    private String attribuuttiarvo;

    public JoinTietovarantoRekisteroityRyhma() {
    }

    public JoinTietovarantoRekisteroityRyhma(Integer parentNode, String attribuuttiarvo) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JoinTietovarantoRekisteroityRyhma that = (JoinTietovarantoRekisteroityRyhma) o;

        return Objects.equals(parentNode, that.parentNode) && Objects.equals(attribuuttiarvo, that.attribuuttiarvo);
    }

    @Override
    public int hashCode() {
        int result = parentNode != null ? parentNode.hashCode() : 0;
        result = 31 * result + (attribuuttiarvo != null ? attribuuttiarvo.hashCode() : 0);
        return result;
    }
}
