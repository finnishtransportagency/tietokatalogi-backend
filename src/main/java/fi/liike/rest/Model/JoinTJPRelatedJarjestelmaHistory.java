package fi.liike.rest.Model;

import fi.liike.rest.api.HistoryType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "TIETOJARJPALVLIITJARJHISTORIA")
public class JoinTJPRelatedJarjestelmaHistory implements JoinHistory, java.io.Serializable  {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "TJPLIITTYVAJARJHIST_ID_SEQ")
    @Column(name = "rivi_id", unique = true, nullable = false)
    private Integer rivi_id;


    @Column(name = "liittyvajarjestelmatunnus")
    private Integer liittyvaJarjestelmatunnus;

    @Column(name = "tietojarjpalvelutunnus")
    private Integer tietojarjpalvelutunnus;

    @Column(name = "HISTORIATYYPPI")
    @Enumerated(EnumType.STRING)
    private HistoryType historiatyyppi;

    @Column(name = "riviluotupvm")
    private Timestamp riviluotupvm;

    @Column(name = "rivimuokkaajatunnus")
    private String rivimuokkaajatunnus;

    public JoinTJPRelatedJarjestelmaHistory() {
    }

    public Integer getRivi_id() {
        return rivi_id;
    }

    public void setRivi_id(Integer rivi_id) {
        this.rivi_id = rivi_id;
    }

    public Integer getLiittyvaJarjestelmatunnus() {
        return liittyvaJarjestelmatunnus;
    }

    public void setLiittyvaJarjestelmatunnus(Integer liittyvaJarjestelmatunnus) {
        this.liittyvaJarjestelmatunnus = liittyvaJarjestelmatunnus;
    }

    public Integer getTietojarjpalvelutunnus() {
        return tietojarjpalvelutunnus;
    }

    public void setTietojarjpalvelutunnus(Integer tietojarjpalvelutunnus) {
        this.tietojarjpalvelutunnus = tietojarjpalvelutunnus;
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
    public Integer getChildNode() {
        return getTietojarjpalvelutunnus();
    }

    @Override
    public void setChildNode(Integer childId) {
        setTietojarjpalvelutunnus(childId);
    }

    @Override
    public Integer getParentNode() {
        return getLiittyvaJarjestelmatunnus();
    }

    @Override
    public void setParentNode(Integer parentId) {
        setLiittyvaJarjestelmatunnus(parentId);
    }

    @Override
    public Long getRivitunnus() {
        return null;
    }
}
