package fi.liike.rest.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIETORYHMATIETOVARANTO")
public class JoinTietoryhmaTietovaranto extends JoinTable implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "TIETORYHMATIETOVARANTO_ID_SEQ")
    @Column(name = "rivitunnus", unique = true, nullable = false)
    private int rivi_id;

    @Column(name = "tietoryhmatunnus")
    private Integer childNode;

    @Column(name = "tietovarantotunnus")
    private Integer parentNode;

    public JoinTietoryhmaTietovaranto() {
    }

    public JoinTietoryhmaTietovaranto(int childNode) {
        this.childNode = childNode;
    }

    public JoinTietoryhmaTietovaranto(int childNode, int parentNode) {
        this.childNode = childNode;
        this.parentNode = parentNode;
    }

    public int getRivi_id() {
        return rivi_id;
    }

    public void setRivi_id(int rivi_id) {
        this.rivi_id = rivi_id;
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
    public String getRivitila() {
        return null;
    }

    @Override
    public void setRivitila(String rivitila) {
    }

    @Override
    public String toString() {
        return "JoinTietoryhmaTietovaranto[childNode=" + childNode + ", parentNode=" + parentNode + "]";
    }
}
