package fi.liike.rest.api.dto;

import fi.liike.rest.auth.Right;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

public class JarjestelmaGeneralDto extends JarjestelmaFetchMinimalDto {

    private String lyhennetty_nimi;

    private String toimittaja;

    private String toimittajan_yhteyshenkilo;

    private String toimittajan_tekn_yh;

    private String muut_toimittajat;

    private String kayttopalvelun_toimittaja;

    private String omistava_organisaatio;

    private String alfresco_linkki;

    private String kayttoonottovuosi;

    private String poistovuosi;

    private String document_id;

    private String confluence_linkki;

    private String paasynhallinta;

    @JsonIgnore
    private List<FetchHenkiloRooliDto> fetchRooliHenkiloList;

    private List<JarjestelmaLinkkausDto> jarjestelmaLinkkausList;

    public String getLyhennetty_nimi() {
        return lyhennetty_nimi;
    }

    public void setLyhennetty_nimi(String lyhennetty_nimi) {
        this.lyhennetty_nimi = lyhennetty_nimi;
    }

    public String getToimittaja() {
        return toimittaja;
    }

    public void setToimittaja(String toimittaja) {
        this.toimittaja = toimittaja;
    }

    public String getToimittajan_yhteyshenkilo() {
        return toimittajan_yhteyshenkilo;
    }

    public void setToimittajan_yhteyshenkilo(String toimittajan_yhteyshenkilo) {
        this.toimittajan_yhteyshenkilo = toimittajan_yhteyshenkilo;
    }

    public String getToimittajan_tekn_yh() {
        return toimittajan_tekn_yh;
    }

    public void setToimittajan_tekn_yh(String toimittajan_tekn_yh) {
        this.toimittajan_tekn_yh = toimittajan_tekn_yh;
    }

    public String getMuut_toimittajat() {
        return muut_toimittajat;
    }

    public void setMuut_toimittajat(String muut_toimittajat) {
        this.muut_toimittajat = muut_toimittajat;
    }

    public String getKayttopalvelun_toimittaja() {
        return kayttopalvelun_toimittaja;
    }

    public void setKayttopalvelun_toimittaja(String kayttopalvelun_toimittaja) {
        this.kayttopalvelun_toimittaja = kayttopalvelun_toimittaja;
    }

    public String getOmistava_organisaatio() {
        return omistava_organisaatio;
    }

    public void setOmistava_organisaatio(String omistava_organisaatio) {
        this.omistava_organisaatio = omistava_organisaatio;
    }

    public String getAlfresco_linkki() {
        return alfresco_linkki;
    }

    public void setAlfresco_linkki(String alfresco_linkki) {
        this.alfresco_linkki = alfresco_linkki;
    }

    public String getKayttoonottovuosi() {
        return kayttoonottovuosi;
    }

    public void setKayttoonottovuosi(String kayttoonottovuosi) {
        this.kayttoonottovuosi = kayttoonottovuosi;
    }

    public String getPoistovuosi() {
        return poistovuosi;
    }

    public void setPoistovuosi(String poistovuosi) {
        this.poistovuosi = poistovuosi;
    }

    public String getDocument_id() {
        return document_id;
    }

    public void setDocument_id(String document_id) {
        this.document_id = document_id;
    }

    public String getConfluence_linkki() {
        return confluence_linkki;
    }

    public void setConfluence_linkki(String confluence_linkki) {
        this.confluence_linkki = confluence_linkki;
    }

    public String getPaasynhallinta() {
        return paasynhallinta;
    }

    public void setPaasynhallinta(String paasynhallinta) {
        this.paasynhallinta = paasynhallinta;
    }

    public List<FetchHenkiloRooliDto> getFetchRooliHenkiloList() {
        return fetchRooliHenkiloList;
    }

    public void setFetchRooliHenkiloList(List<FetchHenkiloRooliDto> fetchRooliHenkiloList) {
        this.fetchRooliHenkiloList = fetchRooliHenkiloList;
    }

    public void setJarjestelmaLinkkausList(List<JarjestelmaLinkkausDto> jarjestelmaLinkkausList) {
        this.jarjestelmaLinkkausList = jarjestelmaLinkkausList;
    }

    public List<JarjestelmaLinkkausDto> getJarjestelmaLinkkausList() {
        return jarjestelmaLinkkausList;
    }

    @Override
    public void setRivimuokkaajatunnus(String header) {
    }

    @Override
    public String getRivimuokkaajatunnus() {
        return null;
    }

    @Override
    public String toString() {
        return "JarjestelmaGeneralDto{" +
                "tunnus=" + getTunnus() +
                ", nimi='" + getNimi() + '\'' +
                ", kuvaus='" + getKuvaus() + '\'' +
                ", jarjestelmaLinkkausList=" + jarjestelmaLinkkausList +
                ", fetchRooliHenkiloList=" + fetchRooliHenkiloList +
                '}';
    }
}
