package fi.liike.rest.Model;

import fi.liike.rest.api.HistoryType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "JARJESTELMA_HENKILO_ROOLI_HIST")
public class JarjestelmaHenkiloRooliHistory extends JoinHenkiloRooliTableHistory {

    @Id
    @Column(name = "rivitunnus", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "jarjhenkroolihist_id_seq")
    private Integer rivitunnus;

    @Column(name = "henkilo_id")
    private Integer henkiloId;

    @Column(name = "rooli_id")
    private Integer rooliId;

    @Column(name = "jarjestelma_id")
    private Integer jarjestelmaId;

    @Column(name = "HISTORIATYYPPI")
    @Enumerated(EnumType.STRING)
    private HistoryType historiatyyppi;

    @Column(name = "riviluotupvm")
    private Timestamp riviluotupvm;

    @Column(name = "rivimuokkaajatunnus")
    private String rivimuokkaajatunnus;

    public JarjestelmaHenkiloRooliHistory() {}

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
    public void setSysteemiId(Integer systeemiId) {
        this.jarjestelmaId = systeemiId;
    }

    @Override
    public HistoryType getHistoriatyyppi() {
        return historiatyyppi;
    }

    @Override
    public void setHistoriatyyppi(HistoryType historiatyyppi) {
        this.historiatyyppi = historiatyyppi;
    }

    @Override
    public Timestamp getRiviluotupvm() {
        return riviluotupvm;
    }

    @Override
    public void setRiviluotupvm(Timestamp riviluotupvm) {
        this.riviluotupvm = riviluotupvm;
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
        return "JarjestelmaHenkiloRooliHistory{" +
                "rivitunnus=" + rivitunnus +
                ", henkiloId=" + henkiloId +
                ", rooliId=" + rooliId +
                ", jarjestelmaId=" + jarjestelmaId +
                ", historiatyyppi=" + historiatyyppi +
                ", riviluotupvm=" + riviluotupvm +
                ", rivimuokkaajatunnus='" + rivimuokkaajatunnus + '\'' +
                '}';
    }
}
