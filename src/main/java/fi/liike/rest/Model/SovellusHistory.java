package fi.liike.rest.Model;

import fi.liike.rest.api.HistoryType;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "SOVELLUS_HISTORY")
public class SovellusHistory implements Serializable, HaettavaHistory {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "sovellus_hist_id_seq")
    @Column(name = "RIVITUNNUS", unique = true, nullable = false)
    private Integer rivitunnus;

    @Column(name = "TUNNUS")
    private Integer tunnus;

    @Column(name = "ELINKAARITIETO")
    private String elinkaaritieto;

    @Column(name = "POISTUNUT")
    private Integer poistunut;

    @Column(name = "HISTORIATYYPPI")
    @Enumerated(EnumType.STRING)
    private HistoryType historiatyyppi;

    @Column(name = "rivimuokkaajatunnus")
    private String rivimuokkaajatunnus;

    public SovellusHistory() {}

    public Integer getRivitunnus() {
        return rivitunnus;
    }

    public void setRivitunnus(Integer rivitunnus) {
        this.rivitunnus = rivitunnus;
    }

    public Integer getTunnus() {
        return tunnus;
    }

    public void setTunnus(Integer sovellusTunnus) {
        this.tunnus = sovellusTunnus;
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
    public HistoryType getHistoriatyyppi() {
        return historiatyyppi;
    }

    @Override
    public void setHistoriatyyppi(HistoryType historiatyyppi) {
        this.historiatyyppi = historiatyyppi;
    }

    @Override
    public Timestamp getRiviluotupvm() {
        return null;
    }

    @Override
    public void setRiviluotupvm(Timestamp riviluotupvm) {

    }

    @Override
    public void setRivimuokkaajatunnus(String remoteUser) {
        this.rivimuokkaajatunnus = remoteUser;
    }

    @Override
    public String getRivimuokkaajatunnus() {
        return rivimuokkaajatunnus;
    }

    @Override
    public String toString() {
        return "SovellusHistory{" +
                "rivitunnus=" + rivitunnus +
                ", tunnus=" + tunnus +
                ", elinkaaritieto='" + elinkaaritieto + '\'' +
                ", poistunut='" + poistunut + '\'' +
                ", historiatyyppi=" + historiatyyppi +
                ", rivimuokkaajatunnus='" + rivimuokkaajatunnus + '\'' +
                '}';
    }
}
