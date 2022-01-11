package fi.liike.rest.Model;


import javax.persistence.*;
import java.util.Objects;

import static java.lang.String.format;

@Entity
@Table(name = "JOINHUOMAUTUS")
public class TermilomakeJoinHuomautus extends JoinTable implements java.io.Serializable {

    private static  final long serialVersionUID = 1L;
    public static final String seq_name = "JOINHUOMAUTUS_ID_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = seq_name)
    @Column(name = "rivitunnus", unique = true, nullable = false)
    private int rivitunnus;

    @Column(name = "TERMILOMAKETUNNUS")
    private Integer parentNode;

    @Column(name = "HUOMAUTUS")
    private String huomautus;

    @Column(name = "rivitila")
    private String rivitila = "A";

    public TermilomakeJoinHuomautus(Integer parentNode, String huomautus) {
        this.parentNode = parentNode;
        this.huomautus = huomautus;
    }

    public TermilomakeJoinHuomautus() {
    }

    public int getRivitunnus() {
        return rivitunnus;
    }

    public void setRivitunnus(int rivitunnus) {
        this.rivitunnus = rivitunnus;
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

        TermilomakeJoinHuomautus that =
                (TermilomakeJoinHuomautus) o;

        return Objects.equals(parentNode, that.parentNode) &&
               Objects.equals(huomautus, that.huomautus);
    }

    @Override
    public int hashCode() {
        int result = this.parentNode != null ? this.parentNode.hashCode() : 0;
        result = 31 * result + (this.huomautus != null ? this.huomautus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return format("TermilomakeJoinHuomautus [termilomake=%s, " +
                              "huomautus=%s, rivitunnus=%s",
                      parentNode, huomautus, rivitunnus);
    }
}
