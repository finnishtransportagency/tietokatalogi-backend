package fi.liike.rest.Model;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * A convenience view that appends jarjestelman_nimi to the tieto-omaisuus.
 * This is intended to make a list of tieto-omaisuus entities more readable since these
 * don't have a proper name of their own.
 */
@Entity
@Immutable
@Table(name="tietoomaisuus_jarjestelma_nimi")
public class TietoomaisuusFetch extends Tietoomaisuus {

    @Column(name="JARJESTELMAN_NIMI")
    private String jarjestelman_nimi;


    public String getJarjestelman_nimi() {
        return jarjestelman_nimi;
    }
}
