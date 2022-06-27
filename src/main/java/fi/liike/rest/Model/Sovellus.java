package fi.liike.rest.Model;

import com.google.common.collect.ImmutableList;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "SOVELLUS")
public class Sovellus extends Haettava implements Serializable, Cloneable {
    public static final String seqName = "sovellus_id_seq";

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TUNNUS", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = seqName)
    private Integer tunnus;

    @Column(name = "NIMI", nullable = false)
    private String nimi;

    @Column(name = "VALMISTAJA")
    private String valmistaja;

    @Column(name = "ALIAS_NIMET")
    private String aliasNimet;

    @Column(name = "VERSIO")
    private String versio;

    @Column(name = "TUOTEKOODI")
    private String tuotekoodi;

    @Column(name = "KONFIGURAATIO_VERSIO")
    private String konfiguraatioVersio;

    @Column(name = "LISATIETOJA")
    private String lisatietoja;

    @Column(name = "SOVELLUS_TYYPPI")
    private String sovellusTyyppi;

    @Column(name = "KIELISYYS")
    private String kielisyys;

    @Column(name = "KAYTTOJARJESTELMAVAATIMUS")
    private String kayttojarjestelmaVaatimus;

    @Column(name = "ARKKITEHTUURI")
    private String arkkitehtuuri;

    @Column(name = "ALUSTA")
    private String alusta;

    @Column(name = "RIIPPUVUUSTIETO")
    private String riippuvuustieto;

    @Column(name = "LIITTYMAT_JARJESTELMIIN")
    private String liittymatJarjestelmiin;

    @Column(name = "TUOTANTOON_HYVAKSYMISPAIVA")
    private Date tuotantoonHyvaksymisPaiva;

    @Column(name = "KRIITTISYYS")
    private String kriittisyys;

    @Column(name = "ELINKAARITIETO", updatable = false)
    private String elinkaaritieto;

    @Column(name = "POISTUNUT", nullable = false)
    private Integer poistunut;

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

    public String getValmistaja() {
        return valmistaja;
    }

    public void setValmistaja(String valmistaja) {
        this.valmistaja = valmistaja;
    }

    public String getAliasNimet() {
        return aliasNimet;
    }

    public void setAliasNimet(String aliasNimet) {
        this.aliasNimet = aliasNimet;
    }

    public String getVersio() {
        return versio;
    }

    public void setVersio(String versio) {
        this.versio = versio;
    }

    public String getTuotekoodi() {
        return tuotekoodi;
    }

    public void setTuotekoodi(String tuotekoodi) {
        this.tuotekoodi = tuotekoodi;
    }

    public String getKonfiguraatioVersio() {
        return konfiguraatioVersio;
    }

    public void setKonfiguraatioVersio(String konfiguraatioVersio) {
        this.konfiguraatioVersio = konfiguraatioVersio;
    }

    public String getLisatietoja() {
        return lisatietoja;
    }

    public void setLisatietoja(String lisatietoja) {
        this.lisatietoja = lisatietoja;
    }

    public String getSovellusTyyppi() {
        return sovellusTyyppi;
    }

    public void setSovellusTyyppi(String sovellusTyyppi) {
        this.sovellusTyyppi = sovellusTyyppi;
    }

    public String getKielisyys() {
        return kielisyys;
    }

    public void setKielisyys(String kielisyys) {
        this.kielisyys = kielisyys;
    }

    public String getKayttojarjestelmaVaatimus() {
        return kayttojarjestelmaVaatimus;
    }

    public void setKayttojarjestelmaVaatimus(String kayttojarjestelmaVaatimus) {
        this.kayttojarjestelmaVaatimus = kayttojarjestelmaVaatimus;
    }

    public String getArkkitehtuuri() {
        return arkkitehtuuri;
    }

    public void setArkkitehtuuri(String arkkitehtuuri) {
        this.arkkitehtuuri = arkkitehtuuri;
    }

    public String getAlusta() {
        return alusta;
    }

    public void setAlusta(String alusta) {
        this.alusta = alusta;
    }

    public String getRiippuvuustieto() {
        return riippuvuustieto;
    }

    public void setRiippuvuustieto(String riippuvuustieto) {
        this.riippuvuustieto = riippuvuustieto;
    }

    public String getLiittymatJarjestelmiin() {
        return liittymatJarjestelmiin;
    }

    public void setLiittymatJarjestelmiin(String liittymatJarjestelmiin) {
        this.liittymatJarjestelmiin = liittymatJarjestelmiin;
    }

    public Date getTuotantoonHyvaksymisPaiva() {
        return tuotantoonHyvaksymisPaiva;
    }

    public void setTuotantoonHyvaksymisPaiva(Date tuotantoonHyvaksymisPaiva) {
        this.tuotantoonHyvaksymisPaiva = tuotantoonHyvaksymisPaiva;
    }

    public String getKriittisyys() {
        return kriittisyys;
    }

    public void setKriittisyys(String kriittisyys) {
        this.kriittisyys = kriittisyys;
    }

    public String getElinkaaritieto() {
        return elinkaaritieto;
    }

    public void setElinkaaritieto(String elinkaaritieto) {
        this.elinkaaritieto = elinkaaritieto;
    }

    public Timestamp getRivimuokattupvm() {
        return null;
    }

    public void setRivimuokattupvm(Timestamp timestamp) {
    }

    public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
    }

    public String getRivimuokkaajatunnus() {
        return null;
    }

    public Integer getPoistunut() {
        return poistunut;
    }

    public void setPoistunut(Integer poistunut) {
        this.poistunut = poistunut;
    }

}
