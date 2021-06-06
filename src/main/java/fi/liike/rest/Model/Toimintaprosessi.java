package fi.liike.rest.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "TOIMINTAPROSESSI")
public class Toimintaprosessi extends Haettava implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String seq_name = "toimintaprosessi_id_seq";

    @Id
    @Column(name = "toimintaprosessitunnus", unique = true, nullable = false)
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

    public Toimintaprosessi() {
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

    @Override
    public Timestamp getRivimuokattupvm() {
        return rivimuokattupvm;
    }

    @Override
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
}
