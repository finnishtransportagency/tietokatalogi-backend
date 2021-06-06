package fi.liike.rest.Model;

import fi.liike.rest.api.AreaType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "looginen_kasite_arvo")
public class LooginenKasite extends KasiteArvo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "KASITE_WID", unique = true, nullable = false)
    private int id;

    @Column(name = "KASITE")
    private String kasite;

    @Column(name = "ARVO")
    private String arvo;

    @Column(name = "ALUEKOODI")
    @Enumerated(EnumType.STRING)
    private AreaType areaCode;

    public String getAreaCode() {
        return areaCode.name();
    }

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
        return "LooginenKasite: Kasite: " + kasite + " Arvo: " + arvo;
    }

}
