package fi.liike.rest.api.dto;

import fi.liike.rest.auth.Right;

import java.util.List;

public class PalveluDto extends ContentDtoWithRights {

	private Integer tunnus;

	private String ylataso;

	private String otsikko;

	private String nimi;

	private String kuvaus;

	private String vastuuhenkilo;

	private String asiakkaat;

	private String saatavuus;

	private String vasteajat;

	private String ohje_pt;

	private String ohjeistus;

	private String ohjesaannot;

	private String rivimuokkaajatunnus;

	@Override
	public Integer getTunnus() {
		return tunnus;
	}

	@Override
	public void setTunnus(Integer tunnus) {
		this.tunnus = tunnus;
	}

	public String getYlataso() {
		return ylataso;
	}

	public void setYlataso(String ylataso) {
		this.ylataso = ylataso;
	}

	public String getOtsikko() {
		return otsikko;
	}

	public void setOtsikko(String otsikko) {
		this.otsikko = otsikko;
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
	public String toString() {
		return "Palvelu[tunnus=" + tunnus + ", nimi=" + nimi + "]";
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public String getVastuuhenkilo() {
		return vastuuhenkilo;
	}

	public void setVastuuhenkilo(String vastuuhenkilo) {
		this.vastuuhenkilo = vastuuhenkilo;
	}

	public String getAsiakkaat() {
		return asiakkaat;
	}

	public void setAsiakkaat(String asiakkaat) {
		this.asiakkaat = asiakkaat;
	}

	public String getSaatavuus() {
		return saatavuus;
	}

	public void setSaatavuus(String saatavuus) {
		this.saatavuus = saatavuus;
	}

	public String getVasteajat() {
		return vasteajat;
	}

	public void setVasteajat(String vasteajat) {
		this.vasteajat = vasteajat;
	}

	public String getOhje_pt() {
		return ohje_pt;
	}

	public void setOhje_pt(String ohje_pt) {
		this.ohje_pt = ohje_pt;
	}

	public String getOhjeistus() {
		return ohjeistus;
	}

	public void setOhjeistus(String ohjeistus) {
		this.ohjeistus = ohjeistus;
	}

	public String getOhjesaannot() {
		return ohjesaannot;
	}

	public void setOhjesaannot(String ohjesaannot) {
		this.ohjesaannot = ohjesaannot;
	}

	public String getRivimuokkaajatunnus() {
		return rivimuokkaajatunnus;
	}

	@Override
	public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
		this.rivimuokkaajatunnus = rivimuokkaajatunnus;
	}
}
