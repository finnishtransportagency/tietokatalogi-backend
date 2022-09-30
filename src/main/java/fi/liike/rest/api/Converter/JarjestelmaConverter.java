package fi.liike.rest.api.Converter;

import fi.liike.rest.Model.*;
import fi.liike.rest.Model.Dto.MolekyyliLinkkiDto;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.*;
import fi.liike.rest.util.Utils;

import java.util.ArrayList;
import java.util.List;

import static fi.liike.rest.util.Utils.dateToUrlParam;

public class JarjestelmaConverter implements MinimalConverter {

	public JoinJarjestelmaLinkkaus jarjestelmaLinkkausDtoToDomain(JarjestelmaLinkkausDto jarjestelmaLinkkaus) {
		JoinJarjestelmaLinkkaus joinJarjestelmaLinkkaus = new JoinJarjestelmaLinkkaus();
		joinJarjestelmaLinkkaus.setRivitunnus(jarjestelmaLinkkaus.getId());
		joinJarjestelmaLinkkaus.setChildNode(jarjestelmaLinkkaus.getLinkattavaTunnus());
		joinJarjestelmaLinkkaus.setParentNode(jarjestelmaLinkkaus.getTietojarjestelmaTunnus());
		joinJarjestelmaLinkkaus.setRivitila("A");
		joinJarjestelmaLinkkaus.setSuunta(jarjestelmaLinkkaus.getSuunta());
		joinJarjestelmaLinkkaus.setTietojarjestelmapalveluTunnus(jarjestelmaLinkkaus.getTietojarjestelmapalveluTunnus());
		joinJarjestelmaLinkkaus.setTyyppi(jarjestelmaLinkkaus.getTyyppi());
		joinJarjestelmaLinkkaus.setKuvaus(jarjestelmaLinkkaus.getTietovirta());
		joinJarjestelmaLinkkaus.setElinkaaritila(jarjestelmaLinkkaus.getElinkaaritila());
		return joinJarjestelmaLinkkaus;
	}

	@Override
	public Haettava dtoToDomain(ContentDto dtoContent) {
		JarjestelmaDto data = (JarjestelmaDto) dtoContent;
		Jarjestelma result = new Jarjestelma();
		result.setTunnus(data.getTunnus());
		result.setNimi(data.getNimi());
		result.setKuvaus(data.getKuvaus());
		result.setJarjestelmatyyppi(data.getJarjestelmatyyppi());
		result.setLyhennetty_nimi(data.getLyhennetty_nimi());
		result.setKayttajaryhmat(data.getKayttajaryhmat());
		result.setElinkaaritila(data.getElinkaaritila());
		result.setToimittaja(data.getToimittaja());
		result.setToimittajan_yhteyshenkilo(data.getToimittajan_yhteyshenkilo());
		result.setSisaiset_kayttajat(data.getSisaiset_kayttajat());
		result.setLisenssitieto(data.getLisenssitieto());
		result.setAlfresco_linkki(data.getAlfresco_linkki());
		result.setKriittisyys(data.getKriittisyys());
		result.setMuut_toimittajat(data.getMuut_toimittajat());
		result.setVersio(data.getVersio());
		result.setTietoj_kriittisyys_toiminnalle(data.getTietoj_kriittisyys_toiminnalle());
		result.setTietovarastot(data.getTietovarastot());
		result.setPaasynhallinta(data.getPaasynhallinta());
		result.setPortaalipalvelu(data.getPortaalipalvelu());
		result.setTunnistetut_kehitystarpeet(data.getTunnistetut_kehitystarpeet());
		result.setToteutusteknologia(data.getToteutusteknologia());
		result.setSisaisten_kayttajien_maara(data.getSisaisten_kayttajien_maara());
		result.setPalvelin(data.getPalvelin());
		result.setTekninen_elinkaari(data.getTekninen_elinkaari());
		result.setKayttooikeuksien_hallinta(data.getKayttooikeuksien_hallinta());
		result.setKayttoonottovuosi(data.getKayttoonottovuosi());
		result.setHuomautus_esimerkki(data.getHuomautus_esimerkki());
		result.setKehityspanos_arvioitu(data.getKehityspanos_arvioitu());
		result.setKehityskulut_arvioidut(data.getKehityskulut_arvioidut());
		result.setPalveluaika(data.getPalveluaika());
		result.setLiik_turvallisuusluokka(data.getLiik_turvallisuusluokka());
		result.setTunnistetut_riskit(data.getTunnistetut_riskit());
		result.setLuokituksen_tarkastuspvm(Utils.strToDate(data.getLuokituksen_tarkastuspvm()));
		result.setTietoturvatasoluokitus(data.getTietoturvatasoluokitus());
		result.setIct_varautumisen_luokitus(data.getIct_varautumisen_luokitus());
		result.setSla_luokitus_jhs(data.getSla_luokitus_jhs());
		result.setSla_luokitus_kayttopalvelu(data.getSla_luokitus_kayttopalvelu());
		result.setJarjestelman_tarkeys_liville(data.getJarjestelman_tarkeys_liville());
		result.setJarj_tarkeys_yhteistyokump(data.getJarj_tarkeys_yhteistyokump());
		result.setTurvallisuuskuvaus_laadittu(data.getTurvallisuuskuvaus_laadittu());
		result.setToipumissuunnitelma_laadittu(data.getToipumissuunnitelma_laadittu());
		result.setKehitystarve(data.getKehitystarve());
		result.setKayttotiheys(data.getKayttotiheys());
		result.setUlkoiset_kayttajat(data.getUlkoiset_kayttajat());
		result.setPoistovuosi(data.getPoistovuosi());
		result.setTietolahteet(data.getTietolahteet());
		result.setSalassa_pidettavat_tiedot(data.getSalassa_pidettavat_tiedot());
		result.setJulkiset_tiedot_ryhmittain(data.getJulkiset_tiedot_ryhmittain());
		result.setTietoj_internet_osoite(data.getTietoj_internet_osoite());
		result.setTietojen_julkisuus(data.getTietojen_julkisuus());
		result.setRekisteriseloste(data.getRekisteriseloste());
		result.setToimittajan_tekn_yh(data.getToimittajan_tekn_yh());
		result.setYhteisk_kriit_jarj(data.getYhteisk_kriit_jarj());
		result.setJarj_tarkeys_yk(data.getJarj_tarkeys_yk());
		result.setJarjestelmaalue(data.getJarjestelmaalue());
		Boolean tietoturvaSopimus = data.getTietoturvasopimus();
		result.setConfluence_linkki(data.getConfluence_linkki());
		if (tietoturvaSopimus == null) {
			result.setTietoturvasopimus(null);
		} else {
			result.setTietoturvasopimus((tietoturvaSopimus) ? 1 : 0);
		}
		result.setRivimuokkaajatunnus(data.getRivimuokkaajatunnus());
		result.setKayttopalvelun_toimittaja(data.getKayttopalvelun_toimittaja());
		result.setOmistava_organisaatio(data.getOmistava_organisaatio());
		result.setBudjetti(data.getBudjetti());
		result.setRahoitusmomentti(data.getRahoitusmomentti());
		result.setSalassapidon_peruste(data.getSalassapidon_peruste());
		result.setTurvallisuusluokitus(data.getTurvallisuusluokitus());
		result.setTarkeinta(data.getTarkeinta());
		result.setTiedonluottamuksellisuus(data.getTiedonluottamuksellisuus());
		result.setTiedon_saatavuus(data.getTiedon_saatavuus());
		result.setTiedon_eheys(data.getTiedon_eheys());
		result.setTarvitaan_viikonloppuna(data.getTarvitaan_viikonloppuna());
		result.setTarvitaan_audit_trail(data.getTarvitaan_audit_trail());
		result.setIntegraatioita_muihin(data.getIntegraatioita_muihin());
		return result;
	}

	public ContentDto modelToMinimalDto(Haettava modelObject) {
		if (modelObject == null)
			return null;
		Jarjestelma data = (Jarjestelma) modelObject;
		JarjestelmaFetchMinimalDto result = new JarjestelmaFetchMinimalDto();
		result.setTunnus(data.getTunnus());
		result.setNimi(data.getNimi());
		result.setKuvaus(data.getKuvaus());
		result.setElinkaaritila(data.getElinkaaritila());
		result.setJarjestelmaalue(data.getJarjestelmaalue());
		result.setJarjestelmatyyppi(data.getJarjestelmatyyppi());

		return result;
	}

	public ContentDto modelToGeneralDto(Haettava modelObject, List<FetchHenkiloRooliDto> fetchHenkiloRooliDtoList,
										List<JoinJarjestelmaLinkkaus> joinJarjestelmaLinkkausList) {
		if (modelObject == null)
			return null;

		Jarjestelma data = (Jarjestelma) modelObject;
		JarjestelmaGeneralDto result = new JarjestelmaGeneralDto();
		result.setTunnus(data.getTunnus());
		result.setNimi(data.getNimi());
		result.setLyhennetty_nimi(data.getLyhennetty_nimi());
		result.setKuvaus(data.getKuvaus());
		result.setJarjestelmaalue(data.getJarjestelmaalue());
		result.setToimittaja(data.getToimittaja());
		result.setToimittajan_yhteyshenkilo(data.getToimittajan_yhteyshenkilo());
		result.setToimittajan_tekn_yh(data.getToimittajan_tekn_yh());
		result.setMuut_toimittajat(data.getMuut_toimittajat());
		result.setKayttopalvelun_toimittaja(data.getKayttopalvelun_toimittaja());
		result.setOmistava_organisaatio(data.getOmistava_organisaatio());
		result.setAlfresco_linkki(data.getAlfresco_linkki());
		result.setElinkaaritila(data.getElinkaaritila());
		result.setKayttoonottovuosi(data.getKayttoonottovuosi());
		result.setPoistovuosi(data.getPoistovuosi());
		result.setDocument_id(data.getDocument_id());
		result.setConfluence_linkki(data.getConfluence_linkki());
		result.setPaasynhallinta(data.getPaasynhallinta());

		result.setFetchRooliHenkiloList(fetchHenkiloRooliDtoList);

		List<JarjestelmaLinkkausDto> jarjestelmaLinkkausList = new ArrayList<JarjestelmaLinkkausDto>();
		for (JoinJarjestelmaLinkkaus jarjestelmaLinkkaus : joinJarjestelmaLinkkausList) {
			jarjestelmaLinkkausList.add(jarjestelmaLinkkausModelToDto(jarjestelmaLinkkaus));
		}
		result.setJarjestelmaLinkkausList(jarjestelmaLinkkausList);

		return result;
	}

	public ContentDto modelToFilteredSahkeDto(Haettava modelObject, List<FetchHenkiloRooliDto> roolit) {
		if (modelObject == null)
			return null;

		Jarjestelma data = (Jarjestelma) modelObject;
		JarjestelmaSahkeDto result = new JarjestelmaSahkeDto();
		result.setTunnus(data.getTunnus());
		result.setNimi(data.getNimi());
		result.setPaasynhallinta(data.getPaasynhallinta());
		result.setTietoturvasopimus(data.getTietoturvasopimus() == null ? null : data.getTietoturvasopimus() == 1);
		List<String> sijaiset = new ArrayList<String>();

		for (FetchHenkiloRooliDto rooli : roolit) {
			if (rooli.getRooli().getNimi().equals("OMISTAJA")) {
				result.setOmistajaId(rooli.getHenkilo().getObjectID());
			}
			else if (rooli.getRooli().getNimi().equals("VASTAAVA")) {
				result.setJarjestelmavastaavaId(rooli.getHenkilo().getObjectID());
			}
			else if (rooli.getRooli().getNimi().equals("SIJAINEN")) {
				sijaiset.add(rooli.getHenkilo().getObjectID());
			}
		}
		result.setJarjestelmaSijaistenIdt(sijaiset);
		result.setMuokattuPvm(dateToUrlParam(data.getRivimuokattupvm()));

		return result;
	}

	@Override
	public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
		return modelToDto(modelObject, null, null, parentId);
	}

	public ContentDto modelToDto(Haettava modelObject, List<FetchHenkiloRooliDto> fetchHenkiloRooliDtoList,
								 List<JoinJarjestelmaLinkkaus> joinJarjestelmaLinkkausList,
								 MolekyyliLinkkiDto linkData, Integer... parentId) {

		JarjestelmaDto jarjestelmaDto = (JarjestelmaDto) modelToDto(modelObject, joinJarjestelmaLinkkausList, linkData, parentId);

		if (jarjestelmaDto != null) {
			jarjestelmaDto.setFetchRooliHenkiloList(fetchHenkiloRooliDtoList);
		}
		return jarjestelmaDto;
	}

	private ContentDto modelToDto(Haettava modelObject, List<JoinJarjestelmaLinkkaus> joinJarjestelmaLinkkausList,
								  MolekyyliLinkkiDto linkData, Integer... parentId) {
		if (modelObject == null)
			return null;
		Jarjestelma data = (Jarjestelma) modelObject;
		JarjestelmaDto result = new JarjestelmaDto();
		result.setTunnus(data.getTunnus());
		result.setNimi(data.getNimi());
		result.setKuvaus(data.getKuvaus());
		result.setJarjestelmatyyppi(data.getJarjestelmatyyppi());
		result.setLyhennetty_nimi(data.getLyhennetty_nimi());
		result.setKayttajaryhmat(data.getKayttajaryhmat());
		result.setElinkaaritila(data.getElinkaaritila());
		result.setToimittaja(data.getToimittaja());
		result.setToimittajan_yhteyshenkilo(data.getToimittajan_yhteyshenkilo());
		result.setSisaiset_kayttajat(data.getSisaiset_kayttajat());
		result.setLisenssitieto(data.getLisenssitieto());
		result.setAlfresco_linkki(data.getAlfresco_linkki());
		result.setKriittisyys(data.getKriittisyys());
		result.setMuut_toimittajat(data.getMuut_toimittajat());
		result.setVersio(data.getVersio());
		result.setTietoj_kriittisyys_toiminnalle(data.getTietoj_kriittisyys_toiminnalle());
		result.setTietovarastot(data.getTietovarastot());
		result.setPaasynhallinta(data.getPaasynhallinta());
		result.setPortaalipalvelu(data.getPortaalipalvelu());
		result.setTunnistetut_kehitystarpeet(data.getTunnistetut_kehitystarpeet());
		result.setToteutusteknologia(data.getToteutusteknologia());
		result.setSisaisten_kayttajien_maara(data.getSisaisten_kayttajien_maara());
		result.setPalvelin(data.getPalvelin());
		result.setTekninen_elinkaari(data.getTekninen_elinkaari());
		result.setKayttooikeuksien_hallinta(data.getKayttooikeuksien_hallinta());
		result.setKayttoonottovuosi(data.getKayttoonottovuosi());
		result.setHuomautus_esimerkki(data.getHuomautus_esimerkki());
		result.setKehityspanos_arvioitu(data.getKehityspanos_arvioitu());
		result.setKehityskulut_arvioidut(data.getKehityskulut_arvioidut());
		result.setPalveluaika(data.getPalveluaika());
		result.setLiik_turvallisuusluokka(data.getLiik_turvallisuusluokka());
		result.setTunnistetut_riskit(data.getTunnistetut_riskit());
		result.setLuokituksen_tarkastuspvm(Utils.dateToStr(data.getLuokituksen_tarkastuspvm()));
		result.setTietoturvatasoluokitus(data.getTietoturvatasoluokitus());
		result.setIct_varautumisen_luokitus(data.getIct_varautumisen_luokitus());
		result.setSla_luokitus_jhs(data.getSla_luokitus_jhs());
		result.setSla_luokitus_kayttopalvelu(data.getSla_luokitus_kayttopalvelu());
		result.setJarjestelman_tarkeys_liville(data.getJarjestelman_tarkeys_liville());
		result.setJarj_tarkeys_yhteistyokump(data.getJarj_tarkeys_yhteistyokump());
		result.setTurvallisuuskuvaus_laadittu(data.getTurvallisuuskuvaus_laadittu());
		result.setToipumissuunnitelma_laadittu(data.getToipumissuunnitelma_laadittu());
		result.setKehitystarve(data.getKehitystarve());
		result.setKayttotiheys(data.getKayttotiheys());
		result.setUlkoiset_kayttajat(data.getUlkoiset_kayttajat());
		result.setPoistovuosi(data.getPoistovuosi());
		result.setTietolahteet(data.getTietolahteet());
		result.setSalassa_pidettavat_tiedot(data.getSalassa_pidettavat_tiedot());
		result.setJulkiset_tiedot_ryhmittain(data.getJulkiset_tiedot_ryhmittain());
		result.setTietoj_internet_osoite(data.getTietoj_internet_osoite());
		result.setTietojen_julkisuus(data.getTietojen_julkisuus());
		result.setRekisteriseloste(data.getRekisteriseloste());
		result.setToimittajan_tekn_yh(data.getToimittajan_tekn_yh());
		result.setYhteisk_kriit_jarj(data.getYhteisk_kriit_jarj());
		result.setJarj_tarkeys_yk(data.getJarj_tarkeys_yk());
		result.setJarjestelmaalue(data.getJarjestelmaalue());
		result.setTietoturvasopimus((data.getTietoturvasopimus() == null) ? null : data.getTietoturvasopimus() == 1);
		List<JarjestelmaLinkkausDto> jarjestelmaLinkkausList = new ArrayList<JarjestelmaLinkkausDto>();
		for (JoinJarjestelmaLinkkaus jarjestelmaLinkkaus : joinJarjestelmaLinkkausList) {
			jarjestelmaLinkkausList.add(jarjestelmaLinkkausModelToDto(jarjestelmaLinkkaus));
		}
		result.setJarjestelmaLinkkausList(jarjestelmaLinkkausList);
		result.setLinkData(linkData);
		result.setConfluence_linkki(data.getConfluence_linkki());
		result.setKayttopalvelun_toimittaja(data.getKayttopalvelun_toimittaja());
		result.setOmistava_organisaatio(data.getOmistava_organisaatio());
		result.setBudjetti(data.getBudjetti());
		result.setRahoitusmomentti(data.getRahoitusmomentti());
		result.setSalassapidon_peruste(data.getSalassapidon_peruste());
		result.setTurvallisuusluokitus(data.getTurvallisuusluokitus());
		result.setTarkeinta(data.getTarkeinta());
		result.setTiedonluottamuksellisuus(data.getTiedonluottamuksellisuus());
		result.setTiedon_saatavuus(data.getTiedon_saatavuus());
		result.setTiedon_eheys(data.getTiedon_eheys());
		result.setTarvitaan_viikonloppuna(data.getTarvitaan_viikonloppuna());
		result.setTarvitaan_audit_trail(data.getTarvitaan_audit_trail());
		result.setIntegraatioita_muihin(data.getIntegraatioita_muihin());

		return result;
	}

	private JarjestelmaLinkkausDto jarjestelmaLinkkausModelToDto(JoinJarjestelmaLinkkaus jarjestelmaLinkkaus) {
		JarjestelmaLinkkausDto linkkausDto = new JarjestelmaLinkkausDto();
		linkkausDto.setId(jarjestelmaLinkkaus.getRivitunnus());
		linkkausDto.setTietojarjestelmaTunnus(jarjestelmaLinkkaus.getParentNode());
		linkkausDto.setLinkattavaTunnus(jarjestelmaLinkkaus.getChildNode());
		linkkausDto.setSuunta(jarjestelmaLinkkaus.getSuunta());
		linkkausDto.setTietojarjestelmapalveluTunnus(jarjestelmaLinkkaus.getTietojarjestelmapalveluTunnus());
		linkkausDto.setTyyppi(jarjestelmaLinkkaus.getTyyppi());
		linkkausDto.setTietovirta(jarjestelmaLinkkaus.getKuvaus());
		linkkausDto.setElinkaaritila(jarjestelmaLinkkaus.getElinkaaritila());
		return linkkausDto;
	}
}
