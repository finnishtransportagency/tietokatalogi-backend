package fi.liike.rest.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tieto_kasite_arvo")
public class TietolajiKasite extends KasiteArvo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "KASITE_WID", unique = true, nullable = false)
    private int id;

    @Column(name = "KASITE")
    private String kasite;

    @Column(name = "ARVO")
    private String arvo;

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

    @Override
    public String toString() {
        return "TietolajiKasite: Kasite: " + kasite + " Arvo: " + arvo;
    }

}
