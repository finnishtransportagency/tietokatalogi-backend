package fi.liike.rest.Model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "sovellus_henkilo_rooli")
public class SovellusHenkiloRooli extends JoinHenkiloRooliTable implements java.io.Serializable {
    public static final String systemIdFieldName = "sovellusId";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "joinsovellushenkrooli_id_seq")
    @Column(name = "rivitunnus", unique = true, nullable = false)
    private Integer rivitunnus;

    @Column(name = "henkilo_id")
    private Integer henkiloId;

    @Column(name = "rooli_id")
    private Integer rooliId;

    @Column(name = "sovellus_id")
    private Integer sovellusId;

    @Transient
    private String rivimuokkaajatunnus;

    public SovellusHenkiloRooli() {}

    public SovellusHenkiloRooli(Integer henkiloId, Integer rooliId, Integer sovellusId) {
        this.henkiloId = henkiloId;
        this.rooliId = rooliId;
        this.sovellusId = sovellusId;
    }

    public SovellusHenkiloRooli(Integer henkiloId, Integer rooliId, Integer sovellusId, String rivimuokkaajatunnus) {
        this.henkiloId = henkiloId;
        this.rooliId = rooliId;
        this.sovellusId = sovellusId;
        this.rivimuokkaajatunnus = rivimuokkaajatunnus;
    }

    public SovellusHenkiloRooli(Integer rivitunnus, Integer henkiloId, Integer rooliId, Integer sovellusId) {
        this.rivitunnus = rivitunnus;
        this.henkiloId = henkiloId;
        this.rooliId = rooliId;
        this.sovellusId = sovellusId;
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
        return sovellusId;
    }

    @Override
    public void setSysteemiId(Integer sovellusId) {
        this.sovellusId = sovellusId;
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
        return "SovellusHenkiloRooli{" +
                "rivitunnus=" + rivitunnus +
                ", henkiloId=" + henkiloId +
                ", rooliId=" + rooliId +
                ", sovellusId=" + sovellusId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SovellusHenkiloRooli that = (SovellusHenkiloRooli) o;

        if (rivitunnus != null ? !rivitunnus.equals(that.rivitunnus) : that.rivitunnus != null) return false;
        if (henkiloId != null ? !henkiloId.equals(that.henkiloId) : that.henkiloId != null) return false;
        if (rooliId != null ? !rooliId.equals(that.rooliId) : that.rooliId != null) return false;
        return sovellusId != null ? sovellusId.equals(that.sovellusId) : that.sovellusId == null;
    }

    @Override
    public int hashCode() {
        int result = rivitunnus != null ? rivitunnus.hashCode() : 0;
        result = 31 * result + (henkiloId != null ? henkiloId.hashCode() : 0);
        result = 31 * result + (rooliId != null ? rooliId.hashCode() : 0);
        result = 31 * result + (sovellusId != null ? sovellusId.hashCode() : 0);
        return result;
    }
}
