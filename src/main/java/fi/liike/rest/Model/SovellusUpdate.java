package fi.liike.rest.Model;

import javax.persistence.*;

@Entity
@Table(name = "SOVELLUS")
public class SovellusUpdate extends Haettava {
    private static final String seqName = "sovellus_id_seq";

    @Id
    @Column(name = "TUNNUS", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = seqName)
    private Integer tunnus;

    @Column(name = "ELINKAARITIETO")
    private String elinkaaritieto;

    @Column(name = "POISTUNUT")
    private Integer poistunut;

    public Integer getTunnus() {
        return tunnus;
    }

    public void setTunnus(Integer tunnus) {
        this.tunnus = tunnus;
    }

    public String getElinkaaritieto() {
        return elinkaaritieto;
    }

    public void setElinkaaritieto(String elinkaaritieto) {
        this.elinkaaritieto = elinkaaritieto;
    }

    public Integer getPoistunut() {
        return poistunut;
    }

    public void setPoistunut(Integer poistunut) {
        this.poistunut = poistunut;
    }

    @Override
    public String toString() {
        return "SovellusUpdate{" +
                "tunnus=" + tunnus +
                ", elinkaaritieto='" + elinkaaritieto + '\'' +
                ", poistunut='" + poistunut + '\'' +
                '}';
    }
}
