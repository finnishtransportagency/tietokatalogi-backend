package fi.liike.rest.Model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "tietojarjestelmasalkku")
public class Jarjestelma extends Haettava implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "TIETOJARJESTELMATUNNUS", unique = true, nullable = false)
	private Integer tunnus;

	@Column(name = "JARJESTELMAN_NIMI")
	private String nimi;

	@Column(name = "KUVAUS_MAARITELMA")
	private String kuvaus;

	@Column(name = "JARJESTELMATYYPPI")
	private String jarjestelmatyyppi;

	@Column(name = "LYHENNETTY_NIMI")
	private String lyhennetty_nimi;

	@Column(name = "KAYTTAJARYHMAT")
	private String kayttajaryhmat;

	@Column(name = "ELINKAARITILA")
	private String elinkaaritila;

	@Column(name = "TOIMITTAJA")
	private String toimittaja;

	@Column(name = "TOIMITTAJAN_YHTEYSHENKILO")
	private String toimittajan_yhteyshenkilo;

	@Column(name = "SISAISET_KAYTTAJAT")
	private String sisaiset_kayttajat;

	@Column(name = "LISENSSITIETO")
	private String lisenssitieto;

	@Column(name = "ALFRESCO_LINKKI")
	private String alfresco_linkki;

	@Column(name = "KRIITTISYYS")
	private String kriittisyys;

	@Column(name = "MUUT_TOIMITTAJAT")
	private String muut_toimittajat;

	@Column(name = "VERSIO")
	private String versio;

	@Column(name = "TIETOJ_KRIITTISYYS_TOIMINNALLE")
	private String tietoj_kriittisyys_toiminnalle;

	@Column(name = "TIETOVARASTOT")
	private String tietovarastot;

	@Column(name = "PAASYNHALLINTA")
	private String paasynhallinta;

	@Column(name = "PORTAALIPALVELU")
	private String portaalipalvelu;

	@Column(name = "TUNNISTETUT_KEHITYSTARPEET")
	private String tunnistetut_kehitystarpeet;

	@Column(name = "TOTEUTUSTEKNOLOGIA")
	private String toteutusteknologia;

	@Column(name = "SISAISTEN_KAYTTAJIEN_MAARA")
	private String sisaisten_kayttajien_maara;

	@Column(name = "PALVELIN")
	private String palvelin;

	@Column(name = "TEKNINEN_ELINKAARI")
	private String tekninen_elinkaari;

	@Column(name = "KAYTTOOIKEUKSIEN_HALLINTA")
	private String kayttooikeuksien_hallinta;

	@Column(name = "KAYTTOONOTTOVUOSI")
	private String kayttoonottovuosi;

	@Column(name = "HUOMAUTUS_ESIMERKKI")
	private String huomautus_esimerkki;

	@Column(name = "KEHITYSPANOS_ARVIOITU")
	private String kehityspanos_arvioitu;

	@Column(name = "KEHITYSKULUT_ARVIOIDUT")
	private String kehityskulut_arvioidut;

	@Column(name = "PALVELUAIKA")
	private String palveluaika;

	@Column(name = "LIIK_TURVALLISUUSLUOKKA")
	private String liik_turvallisuusluokka;

	@Column(name = "TUNNISTETUT_RISKIT")
	private String tunnistetut_riskit;

	@Column(name = "LUOKITUKSEN_TARKASTUSPVM")
	private Date luokituksen_tarkastuspvm;

	@Column(name = "TIETOTURVATASOLUOKITUS")
	private String tietoturvatasoluokitus;

	@Column(name = "ICT_VARAUTUMISEN_LUOKITUS")
	private String ict_varautumisen_luokitus;

	@Column(name = "SLA_LUOKITUS_JHS")
	private String sla_luokitus_jhs;

	@Column(name = "SLA_LUOKITUS_KAYTTOPALVELU")
	private String sla_luokitus_kayttopalvelu;

	@Column(name = "JARJESTELMAN_TARKEYS_LIVILLE")
	private String jarjestelman_tarkeys_liville;

	@Column(name = "JARJ_TARKEYS_YHTEISTYOKUMP")
	private String jarj_tarkeys_yhteistyokump;

	@Column(name = "TURVALLISUUSKUVAUS_LAADITTU")
	private String turvallisuuskuvaus_laadittu;

	@Column(name = "TOIPUMISSUUNNITELMA_LAADITTU")
	private String toipumissuunnitelma_laadittu;

	@Column(name = "KEHITYSTARVE")
	private String kehitystarve;

	@Column(name = "KAYTTOTIHEYS")
	private String kayttotiheys;

	@Column(name = "ULKOISET_KAYTTAJAT")
	private String ulkoiset_kayttajat;

	@Column(name = "POISTOVUOSI")
	private String poistovuosi;

	@Column(name = "TIETOLAHTEET")
	private String tietolahteet;

	@Column(name = "SALASSA_PIDETTAVAT_TIEDOT")
	private String salassa_pidettavat_tiedot;

	@Column(name = "JULKISET_TIEDOT_RYHMITTAIN")
	private String julkiset_tiedot_ryhmittain;

	@Column(name = "TIETOJ_INTERNET_OSOITE")
	private String tietoj_internet_osoite;

	@Column(name = "TIETOJEN_JULKISUUS")
	private String tietojen_julkisuus;

	@Column(name = "REKISTERISELOSTE")
	private String rekisteriseloste;

	@Column(name = "RIVIMUOKATTUPVM")
	private Timestamp rivimuokattupvm;

	@Column(name = "RIVILUOTUPVM")
	private Timestamp riviluotupvm;

	@Column(name = "RIVIMUOKKAAJATUNNUS")
	private String rivimuokkaajatunnus;

	@Column(name = "DOCUMENT_ID")
	private String document_id;

	@Column(name = "TOIMITTAJAN_TEKN_YH")
	private String toimittajan_tekn_yh;

	@Column(name = "YHTEISK_KRIIT_JARJ")
	private String yhteisk_kriit_jarj;

	@Column(name = "JARJ_TARKEYS_YK")
	private String jarj_tarkeys_yk;

	@Column(name = "JARJESTELMAALUE")
	private String jarjestelmaalue;

	@Column(name = "TIETOTURVASOPIMUS")
	private Integer tietoturvasopimus;

	@Column(name = "CONFLUENCE_LINKKI")
	private String confluence_linkki;

	@Column(name ="KAYTTOPALVELUN_TOIMITTAJA")
	private String kayttopalvelun_toimittaja;

	@Column(name ="OMISTAVA_ORGANISAATIO")
	private String omistava_organisaatio;

	@Column(name = "BUDJETTI")
	private String budjetti;

	@Column(name = "RAHOITUSMOMENTTI")
	private String rahoitusmomentti;

	@Column(name = "SALASSAPIDON_PERUSTE")
	private String salassapidon_peruste;

	@Column(name = "TURVALLISUUSLUOKITUS")
	private String turvallisuusluokitus;

	@Column(name = "TARKEINTA")
	private String tarkeinta;

	@Column(name = "TIEDONLUOTTAMUKSELLISUUS")
	private String tiedonluottamuksellisuus;

	@Column(name = "TIEDON_SAATAVUUS")
	private String tiedon_saatavuus;

	@Column(name = "TIEDON_EHEYS")
	private String tiedon_eheys;

	@Column(name = "TARVITAAN_VIIKONLOPPUNA")
	private String tarvitaan_viikonloppuna;

	@Column(name = "TARVITAAN_AUDIT_TRAIL")
	private String tarvitaan_audit_trail;

	@Column(name = "INTEGRAATIOITA_MUIHIN")
	private String integraatioita_muihin;

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

	@Override
	public String toString() {
		return "Jarjestelma{" +
				"tunnus=" + tunnus +
				", nimi='" + nimi + '\'' +
				", kuvaus='" + kuvaus + '\'' +
				", jarjestelmatyyppi='" + jarjestelmatyyppi + '\'' +
				", tietoturvasopimus=" + tietoturvasopimus +
				'}';
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public String getJarjestelmatyyppi() {
		return jarjestelmatyyppi;
	}

	public void setJarjestelmatyyppi(String jarjestelmatyyppi) {
		this.jarjestelmatyyppi = jarjestelmatyyppi;
	}

	public String getLyhennetty_nimi() {
		return lyhennetty_nimi;
	}

	public void setLyhennetty_nimi(String lyhennetty_nimi) {
		this.lyhennetty_nimi = lyhennetty_nimi;
	}

	public String getKayttajaryhmat() {
		return kayttajaryhmat;
	}

	public void setKayttajaryhmat(String kayttajaryhmat) {
		this.kayttajaryhmat = kayttajaryhmat;
	}

	public String getElinkaaritila() {
		return elinkaaritila;
	}

	public void setElinkaaritila(String elinkaaritila) {
		this.elinkaaritila = elinkaaritila;
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

	public String getSisaiset_kayttajat() {
		return sisaiset_kayttajat;
	}

	public void setSisaiset_kayttajat(String sisaiset_kayttajat) {
		this.sisaiset_kayttajat = sisaiset_kayttajat;
	}

	public String getLisenssitieto() {
		return lisenssitieto;
	}

	public void setLisenssitieto(String lisenssitieto) {
		this.lisenssitieto = lisenssitieto;
	}

	public String getAlfresco_linkki() {
		return alfresco_linkki;
	}

	public void setAlfresco_linkki(String alfresco_linkki) {
		this.alfresco_linkki = alfresco_linkki;
	}

	public String getKriittisyys() {
		return kriittisyys;
	}

	public void setKriittisyys(String kriittisyys) {
		this.kriittisyys = kriittisyys;
	}

	public String getMuut_toimittajat() {
		return muut_toimittajat;
	}

	public void setMuut_toimittajat(String muut_toimittajat) {
		this.muut_toimittajat = muut_toimittajat;
	}

	public String getVersio() {
		return versio;
	}

	public void setVersio(String versio) {
		this.versio = versio;
	}

	public String getTietoj_kriittisyys_toiminnalle() {
		return tietoj_kriittisyys_toiminnalle;
	}

	public void setTietoj_kriittisyys_toiminnalle(String tietoj_kriittisyys_toiminnalle) {
		this.tietoj_kriittisyys_toiminnalle = tietoj_kriittisyys_toiminnalle;
	}

	public String getTietovarastot() {
		return tietovarastot;
	}

	public void setTietovarastot(String tietovarastot) {
		this.tietovarastot = tietovarastot;
	}

	public String getPaasynhallinta() {
		return paasynhallinta;
	}

	public void setPaasynhallinta(String paasynhallinta) {
		this.paasynhallinta = paasynhallinta;
	}

	public String getPortaalipalvelu() {
		return portaalipalvelu;
	}

	public void setPortaalipalvelu(String portaalipalvelu) {
		this.portaalipalvelu = portaalipalvelu;
	}

	public String getTunnistetut_kehitystarpeet() {
		return tunnistetut_kehitystarpeet;
	}

	public void setTunnistetut_kehitystarpeet(String tunnistetut_kehitystarpeet) {
		this.tunnistetut_kehitystarpeet = tunnistetut_kehitystarpeet;
	}

	public String getToteutusteknologia() {
		return toteutusteknologia;
	}

	public void setToteutusteknologia(String toteutusteknologia) {
		this.toteutusteknologia = toteutusteknologia;
	}

	public String getSisaisten_kayttajien_maara() {
		return sisaisten_kayttajien_maara;
	}

	public void setSisaisten_kayttajien_maara(String sisaisten_kayttajien_maara) {
		this.sisaisten_kayttajien_maara = sisaisten_kayttajien_maara;
	}

	public String getPalvelin() {
		return palvelin;
	}

	public void setPalvelin(String palvelin) {
		this.palvelin = palvelin;
	}

	public String getTekninen_elinkaari() {
		return tekninen_elinkaari;
	}

	public void setTekninen_elinkaari(String tekninen_elinkaari) {
		this.tekninen_elinkaari = tekninen_elinkaari;
	}

	public String getKayttooikeuksien_hallinta() {
		return kayttooikeuksien_hallinta;
	}

	public void setKayttooikeuksien_hallinta(String kayttooikeuksien_hallinta) {
		this.kayttooikeuksien_hallinta = kayttooikeuksien_hallinta;
	}

	public String getKayttoonottovuosi() {
		return kayttoonottovuosi;
	}

	public void setKayttoonottovuosi(String kayttoonottovuosi) {
		this.kayttoonottovuosi = kayttoonottovuosi;
	}

	public String getHuomautus_esimerkki() {
		return huomautus_esimerkki;
	}

	public void setHuomautus_esimerkki(String huomautus_esimerkki) {
		this.huomautus_esimerkki = huomautus_esimerkki;
	}

	public String getKehityspanos_arvioitu() {
		return kehityspanos_arvioitu;
	}

	public void setKehityspanos_arvioitu(String kehityspanos_arvioitu) {
		this.kehityspanos_arvioitu = kehityspanos_arvioitu;
	}

	public String getKehityskulut_arvioidut() {
		return kehityskulut_arvioidut;
	}

	public void setKehityskulut_arvioidut(String kehityskulut_arvioidut) {
		this.kehityskulut_arvioidut = kehityskulut_arvioidut;
	}

	public String getPalveluaika() {
		return palveluaika;
	}

	public void setPalveluaika(String palveluaika) {
		this.palveluaika = palveluaika;
	}

	public String getLiik_turvallisuusluokka() {
		return liik_turvallisuusluokka;
	}

	public void setLiik_turvallisuusluokka(String liik_turvallisuusluokka) {
		this.liik_turvallisuusluokka = liik_turvallisuusluokka;
	}

	public String getTunnistetut_riskit() {
		return tunnistetut_riskit;
	}

	public void setTunnistetut_riskit(String tunnistetut_riskit) {
		this.tunnistetut_riskit = tunnistetut_riskit;
	}

	public Date getLuokituksen_tarkastuspvm() {
		return luokituksen_tarkastuspvm;
	}

	public void setLuokituksen_tarkastuspvm(Date luokituksen_tarkastuspvm) {
		this.luokituksen_tarkastuspvm = luokituksen_tarkastuspvm;
	}

	public String getTietoturvatasoluokitus() {
		return tietoturvatasoluokitus;
	}

	public void setTietoturvatasoluokitus(String tietoturvatasoluokitus) {
		this.tietoturvatasoluokitus = tietoturvatasoluokitus;
	}

	public String getIct_varautumisen_luokitus() {
		return ict_varautumisen_luokitus;
	}

	public void setIct_varautumisen_luokitus(String ict_varautumisen_luokitus) {
		this.ict_varautumisen_luokitus = ict_varautumisen_luokitus;
	}

	public String getSla_luokitus_jhs() {
		return sla_luokitus_jhs;
	}

	public void setSla_luokitus_jhs(String sla_luokitus_jhs) {
		this.sla_luokitus_jhs = sla_luokitus_jhs;
	}

	public String getSla_luokitus_kayttopalvelu() {
		return sla_luokitus_kayttopalvelu;
	}

	public void setSla_luokitus_kayttopalvelu(String sla_luokitus_kayttopalvelu) {
		this.sla_luokitus_kayttopalvelu = sla_luokitus_kayttopalvelu;
	}

	public String getJarjestelman_tarkeys_liville() {
		return jarjestelman_tarkeys_liville;
	}

	public void setJarjestelman_tarkeys_liville(String jarjestelman_tarkeys_liville) {
		this.jarjestelman_tarkeys_liville = jarjestelman_tarkeys_liville;
	}

	public String getJarj_tarkeys_yhteistyokump() {
		return jarj_tarkeys_yhteistyokump;
	}

	public void setJarj_tarkeys_yhteistyokump(String jarj_tarkeys_yhteistyokump) {
		this.jarj_tarkeys_yhteistyokump = jarj_tarkeys_yhteistyokump;
	}

	public String getTurvallisuuskuvaus_laadittu() {
		return turvallisuuskuvaus_laadittu;
	}

	public void setTurvallisuuskuvaus_laadittu(String turvallisuuskuvaus_laadittu) {
		this.turvallisuuskuvaus_laadittu = turvallisuuskuvaus_laadittu;
	}

	public String getToipumissuunnitelma_laadittu() {
		return toipumissuunnitelma_laadittu;
	}

	public void setToipumissuunnitelma_laadittu(String toipumissuunnitelma_laadittu) {
		this.toipumissuunnitelma_laadittu = toipumissuunnitelma_laadittu;
	}

	public String getKehitystarve() {
		return kehitystarve;
	}

	public void setKehitystarve(String kehitystarve) {
		this.kehitystarve = kehitystarve;
	}

	public String getKayttotiheys() {
		return kayttotiheys;
	}

	public void setKayttotiheys(String kayttotiheys) {
		this.kayttotiheys = kayttotiheys;
	}

	public String getUlkoiset_kayttajat() {
		return ulkoiset_kayttajat;
	}

	public void setUlkoiset_kayttajat(String ulkoiset_kayttajat) {
		this.ulkoiset_kayttajat = ulkoiset_kayttajat;
	}

	public String getPoistovuosi() {
		return poistovuosi;
	}

	public void setPoistovuosi(String poistovuosi) {
		this.poistovuosi = poistovuosi;
	}

	public String getTietolahteet() {
		return tietolahteet;
	}

	public void setTietolahteet(String tietolahteet) {
		this.tietolahteet = tietolahteet;
	}

	public String getSalassa_pidettavat_tiedot() {
		return salassa_pidettavat_tiedot;
	}

	public void setSalassa_pidettavat_tiedot(String salassa_pidettavat_tiedot) {
		this.salassa_pidettavat_tiedot = salassa_pidettavat_tiedot;
	}

	public String getJulkiset_tiedot_ryhmittain() {
		return julkiset_tiedot_ryhmittain;
	}

	public void setJulkiset_tiedot_ryhmittain(String julkiset_tiedot_ryhmittain) {
		this.julkiset_tiedot_ryhmittain = julkiset_tiedot_ryhmittain;
	}

	public String getTietoj_internet_osoite() {
		return tietoj_internet_osoite;
	}

	public void setTietoj_internet_osoite(String tietoj_internet_osoite) {
		this.tietoj_internet_osoite = tietoj_internet_osoite;
	}

	public String getTietojen_julkisuus() {
		return tietojen_julkisuus;
	}

	public void setTietojen_julkisuus(String tietojen_julkisuus) {
		this.tietojen_julkisuus = tietojen_julkisuus;
	}

	public String getRekisteriseloste() {
		return rekisteriseloste;
	}

	public void setRekisteriseloste(String rekisteriseloste) {
		this.rekisteriseloste = rekisteriseloste;
	}

	@Override
	public Timestamp getRivimuokattupvm() {
		return rivimuokattupvm;
	}

	@Override
	public void setRivimuokattupvm(Timestamp rivimuokattupvm) {
		this.rivimuokattupvm = rivimuokattupvm;
	}

	@Override
	public Timestamp getRiviluotupvm() {
		return riviluotupvm;
	}

	@Override
	public void setRiviluotupvm(Timestamp riviluotupvm) {
		this.riviluotupvm = riviluotupvm;
	}

	public String getRivimuokkaajatunnus() {
		return rivimuokkaajatunnus;
	}

	public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
		this.rivimuokkaajatunnus = rivimuokkaajatunnus;
	}

	public String getDocument_id() {
		return document_id;
	}

	public void setDocument_id(String document_id) {
		this.document_id = document_id;
	}

	public String getToimittajan_tekn_yh() {
		return toimittajan_tekn_yh;
	}

	public void setToimittajan_tekn_yh(String toimittajan_tekn_yh) {
		this.toimittajan_tekn_yh = toimittajan_tekn_yh;
	}

	public String getYhteisk_kriit_jarj() {
		return yhteisk_kriit_jarj;
	}

	public void setYhteisk_kriit_jarj(String yhteisk_kriit_jarj) {
		this.yhteisk_kriit_jarj = yhteisk_kriit_jarj;
	}

	public String getJarj_tarkeys_yk() {
		return jarj_tarkeys_yk;
	}

	public void setJarj_tarkeys_yk(String jarj_tarkeys_yk) {
		this.jarj_tarkeys_yk = jarj_tarkeys_yk;
	}

	public String getJarjestelmaalue() {
		return jarjestelmaalue;
	}

	public void setJarjestelmaalue(String jarjestelmaalue) {
		this.jarjestelmaalue = jarjestelmaalue;
	}

	public Integer getTietoturvasopimus() {
		return tietoturvasopimus;
	}

	public void setTietoturvasopimus(Integer tietoturvasopimus) {
		this.tietoturvasopimus = tietoturvasopimus;
	}


	public String getConfluence_linkki() {
		return confluence_linkki;
	}

	public void setConfluence_linkki(String confluence_linkki) {
		this.confluence_linkki = confluence_linkki;
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

	public String getBudjetti() {
		return budjetti;
	}

	public void setBudjetti(String budjetti) {
		this.budjetti = budjetti;
	}

	public String getRahoitusmomentti() {
		return rahoitusmomentti;
	}

	public void setRahoitusmomentti(String rahoitusmomentti) {
		this.rahoitusmomentti = rahoitusmomentti;
	}

	public String getSalassapidon_peruste() {
		return salassapidon_peruste;
	}

	public void setSalassapidon_peruste(String salassapidon_peruste) {
		this.salassapidon_peruste = salassapidon_peruste;
	}

	public String getTurvallisuusluokitus() {
		return turvallisuusluokitus;
	}

	public void setTurvallisuusluokitus(String turvallisuusluokitus) {
		this.turvallisuusluokitus = turvallisuusluokitus;
	}

	public String getTarkeinta() {
		return tarkeinta;
	}

	public void setTarkeinta(String tarkeinta) {
		this.tarkeinta = tarkeinta;
	}

	public String getTiedonluottamuksellisuus() {
		return tiedonluottamuksellisuus;
	}

	public void setTiedonluottamuksellisuus(String tiedonluottamuksellisuus) {
		this.tiedonluottamuksellisuus = tiedonluottamuksellisuus;
	}

	public String getTiedon_saatavuus() {
		return tiedon_saatavuus;
	}

	public void setTiedon_saatavuus(String tiedon_saatavuus) {
		this.tiedon_saatavuus = tiedon_saatavuus;
	}

	public String getTiedon_eheys() {
		return tiedon_eheys;
	}

	public void setTiedon_eheys(String tiedon_eheys) {
		this.tiedon_eheys = tiedon_eheys;
	}

	public String getTarvitaan_viikonloppuna() {
		return tarvitaan_viikonloppuna;
	}

	public void setTarvitaan_viikonloppuna(String tarvitaan_viikonloppuna) {
		this.tarvitaan_viikonloppuna = tarvitaan_viikonloppuna;
	}

	public String getTarvitaan_audit_trail() {
		return tarvitaan_audit_trail;
	}

	public void setTarvitaan_audit_trail(String tarvitaan_audit_trail) {
		this.tarvitaan_audit_trail = tarvitaan_audit_trail;
	}

	public String getIntegraatioita_muihin() {
		return integraatioita_muihin;
	}

	public void setIntegraatioita_muihin(String integraatioita_muihin) {
		this.integraatioita_muihin = integraatioita_muihin;
	}
}
