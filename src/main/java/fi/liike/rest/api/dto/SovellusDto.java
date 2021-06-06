package fi.liike.rest.api.dto;

import fi.liike.rest.api.ContentDto;
import fi.liike.rest.auth.Right;

import java.util.*;

public class SovellusDto extends ContentDto {

    private Integer tunnus;
    private String valmistaja;
    private String aliasNimet;
    private String nimi;
    private String versio;
    private String tuotekoodi;
    private String konfiguraatioVersio;
    private String lisatietoja;
    private String sovelluksenTyyppi;
    private String kielisyys;
    private String kayttojarjestelmavaatimus;
    private String arkkitehtuuri;
    private String alusta;
    private String riippuvuustieto;
    private String liittymatJarjestelmiin;
    private List<FetchHenkiloRooliDto> fetchRooliHenkiloList;
    private String tuotantoonHyvaksymispaiva;
    private String kriittisyys;
    private String elinkaaritieto;
    private Integer poistunut;
    private List<String> noRightsToModify;

    public SovellusDto() {
        mapRightToFields = new HashMap<>();
        mapRightToFields.put(Right.MODIFY_UNSECURED_FIELDS, Collections.singletonList("ALL_FIELDS"));
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

    @Override
    public void setRivimuokkaajatunnus(String header) {

    }

    @Override
    public String getRivimuokkaajatunnus() {
        return null;
    }

    @Override
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }


    @Override
    public void setNoRightsToModify(Set<Right> userRights) {
        this.noRightsToModify = new ArrayList<String>();
        for (Right right : mapRightToFields.keySet()) {
            if (!userRights.contains(right)) {
                noRightsToModify.addAll(mapRightToFields.get(right));
            }
        }
    }

    @Override
    public List<String> getNoRightsToModify() {
        return this.noRightsToModify;
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

    public Integer getPoistunut() {
        return poistunut;
    }

    public void setPoistunut(Integer poistunut) {
        this.poistunut = poistunut;
    }

    public List<FetchHenkiloRooliDto> getFetchHenkiloRooliList() {
        return fetchRooliHenkiloList;
    }

    public void setFetchHenkiloRooliList(List<FetchHenkiloRooliDto> fetchHenkiloRooliList) {
        this.fetchRooliHenkiloList = fetchHenkiloRooliList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SovellusDto that = (SovellusDto) o;

        if (tunnus != null ? !tunnus.equals(that.tunnus) : that.tunnus != null) return false;
        if (valmistaja != null ? !valmistaja.equals(that.valmistaja) : that.valmistaja != null) return false;
        if (aliasNimet != null ? !aliasNimet.equals(that.aliasNimet) : that.aliasNimet != null) return false;
        if (nimi != null ? !nimi.equals(that.nimi) : that.nimi != null) return false;
        if (versio != null ? !versio.equals(that.versio) : that.versio != null) return false;
        if (konfiguraatioVersio != null ? !konfiguraatioVersio.equals(that.konfiguraatioVersio) : that.konfiguraatioVersio != null)
            return false;
        if (lisatietoja != null ? !lisatietoja.equals(that.lisatietoja) : that.lisatietoja != null) return false;
        if (sovelluksenTyyppi != null ? !sovelluksenTyyppi.equals(that.sovelluksenTyyppi) : that.sovelluksenTyyppi != null)
            return false;
        if (kielisyys != null ? !kielisyys.equals(that.kielisyys) : that.kielisyys != null) return false;
        if (kayttojarjestelmavaatimus != null ? !kayttojarjestelmavaatimus.equals(that.kayttojarjestelmavaatimus) : that.kayttojarjestelmavaatimus != null)
            return false;
        if (arkkitehtuuri != null ? !arkkitehtuuri.equals(that.arkkitehtuuri) : that.arkkitehtuuri != null)
            return false;
        if (alusta != null ? !alusta.equals(that.alusta) : that.alusta != null) return false;
        if (riippuvuustieto != null ? !riippuvuustieto.equals(that.riippuvuustieto) : that.riippuvuustieto != null)
            return false;
        if (liittymatJarjestelmiin != null ? !liittymatJarjestelmiin.equals(that.liittymatJarjestelmiin) : that.liittymatJarjestelmiin != null)
            return false;
        if (fetchRooliHenkiloList != null ? !fetchRooliHenkiloList.equals(that.fetchRooliHenkiloList) : that.fetchRooliHenkiloList != null)
            return false;
        if (tuotantoonHyvaksymispaiva != null ? !tuotantoonHyvaksymispaiva.equals(that.tuotantoonHyvaksymispaiva) : that.tuotantoonHyvaksymispaiva != null)
            return false;
        if (kriittisyys != null ? !kriittisyys.equals(that.kriittisyys) : that.kriittisyys != null) return false;
        if (elinkaaritieto != null ? !elinkaaritieto.equals(that.elinkaaritieto) : that.elinkaaritieto != null)
            return false;
        return poistunut != null ? poistunut.equals(that.poistunut) : that.poistunut == null;
    }

    @Override
    public int hashCode() {
        int result = tunnus != null ? tunnus.hashCode() : 0;
        result = 31 * result + (valmistaja != null ? valmistaja.hashCode() : 0);
        result = 31 * result + (aliasNimet != null ? aliasNimet.hashCode() : 0);
        result = 31 * result + (nimi != null ? nimi.hashCode() : 0);
        result = 31 * result + (versio != null ? versio.hashCode() : 0);
        result = 31 * result + (konfiguraatioVersio != null ? konfiguraatioVersio.hashCode() : 0);
        result = 31 * result + (lisatietoja != null ? lisatietoja.hashCode() : 0);
        result = 31 * result + (sovelluksenTyyppi != null ? sovelluksenTyyppi.hashCode() : 0);
        result = 31 * result + (kielisyys != null ? kielisyys.hashCode() : 0);
        result = 31 * result + (kayttojarjestelmavaatimus != null ? kayttojarjestelmavaatimus.hashCode() : 0);
        result = 31 * result + (arkkitehtuuri != null ? arkkitehtuuri.hashCode() : 0);
        result = 31 * result + (alusta != null ? alusta.hashCode() : 0);
        result = 31 * result + (riippuvuustieto != null ? riippuvuustieto.hashCode() : 0);
        result = 31 * result + (liittymatJarjestelmiin != null ? liittymatJarjestelmiin.hashCode() : 0);
        result = 31 * result + (fetchRooliHenkiloList != null ? fetchRooliHenkiloList.hashCode() : 0);
        result = 31 * result + (tuotantoonHyvaksymispaiva != null ? tuotantoonHyvaksymispaiva.hashCode() : 0);
        result = 31 * result + (kriittisyys != null ? kriittisyys.hashCode() : 0);
        result = 31 * result + (elinkaaritieto != null ? elinkaaritieto.hashCode() : 0);
        result = 31 * result + (poistunut != null ? poistunut.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SovellusDto{" +
                "tunnus=" + tunnus +
                ", valmistaja='" + valmistaja + '\'' +
                ", aliasNimet='" + aliasNimet + '\'' +
                ", nimi='" + nimi + '\'' +
                ", versio='" + versio + '\'' +
                ", konfiguraatioVersio='" + konfiguraatioVersio + '\'' +
                ", lisatietoja='" + lisatietoja + '\'' +
                ", sovelluksenTyyppi='" + sovelluksenTyyppi + '\'' +
                ", kielisyys='" + kielisyys + '\'' +
                ", kayttojarjestelmavaatimus='" + kayttojarjestelmavaatimus + '\'' +
                ", arkkitehtuuri='" + arkkitehtuuri + '\'' +
                ", alusta='" + alusta + '\'' +
                ", riippuvuustieto='" + riippuvuustieto + '\'' +
                ", liittymatJarjestelmiin='" + liittymatJarjestelmiin + '\'' +
                ", fetchRooliHenkiloList=" + fetchRooliHenkiloList +
                ", tuotantoonHyvaksymispaiva='" + tuotantoonHyvaksymispaiva + '\'' +
                ", kriittisyys=" + kriittisyys +
                ", elinkaaritieto='" + elinkaaritieto + '\'' +
                ", poistunut=" + poistunut +
                '}';
    }
}
