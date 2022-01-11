package fi.liike.rest.api.dto;

public class TietoomaisuusDto extends ContentDtoWithRights {

    private Integer tunnus;

    private Integer tietojarjestelma_tunnus;

    private String tietojarjestelma_nimi;

    private Integer primaarikayttotarve;

    private Integer sekundaar_kayttotarpeet;

    private Integer kayttajat;

    private Integer historiatiedot;

    private Integer kattavuus_ja_laatu;

    private Integer metatiedot;

    private Integer muutostiedot;

    private Integer saatavuus;

    private Integer henkilotiedot;

    private Integer eheys;

    private Integer avoimuus;

    private Integer immateriaalioikeudet;

    private Integer yksiloivat_tunnisteet;

    private Integer pysyvyys;

    private Integer dokumentaatio;

    private Integer elinkaari;

    private String rivimuokkaajatunnus;

    private Integer mallinnustapa;

    private Integer kayttoformaatit;

    private Integer alustojen_tyyp_ja_sij;

    public Integer getTietojarjestelma_tunnus() {
        return tietojarjestelma_tunnus;
    }

    public void setTietojarjestelma_tunnus(Integer tietojarjestelma_tunnus) {
        this.tietojarjestelma_tunnus = tietojarjestelma_tunnus;
    }

    public String getTietojarjestelma_nimi() {
        return tietojarjestelma_nimi;
    }

    public void setTietojarjestelma_nimi(String tietojarjestelma_nimi) {
        this.tietojarjestelma_nimi = tietojarjestelma_nimi;
    }

    public Integer getPrimaarikayttotarve() {
        return primaarikayttotarve;
    }

    public void setPrimaarikayttotarve(Integer primaarikayttotarve) {
        this.primaarikayttotarve = primaarikayttotarve;
    }

    public Integer getSekundaar_kayttotarpeet() {
        return sekundaar_kayttotarpeet;
    }

    public void setSekundaar_kayttotarpeet(Integer sekundaar_kayttotarpeet) {
        this.sekundaar_kayttotarpeet = sekundaar_kayttotarpeet;
    }

    public Integer getKayttajat() {
        return kayttajat;
    }

    public void setKayttajat(Integer kayttajat) {
        this.kayttajat = kayttajat;
    }

    public Integer getHistoriatiedot() {
        return historiatiedot;
    }

    public void setHistoriatiedot(Integer historiatiedot) {
        this.historiatiedot = historiatiedot;
    }

    public Integer getKattavuus_ja_laatu() {
        return kattavuus_ja_laatu;
    }

    public void setKattavuus_ja_laatu(Integer kattavuus_ja_laatu) {
        this.kattavuus_ja_laatu = kattavuus_ja_laatu;
    }

    public Integer getMetatiedot() {
        return metatiedot;
    }

    public void setMetatiedot(Integer metatiedot) {
        this.metatiedot = metatiedot;
    }

    public Integer getMuutostiedot() {
        return muutostiedot;
    }

    public void setMuutostiedot(Integer muutostiedot) {
        this.muutostiedot = muutostiedot;
    }

    public Integer getSaatavuus() {
        return saatavuus;
    }

    public void setSaatavuus(Integer saatavuus) {
        this.saatavuus = saatavuus;
    }

    public Integer getHenkilotiedot() {
        return henkilotiedot;
    }

    public void setHenkilotiedot(Integer henkilotiedot) {
        this.henkilotiedot = henkilotiedot;
    }

    public Integer getEheys() {
        return eheys;
    }

    public void setEheys(Integer eheys) {
        this.eheys = eheys;
    }

    public Integer getAvoimuus() {
        return avoimuus;
    }

    public void setAvoimuus(Integer avoimuus) {
        this.avoimuus = avoimuus;
    }

    public Integer getImmateriaalioikeudet() {
        return immateriaalioikeudet;
    }

    public void setImmateriaalioikeudet(Integer immateriaalioikeudet) {
        this.immateriaalioikeudet = immateriaalioikeudet;
    }

    public Integer getYksiloivat_tunnisteet() {
        return yksiloivat_tunnisteet;
    }

    public void setYksiloivat_tunnisteet(Integer yksiloivat_tunnisteet) {
        this.yksiloivat_tunnisteet = yksiloivat_tunnisteet;
    }

    public Integer getPysyvyys() {
        return pysyvyys;
    }

    public void setPysyvyys(Integer pysyvyys) {
        this.pysyvyys = pysyvyys;
    }

    public Integer getDokumentaatio() {
        return dokumentaatio;
    }

    public void setDokumentaatio(Integer dokumentaatio) {
        this.dokumentaatio = dokumentaatio;
    }

    public Integer getElinkaari() {
        return elinkaari;
    }

    public void setElinkaari(Integer elinkaari) {
        this.elinkaari = elinkaari;
    }

    public Integer getMallinnustapa() {
        return mallinnustapa;
    }

    public void setMallinnustapa(Integer mallinnustapa) {
        this.mallinnustapa = mallinnustapa;
    }

    public Integer getKayttoformaatit() {
        return kayttoformaatit;
    }

    public void setKayttoformaatit(Integer kayttoformaatit) {
        this.kayttoformaatit = kayttoformaatit;
    }

    public Integer getAlustojen_tyyp_ja_sij() {
        return alustojen_tyyp_ja_sij;
    }

    public void setAlustojen_tyyp_ja_sij(Integer alustojen_tyyp_ja_sij) {
        this.alustojen_tyyp_ja_sij = alustojen_tyyp_ja_sij;
    }

    @Override
    public void setNimi(String name) {
    }

    @Override
    public void setTunnus(Integer id) {
        this.tunnus = id;
    }

    @Override
    public Integer getTunnus() {
        return this.tunnus;
    }

    @Override
    public String getNimi() {
        return null;
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
