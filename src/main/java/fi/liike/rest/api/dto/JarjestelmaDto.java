package fi.liike.rest.api.dto;

import fi.liike.rest.Model.Dto.MolekyyliLinkkiDto;
import fi.liike.rest.auth.Right;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.*;

public class JarjestelmaDto extends JarjestelmaGeneralDto {

	private String kayttajaryhmat;
	private String sisaiset_kayttajat;
	private String lisenssitieto;
	private String kriittisyys;
	private String versio;
	private String tietoj_kriittisyys_toiminnalle;
	private String tietovarastot;
	private String portaalipalvelu;
	private String tunnistetut_kehitystarpeet;
	private String toteutusteknologia;
	private String sisaisten_kayttajien_maara;
	private String palvelin;
	private String tekninen_elinkaari;
	private String kayttooikeuksien_hallinta;
	private String huomautus_esimerkki;
	private String kehityspanos_arvioitu;
	private String kehityskulut_arvioidut;
	private String palveluaika;
	private String liik_turvallisuusluokka;
	private String tunnistetut_riskit;
	private String luokituksen_tarkastuspvm;
	private String tietoturvatasoluokitus;
	private String ict_varautumisen_luokitus;
	private String sla_luokitus_jhs;
	private String sla_luokitus_kayttopalvelu;
	private String jarjestelman_tarkeys_liville;
	private String jarj_tarkeys_yhteistyokump;
	private String turvallisuuskuvaus_laadittu;
	private String toipumissuunnitelma_laadittu;
	private String kehitystarve;
	private String kayttotiheys;
	private String ulkoiset_kayttajat;
	private String tietolahteet;
	private String salassa_pidettavat_tiedot;
	private String julkiset_tiedot_ryhmittain;
	private String tietoj_internet_osoite;
	private String tietojen_julkisuus;
	private String rekisteriseloste;
	private String yhteisk_kriit_jarj;
	private String jarj_tarkeys_yk;
	private String rivimuokkaajatunnus;
	private Boolean tietoturvasopimus;
	private List<String> noRightsToModify;
	private String budjetti;
	private String rahoitusmomentti;
	private String salassapidon_peruste;
	private String turvallisuusluokitus;
	private String tarkeinta;
	private String tiedonluottamuksellisuus;
	private String tiedon_saatavuus;
	private String tiedon_eheys;
	private String tarvitaan_viikonloppuna;
	private String tarvitaan_audit_trail;
	private String integraatioita_muihin;

	@JsonIgnore
	private MolekyyliLinkkiDto linkData;

	//Only used for updating or creating henkiloRooli
	private List<HenkiloRooliDto> henkiloRooliList;

	public JarjestelmaDto() {
		mapRightToFields = new HashMap<Right, List<String>>();
		mapRightToFields.put(Right.MODIFY_SECURED_FIELDS, Arrays.asList("tietoturvasopimus"));
		mapRightToFields.put(Right.MODIFY_UNSECURED_FIELDS, Collections.singletonList("ALL_FIELDS"));
	}

	public String getKayttajaryhmat() {
		return kayttajaryhmat;
	}

	public void setKayttajaryhmat(String kayttajaryhmat) {
		this.kayttajaryhmat = kayttajaryhmat;
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

	public String getKriittisyys() {
		return kriittisyys;
	}

	public void setKriittisyys(String kriittisyys) {
		this.kriittisyys = kriittisyys;
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

	public String getLuokituksen_tarkastuspvm() {
		return luokituksen_tarkastuspvm;
	}

	public void setLuokituksen_tarkastuspvm(String luokituksen_tarkastuspvm) {
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

	@Override
	public String getRivimuokkaajatunnus() {
		return rivimuokkaajatunnus;
	}

	@Override
	public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
		this.rivimuokkaajatunnus = rivimuokkaajatunnus;
	}

	public Boolean getTietoturvasopimus() {
		return tietoturvasopimus;
	}

	public void setTietoturvasopimus(Boolean tietoturvasopimus) {
		this.tietoturvasopimus = tietoturvasopimus;
	}

	@Override
	public List<String> getNoRightsToModify() {
		return noRightsToModify;
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
	public List<Right> getNeededRights() throws NoSuchFieldException, IllegalAccessException {
		if (mapRightToFields != null) {
			List<Right> rights = new ArrayList<Right>();
			for (Right right : mapRightToFields.keySet()) {
				for (String field : mapRightToFields.get(right)) {
					if (field.equals("ALL_FIELDS")) {
						rights.add(right);
					}
					else {
						Object currValue = this.getClass().getDeclaredField(field).get(this);
						if (currValue != null) {
							rights.add(right);
						}
					}
				}
			}
			return rights;
		} else {
			return null;
		}
	}

	public void setLinkData(MolekyyliLinkkiDto linkData) {
		this.linkData = linkData;
	}

	@Override
	public String toString() {
		return "JarjestelmaDto{" +
				"tunnus=" + getTunnus() +
				", nimi='" + getNimi() + '\'' +
				", kuvaus='" + getKuvaus() + '\'' +
				", jarjestelmatyyppi='" + getJarjestelmatyyppi() + '\'' +
				", jarjestelmaLinkkausList=" + getJarjestelmaLinkkausList() +
				", tietoturvasopimus=" + tietoturvasopimus +
				", fetchRooliHenkiloList=" + getFetchRooliHenkiloList() +
				'}';
	}

	public List<HenkiloRooliDto> getHenkiloRooliList() {
		return henkiloRooliList;
	}

	public void setHenkiloRooliList(List<HenkiloRooliDto> henkiloRooliList) {
		this.henkiloRooliList = henkiloRooliList;
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
