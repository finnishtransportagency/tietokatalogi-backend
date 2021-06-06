package fi.liike.rest.Model;

import javax.persistence.*;
import java.util.Objects;

import static java.lang.String.format;

@Entity
@Table(name = "TERMILOMAKEASSOSKASITE")
public class TermilomakeJoinAssosiatiivinenKasite extends JoinTable implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    public static final String seq_name = "TERMILOMAKEASSOSKASITE_ID_SEQ";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = seq_name)
    @Column(name = "rivitunnus", unique = true, nullable = false)
    private int rivitunnus;

    @Column(name = "assosiatiivinen_ylakasite")
    private Integer parentNode;

    @Column(name = "assosiatiivinen_alakasite")
    private Integer childNode;

    @Column(name = "rivitila")
    private String rivitila = "A";

    public TermilomakeJoinAssosiatiivinenKasite() {}

    public TermilomakeJoinAssosiatiivinenKasite(Integer termilomakeTunnus, Integer parentNode) {
        this.parentNode = parentNode;
        this.childNode = termilomakeTunnus;
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
        this.rivitila = rivitila;
    }

    @Override
    public String getRivitila() {
        return this.rivitila;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TermilomakeJoinAssosiatiivinenKasite that =
                (TermilomakeJoinAssosiatiivinenKasite) o;

        return Objects.equals(parentNode, that.parentNode) &&
                Objects.equals(childNode, that.childNode);
    }

    @Override
    public int hashCode() {
        int result = this.parentNode != null ? this.parentNode.hashCode() : 0;
        result = 31 * result + (this.childNode != null ? this.childNode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return format("TermilomakeAssosiatiivinenKasiteDto [assosiatiivinenYlakasite=%s, " +
                              "assosiatiivinenAlakasite=%s, rivitunnus=%s",
                      parentNode, childNode, rivitunnus);
    }
}
