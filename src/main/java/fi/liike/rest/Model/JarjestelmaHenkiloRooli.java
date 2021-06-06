package fi.liike.rest.Model;

import javax.persistence.*;

@Entity
@Table(name = "jarjestelma_henkilo_rooli")
public class JarjestelmaHenkiloRooli extends JoinHenkiloRooliTable implements java.io.Serializable {
    public static final String systemIdFieldName = "jarjestelmaId";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "joinjarjhenkrooli_id_seq")
    @Column(name = "rivitunnus", unique = true, nullable = false)
    private Integer rivitunnus;

    @Column(name = "henkilo_id")
    private Integer henkiloId;

    @Column(name = "rooli_id")
    private Integer rooliId;

    @Column(name = "jarjestelma_id")
    private Integer jarjestelmaId;

    @Transient
    private String rivimuokkaajatunnus;

    public JarjestelmaHenkiloRooli() {}

    public JarjestelmaHenkiloRooli(Integer henkiloId, Integer rooliId, Integer jarjestelmaId) {
        this.henkiloId = henkiloId;
        this.rooliId = rooliId;
        this.jarjestelmaId = jarjestelmaId;
    }

    public JarjestelmaHenkiloRooli(Integer henkiloId, Integer rooliId, Integer jarjestelmaId, String rivimuokkaajatunnus) {
        this.henkiloId = henkiloId;
        this.rooliId = rooliId;
        this.jarjestelmaId = jarjestelmaId;
        this.rivimuokkaajatunnus = rivimuokkaajatunnus;
    }

    public JarjestelmaHenkiloRooli(Integer rivitunnus, Integer henkiloId, Integer rooliId, Integer jarjestelmaId) {
        this.rivitunnus = rivitunnus;
        this.henkiloId = henkiloId;
        this.rooliId = rooliId;
        this.jarjestelmaId = jarjestelmaId;
    }

    @Override
    public Integer getRivitunnus() {
        return rivitunnus;
    }

    @Override
    public void setRivitunnus(Integer rivitunnus) {
        this.rivitunnus = rivitunnus;
    }

    @Override
    public Integer getHenkiloId() {
        return henkiloId;
    }

    @Override
    public void setHenkiloId(Integer henkiloId) {
        this.henkiloId = henkiloId;
    }

    @Override
    public Integer getRooliId() {
        return rooliId;
    }

    @Override
    public void setRooliId(Integer rooliId) {
        this.rooliId = rooliId;
    }

    @Override
    public Integer getSysteemiId() {
        return jarjestelmaId;
    }

    @Override
    public void setSysteemiId(Integer jarjestelmaId) {
        this.jarjestelmaId = jarjestelmaId;
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
    public String toString() {
        return "JarjestelmaHenkiloRooli{" +
                "rivitunnus=" + rivitunnus +
                ", henkiloId=" + henkiloId +
                ", rooliId=" + rooliId +
                ", jarjestelmaId=" + jarjestelmaId +
                '}';
    }
}
