package fi.liike.rest.Model;

import javax.persistence.*;

@Entity
@Table(name = "TIETOJARJPALVLIITTYVAJARJ")
public class JoinTJPRelatedJarjestelma extends JoinTable implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "TIETOJARJPALVLIITJARJ_ID_SEQ")
    @Column(name = "rivitunnus", unique = true, nullable = false)
    private Integer rivi_id;

    @Column(name = "liittyvajarjestelmatunnus")
    private Integer parentNode;

    @Column(name = "tietojarjpalvelutunnus")
    private Integer childNode;

    public JoinTJPRelatedJarjestelma() {
    }

    public JoinTJPRelatedJarjestelma(Integer childNode) {
        this.childNode = childNode;
    }

    public JoinTJPRelatedJarjestelma(Integer parentNode, Integer childNode) {
        this.parentNode = parentNode;
        this.childNode = childNode;
    }

    public Integer getRivi_id() {
        return rivi_id;
    }

    public void setRivi_id(Integer rivi_id) {
        this.rivi_id = rivi_id;
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
    public void setRivitila(String rivitila) {

    }

    @Override
    public String getRivitila() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JoinTJPRelatedJarjestelma that = (JoinTJPRelatedJarjestelma) o;

        if (!childNode.equals(that.childNode)) return false;
        return parentNode.equals(that.parentNode);
    }

    @Override
    public int hashCode() {
        int result = parentNode != null ? parentNode.hashCode() : 0;
        result = 31 * result + (childNode != null ? childNode.hashCode() : 0);
        return result;
    }
}
