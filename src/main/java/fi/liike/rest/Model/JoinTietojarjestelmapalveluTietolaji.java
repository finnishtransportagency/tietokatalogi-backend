package fi.liike.rest.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TIETOJARJPALVELU_TIETOLAJI")
public class JoinTietojarjestelmapalveluTietolaji extends JoinTable implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "tietojarjpalvelutieto_id_seq")
    @Column(name = "RIVITUNNUS", unique = true, nullable = false)
    private int rivi_id;

    @Column(name = "TIETOJARJESTELMAPALVELUTUNNUS")
    private Integer childNode;

    @Column(name = "TIETOLAJITUNNUS")
    private Integer parentNode;

    @Column(name = "RIVITILA")
    private String rivitila = "A";

    @Column(name = "LIITTYVA_JARJESTELMA")
    private Integer liittyvaJarjestelma;

    public JoinTietojarjestelmapalveluTietolaji(Integer childNode) {
        this.childNode = childNode;
    }

    public JoinTietojarjestelmapalveluTietolaji() {
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
        return rivitila;
    }

    @Override
    public void setRivitila(String rivitila) {
        this.rivitila = rivitila;
    }

    public Integer getLiittyvaJarjestelma() {
        return liittyvaJarjestelma;
    }

    public void setLiittyvaJarjestelma(Integer liittyvaJarjestelma) {
        this.liittyvaJarjestelma = liittyvaJarjestelma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JoinTietojarjestelmapalveluTietolaji that = (JoinTietojarjestelmapalveluTietolaji) o;

        if (!childNode.equals(that.childNode)) return false;
        if (!parentNode.equals(that.parentNode)) return false;
        return Objects.equals(liittyvaJarjestelma, that.liittyvaJarjestelma);
    }

    public int hashCode() {
        int result = parentNode != null ? parentNode.hashCode() : 0;
        result = 31 * result + (childNode != null ? childNode.hashCode() : 0);
        result = 31 * result + (liittyvaJarjestelma != null ? liittyvaJarjestelma.hashCode() : 0);
        return result;
    }
}

