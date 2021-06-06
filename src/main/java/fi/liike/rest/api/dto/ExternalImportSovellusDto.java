package fi.liike.rest.api.dto;

import java.util.List;

public class ExternalImportSovellusDto {

    private String nimi;
    private String valmistaja;
    private String aliasNimet;
    private String versio;
    private String signature;
    private String konfiguraatioVersio;
    private String lisatietoja;
    private String sovelluksenTyyppi;
    private String kielisyys;
    private String kayttojarjestelmavaatimus;
    private String arkkitehtuuri;
    private String alusta;
    private String riippuvuustieto;
    private String liittymatJarjestelmiin;
    private List<String> tuotantoonHyvaksynyt;
    private List<String> asennuksenHyvaksyja;
    private String tuotantoonHyvaksymispaiva;
    private String kriittisyys;

    public ExternalImportSovellusDto() {}

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

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getVersio() {
        return versio;
    }

    public void setVersio(String versio) {
        this.versio = versio;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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

    public String getSovelluksenTyyppi() {
        return sovelluksenTyyppi;
    }

    public void setSovelluksenTyyppi(String sovelluksenTyyppi) {
        this.sovelluksenTyyppi = sovelluksenTyyppi;
    }

    public String getKielisyys() {
        return kielisyys;
    }

    public void setKielisyys(String kielisyys) {
        this.kielisyys = kielisyys;
    }

    public String getKayttojarjestelmavaatimus() {
        return kayttojarjestelmavaatimus;
    }

    public void setKayttojarjestelmavaatimus(String kayttojarjestelmavaatimus) {
        this.kayttojarjestelmavaatimus = kayttojarjestelmavaatimus;
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

    public String getTuotantoonHyvaksymispaiva() {
        return tuotantoonHyvaksymispaiva;
    }

    public void setTuotantoonHyvaksymispaiva(String tuotantoonHyvaksymispaiva) {
        this.tuotantoonHyvaksymispaiva = tuotantoonHyvaksymispaiva;
    }

    public List<String> getTuotantoonHyvaksynyt() {
        return tuotantoonHyvaksynyt;
    }

    public void setTuotantoonHyvaksynyt(List<String> tuotantoonHyvaksynyt) {
        this.tuotantoonHyvaksynyt = tuotantoonHyvaksynyt;
    }

    public List<String> getAsennuksenHyvaksyja() {
        return asennuksenHyvaksyja;
    }

    public void setAsennuksenHyvaksyja(List<String> asennuksenHyvaksyja) {
        this.asennuksenHyvaksyja = asennuksenHyvaksyja;
    }

    public String getKriittisyys() {
        return kriittisyys;
    }

    public void setKriittisyys(String kriittisyys) {
        this.kriittisyys = kriittisyys;
    }

}
