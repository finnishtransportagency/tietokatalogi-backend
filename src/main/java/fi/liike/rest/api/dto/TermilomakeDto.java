package fi.liike.rest.api.dto;

import java.util.List;

public class TermilomakeDto extends ContentDtoWithRights {

    private Integer tunnus;
    private String nimi;
    private String ensisij_termi;
    private String synonyymit;
    private String ei_suosit_termi;
    private String kayttoaluerajaus;
    private String maaritelma;
    private String kommentit;
    private String valmis;
    private String lahde;
    private String omistaja;
    private String ydinkasite;
    private String hakutermit;

    private String rivimuokkaajatunnus;

    private List<Integer> hierarkk_ylakasite;
    private List<Integer> koostumussuht_ylakasite;
    private List<Integer> assosiatiiv_kasite;


    private List<String> huomautusList;

    public List<String> gethuomautusList() {
        return huomautusList;
    }

    public void sethuomautusList(List<String> huomautusList) {
        this.huomautusList = huomautusList;
    }

    public String getEnsisij_termi() {
        return ensisij_termi;
    }

    public void setEnsisij_termi(String ensisij_termi) {
        this.ensisij_termi = ensisij_termi;
    }

    public String getSynonyymit() {
        return synonyymit;
    }

    public void setSynonyymit(String synonyymit) {
        this.synonyymit = synonyymit;
    }

    public String getEi_suosit_termi() {
        return ei_suosit_termi;
    }

    public void setEi_suosit_termi(String ei_suosit_termi) {
        this.ei_suosit_termi = ei_suosit_termi;
    }

    public String getKayttoaluerajaus() {
        return kayttoaluerajaus;
    }

    public void setKayttoaluerajaus(String kayttoaluerajaus) {
        this.kayttoaluerajaus = kayttoaluerajaus;
    }

    public String getMaaritelma() {
        return maaritelma;
    }

    public void setMaaritelma(String maaritelma) {
        this.maaritelma = maaritelma;
    }

    public String getKommentit() {
        return kommentit;
    }

    public void setKommentit(String kommentit) {
        this.kommentit = kommentit;
    }

    public String getValmis() {
        return valmis;
    }

    public void setValmis(String valmis) {
        this.valmis = valmis;
    }

    public String getLahde() {
        return lahde;
    }

    public void setLahde(String lahde) {
        this.lahde = lahde;
    }

    public String getOmistaja() {
        return omistaja;
    }

    public void setOmistaja(String omistaja) {
        this.omistaja = omistaja;
    }

    public String getYdinkasite() {
        return ydinkasite;
    }

    public void setYdinkasite(String ydinkasite) {
        this.ydinkasite = ydinkasite;
    }

    public String getHakutermit() {
        return hakutermit;
    }

    public void setHakutermit(String hakutermit) {
        this.hakutermit = hakutermit;
    }

    public List<Integer> getHierarkk_ylakasite() {
        return hierarkk_ylakasite;
    }

    public void setHierarkk_ylakasite(List<Integer> hierarkk_ylakasite) {
        this.hierarkk_ylakasite = hierarkk_ylakasite;
    }

    public List<Integer> getKoostumussuht_ylakasite() {
        return koostumussuht_ylakasite;
    }

    public void setKoostumussuht_ylakasite(List<Integer> koostumussuht_ylakasite) {
        this.koostumussuht_ylakasite = koostumussuht_ylakasite;
    }

    public List<Integer> getAssosiatiiv_kasite() {
        return assosiatiiv_kasite;
    }

    public void setAssosiatiiv_kasite(List<Integer> assosiatiiv_kasite) {
        this.assosiatiiv_kasite = assosiatiiv_kasite;
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
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }


    @Override
    public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
        this.rivimuokkaajatunnus = rivimuokkaajatunnus;
    }

    @Override
    public String getRivimuokkaajatunnus() {
        return this.rivimuokkaajatunnus;
    }
}
