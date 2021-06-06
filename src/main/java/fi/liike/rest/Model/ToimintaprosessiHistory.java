package fi.liike.rest.Model;

import fi.liike.rest.api.HistoryType;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "TOIMINTAPROSESSIHISTORIA")
public class ToimintaprosessiHistory implements Serializable, HaettavaHistory {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "toimintaprosessihist_id_seq")
    @Column(name = "rivi_id", unique = true, nullable = false)
    private int rivi_id;

    @Column(name = "HISTORIATYYPPI")
    @Enumerated(EnumType.STRING)
    private HistoryType historiatyyppi;

    @Column(name = "toimintaprosessitunnus")
    private Integer tunnus;

    @Column(name = "nimi")
    private String nimi;

    @Column(name = "vastaava_organisaatio")
    private String vastaava_organisaatio;

    @Column(name = "tarkoitus")
    private String tarkoitus;

    @Column(name = "rivimuokkaajatunnus")
    private String rivimuokkaajatunnus;

    @Column(name = "rivimuokattupvm")
    private Timestamp rivimuokattupvm;

    @Column(name = "riviluotupvm")
    private Timestamp riviluotupvm;

    public int getRivi_id() {
        return rivi_id;
    }

    public void setRivi_id(int rivi_id) {
        this.rivi_id = rivi_id;
    }

    public Integer getTunnus() {
        return tunnus;
    }

    public void setTunnus(Integer tunnus) {
        this.tunnus = tunnus;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getVastaava_organisaatio() {
        return vastaava_organisaatio;
    }

    public void setVastaava_organisaatio(String vastaava_organisaatio) {
        this.vastaava_organisaatio = vastaava_organisaatio;
    }

    public String getTarkoitus() {
        return tarkoitus;
    }

    public void setTarkoitus(String tarkoitus) {
        this.tarkoitus = tarkoitus;
    }

    @Override
    public String getRivimuokkaajatunnus() {
        return rivimuokkaajatunnus;
    }

    @Override
    public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
        this.rivimuokkaajatunnus = rivimuokkaajatunnus;
    }

    public Timestamp getRivimuokattupvm() {
        return rivimuokattupvm;
    }

    public void setRivimuokattupvm(Timestamp rivimuokattupvm) {
        this.rivimuokattupvm = rivimuokattupvm;
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
    public HistoryType getHistoriatyyppi() {
        return historiatyyppi;
    }

    @Override
    public void setHistoriatyyppi(HistoryType historiatyyppi) {
        this.historiatyyppi = historiatyyppi;
    }
}
