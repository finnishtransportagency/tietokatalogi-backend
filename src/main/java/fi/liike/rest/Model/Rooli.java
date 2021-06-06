package fi.liike.rest.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rooli")
public class Rooli extends Haettava implements Serializable, Cloneable {

    @Id
    @Column(name = "TUNNUS", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "rooli_id_seq", allocationSize = 1)
    private Integer tunnus;

    @Column(name = "NIMI")
    private String nimi;

    public Rooli() {}

    public Rooli(Integer tunnus, String nimi) {
        this.tunnus = tunnus;
        this.nimi = nimi;
    }

    public Rooli(String nimi) {
        this.nimi = nimi;
    }

    @Override
    public Integer getTunnus() {
        return tunnus;
    }

    @Override
    public void setTunnus(Integer tunnus) {
        this.tunnus = tunnus;
    }

    @Override
    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    @Override
    public String toString() {
        return "Rooli{" +
                "tunnus=" + tunnus +
                ", nimi='" + nimi + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rooli rooli = (Rooli) o;

        if (!tunnus.equals(rooli.tunnus)) return false;
        return nimi.equals(rooli.nimi);
    }

    @Override
    public int hashCode() {
        int result = tunnus.hashCode();
        result = 31 * result + nimi.hashCode();
        return result;
    }
}
