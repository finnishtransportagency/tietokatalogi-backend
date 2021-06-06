package fi.liike.rest.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tietoomaisuus_kasite_arvo")
public class TietoomaisuusKasite extends ScoredKasiteArvo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "KASITE_WID", unique = true, nullable = false)
    private int id;

    @Column(name = "KASITE")
    private String kasite;

    @Column(name = "ARVO")
    private String arvo;

    @Column(name = "PISTEYTYS")
    private Integer pisteytys;

    @Override
    public String getKasite() {
        return kasite;
    }

    @Override
    public String getArvo() {
        return arvo;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Integer getPisteytys() {
        return pisteytys;
    }

    public void setPisteytys(Integer pisteytys) {
        this.pisteytys = pisteytys;
    }

    @Override
    public String toString() {
        return "Tieto-omaisuusKasite: Kasite: " + kasite + " Arvo: " + arvo + " Pisteytys: " + pisteytys;
    }

}
