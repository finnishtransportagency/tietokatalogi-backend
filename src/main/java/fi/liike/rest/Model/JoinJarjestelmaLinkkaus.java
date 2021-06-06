package fi.liike.rest.Model;

import javax.persistence.*;
import java.io.Serializable;

import static java.lang.String.format;

@Entity
@Table(name = "jarjestelmalinkkaus")
public class JoinJarjestelmaLinkkaus extends JoinTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "joinjarjestelmalinkkaus_id_seq")
    @Column(name = "rivitunnus", unique = true, nullable = false)
    private Integer rivitunnus;

    @Column(name = "LINKATTAVA_ID")
    private Integer childNode;

    @Column(name = "TIETOJARJESTELMATUNNUS")
    private Integer parentNode;

    @Column(name = "rivitila")
    private String rivitila = "A";

    @Column(name = "SUUNTA")
    private String suunta;

    @Column(name = "TIETOJARJESTELMAPALVELUTUNNUS")
    private Integer tietojarjestelmapalveluTunnus;

    @Column(name = "TYYPPI")
    private String tyyppi;

    @Column(name = "KUVAUS")
    private String kuvaus;

    public JoinJarjestelmaLinkkaus() {
    }

    public JoinJarjestelmaLinkkaus(int childNode) {
        this.childNode = childNode;
    }

    public void setSuunta(String suunta) {
        this.suunta = suunta;
    }

    public String getSuunta() {
        return suunta;
    }

    public Integer getTietojarjestelmapalveluTunnus() {
        return tietojarjestelmapalveluTunnus;
    }

    public void setTietojarjestelmapalveluTunnus(Integer tietojarjestelmapalveluTunnus) {
        this.tietojarjestelmapalveluTunnus = tietojarjestelmapalveluTunnus;
    }

    public void setTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    public String getTyyppi() {
        return tyyppi;
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
    public void setRivitila(String rivitila) {
        this.rivitila = rivitila;
    }

    @Override
    public String getRivitila() {
        return rivitila;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public Integer getRivitunnus() {
        return rivitunnus;
    }

    public void setRivitunnus(Integer rivitunnus) {
        this.rivitunnus = rivitunnus;
    }

    @Override
    public String toString() {
        return format("JarjestelmaLinkkausDto [tietojarjestelmaTunnus=%s, linkattavaTunnus=%s, suunta=%s, tietojarjestelmapalveluTunnus=%s, tyyppi=%s, rivitunnus=%s, kuvaus=%s",
                parentNode, childNode, suunta, tietojarjestelmapalveluTunnus, tyyppi, rivitunnus, kuvaus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JoinJarjestelmaLinkkaus that = (JoinJarjestelmaLinkkaus) o;

        if (!rivitunnus.equals(that.rivitunnus)) return false;
        if (!childNode.equals(that.childNode)) return false;
        if (!parentNode.equals(that.parentNode)) return false;
        if (!rivitila.equals(that.rivitila)) return false;
        if (!suunta.equals(that.suunta)) return false;
        if (tietojarjestelmapalveluTunnus != null ? !tietojarjestelmapalveluTunnus.equals(that.tietojarjestelmapalveluTunnus) : that.tietojarjestelmapalveluTunnus != null) return false;
        if (!tyyppi.equals(that.tyyppi)) return false;
        return kuvaus != null ? kuvaus.equals(that.kuvaus) : that.kuvaus == null;
    }

    @Override
    public int hashCode() {
        int result = rivitunnus.hashCode();
        result = 31 * result + childNode.hashCode();
        result = 31 * result + parentNode.hashCode();
        result = 31 * result + rivitila.hashCode();
        result = 31 * result + suunta.hashCode();
        result = 31 * result + tietojarjestelmapalveluTunnus.hashCode();
        result = 31 * result + tyyppi.hashCode();
        result = 31 * result + (kuvaus != null ? kuvaus.hashCode() : 0);
        return result;
    }
}
