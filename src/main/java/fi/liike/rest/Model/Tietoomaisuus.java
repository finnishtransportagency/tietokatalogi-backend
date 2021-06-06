package fi.liike.rest.Model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="tietoomaisuus")
public class Tietoomaisuus extends Haettava implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private Integer tunnus;

    @Column(name="TIETOJARJESTELMA_ID")
    private Integer tietojarjestelmaTunnus;

    @Column(name="PRIMAARIKAYTTOTARVE")
    private Integer primaarikayttotarve;

    @Column(name="SEKUNDAAR_KAYTTOTARPEET")
    private Integer sekundaar_kayttotarpeet;

    @Column(name="KAYTTAJAT")
    private Integer kayttajat;

    @Column(name="HISTORIATIEDOT")
    private Integer historiatiedot;

    @Column(name="KATTAVUUS_JA_LAATU")
    private Integer kattavuusJaLaatu;

    @Column(name="METATIEDOT")
    private Integer metatiedot;

    @Column(name="MUUTOSTIEDOT")
    private Integer muutostiedot;

    @Column(name="SAATAVUUS")
    private Integer saatavuus;

    @Column(name="HENKILOTIEDOT")
    private Integer henkilotiedot;

    @Column(name="EHEYS")
    private Integer eheys;

    @Column(name="AVOIMUUS")
    private Integer avoimuus;

    @Column(name="IMMATERIAALIOIKEUDET")
    private Integer immateriaalioikeudet;

    @Column(name="YKSILOIVAT_TUNNISTEET")
    private Integer yksiloivatTunnisteet;

    @Column(name="PYSYVYYS")
    private Integer pysyvyys;

    @Column(name="DOKUMENTAATIO")
    private Integer dokumentaatio;

    @Column(name="ELINKAARI")
    private Integer elinkaari;

    @Column(name="MALLINNUSTAPA")
    private Integer mallinnustapa;

    @Column(name="KAYTTOFORMAATIT")
    private Integer kayttoformaatit;

    @Column(name="ALUSTOJEN_TYYP_JA_SIJ")
    private Integer alustojenTyypJaSij;

    @Column(name="RIVIMUOKATTUPVM")
    private Timestamp rivimuokattupvm;

    @Column(name="RIVIMUOKKAAJATUNNUS")
    private String rivimuokkaajatunnus;

    @Column(name="RIVILUOTUPVM")
    private Timestamp riviluotupvm;

    @Column(name="NIMI")
    private String nimi;

    @Column(name="KUVAUS")
    private String kuvaus;

    @Override
    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    @Override
    public Integer getTunnus() {
        return tunnus;
    }

    @Override
    public void setTunnus(Integer tunnus) {
        this.tunnus = tunnus;
    }


    public Integer getTietojarjestelmaTunnus() {
        return tietojarjestelmaTunnus;
    }

    public void setTietojarjestelmaTunnus(Integer tietojarjestelmaTunnus) {
        this.tietojarjestelmaTunnus = tietojarjestelmaTunnus;
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

    public Integer getKattavuusJaLaatu() {
        return kattavuusJaLaatu;
    }

    public void setKattavuusJaLaatu(Integer kattavuusJaLaatu) {
        this.kattavuusJaLaatu = kattavuusJaLaatu;
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

    public Integer getYksiloivatTunnisteet() {
        return yksiloivatTunnisteet;
    }

    public void setYksiloivatTunnisteet(Integer yksiloivatTunnisteet) {
        this.yksiloivatTunnisteet = yksiloivatTunnisteet;
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

    public Integer getAlustojenTyypJaSij() {
        return alustojenTyypJaSij;
    }

    public void setAlustojenTyypJaSij(Integer alustojenTyypJaSij) {
        this.alustojenTyypJaSij = alustojenTyypJaSij;
    }


    @Override
    public Timestamp getRivimuokattupvm() {
        return rivimuokattupvm;
    }

    @Override
    public void setRivimuokattupvm(Timestamp rivimuokattupvm) {
        this.rivimuokattupvm = rivimuokattupvm;
    }

    public String getRivimuokkaajatunnus() {
        return rivimuokkaajatunnus;
    }

    public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
        this.rivimuokkaajatunnus = rivimuokkaajatunnus;
    }

    @Override
    public Timestamp getRiviluotupvm() {
        return riviluotupvm;
    }

    @Override
    public void setRiviluotupvm(Timestamp riviluotupvm) {
        this.riviluotupvm = riviluotupvm;
    }

    @Override
    public String toString() {
        return "Tieto-omaisuus{" +
                "tunnus=" + tunnus +
                ", tietojarjestelmaTunnus=" + tietojarjestelmaTunnus +
                '}';
    }
}
