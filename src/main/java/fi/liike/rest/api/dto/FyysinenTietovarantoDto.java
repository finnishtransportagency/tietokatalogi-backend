package fi.liike.rest.api.dto;

public class FyysinenTietovarantoDto extends ContentDtoWithRights {

	private Integer tunnus;

	private String nimi;

	private String koodi;

	private String kuvaus;

	private String id;

	private String tietokantateknologia;

	private String palvelutaso;

	private Integer koko;

	private Integer tietuemaara;

	private String linkki;

	private String muuta;

	private String teknologia;

	private String rivimuokkaajatunnus;

	private String sijainti;

	private String omistaja;

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
	public String toString() {
		return "Fyysinen [tunnus=" + tunnus + ", nimi=" + nimi + "]";
	}

	public String getKoodi() {
		return koodi;
	}

	public void setKoodi(String koodi) {
		this.koodi = koodi;
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTietokantateknologia() {
		return tietokantateknologia;
	}

	public void setTietokantateknologia(String tietokantateknologia) {
		this.tietokantateknologia = tietokantateknologia;
	}

	public String getPalvelutaso() {
		return palvelutaso;
	}

	public void setPalvelutaso(String palvelutaso) {
		this.palvelutaso = palvelutaso;
	}

	public Integer getKoko() {
		return koko;
	}

	public void setKoko(Integer koko) {
		this.koko = koko;
	}

	public Integer getTietuemaara() {
		return tietuemaara;
	}

	public void setTietuemaara(Integer tietuemaara) {
		this.tietuemaara = tietuemaara;
	}

	public String getLinkki() {
		return linkki;
	}

	public void setLinkki(String linkki) {
		this.linkki = linkki;
	}

	public String getMuuta() {
		return muuta;
	}

	public void setMuuta(String muuta) {
		this.muuta = muuta;
	}

	public String getTeknologia() {
		return teknologia;
	}

	public void setTeknologia(String teknologia) {
		this.teknologia = teknologia;
	}

	public String getRivimuokkaajatunnus() {
		return rivimuokkaajatunnus;
	}

	@Override
	public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
		this.rivimuokkaajatunnus = rivimuokkaajatunnus;
	}

	public String getOmistaja() {
		return omistaja;
	}

	public void setOmistaja(String omistaja) {
		this.omistaja = omistaja;
	}

	public String getSijainti() {
		return sijainti;
	}

	public void setSijainti(String sijainti) {
		this.sijainti = sijainti;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FyysinenTietovarantoDto that = (FyysinenTietovarantoDto) o;

		if (tunnus != null ? !tunnus.equals(that.tunnus) : that.tunnus != null) return false;
		if (nimi != null ? !nimi.equals(that.nimi) : that.nimi != null) return false;
		if (koodi != null ? !koodi.equals(that.koodi) : that.koodi != null) return false;
		if (kuvaus != null ? !kuvaus.equals(that.kuvaus) : that.kuvaus != null) return false;
		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (tietokantateknologia != null ? !tietokantateknologia.equals(that.tietokantateknologia) : that.tietokantateknologia != null)
			return false;
		if (palvelutaso != null ? !palvelutaso.equals(that.palvelutaso) : that.palvelutaso != null) return false;
		if (koko != null ? !koko.equals(that.koko) : that.koko != null) return false;
		if (tietuemaara != null ? !tietuemaara.equals(that.tietuemaara) : that.tietuemaara != null) return false;
		if (linkki != null ? !linkki.equals(that.linkki) : that.linkki != null) return false;
		if (muuta != null ? !muuta.equals(that.muuta) : that.muuta != null) return false;
		if (teknologia != null ? !teknologia.equals(that.teknologia) : that.teknologia != null) return false;
		if (rivimuokkaajatunnus != null ? !rivimuokkaajatunnus.equals(that.rivimuokkaajatunnus) : that.rivimuokkaajatunnus != null)
			return false;
		if (sijainti != null ? !sijainti.equals(that.sijainti) : that.sijainti != null) return false;
		return omistaja != null ? omistaja.equals(that.omistaja) : that.omistaja == null;
	}

	@Override
	public int hashCode() {
		int result = tunnus != null ? tunnus.hashCode() : 0;
		result = 31 * result + (nimi != null ? nimi.hashCode() : 0);
		result = 31 * result + (koodi != null ? koodi.hashCode() : 0);
		result = 31 * result + (kuvaus != null ? kuvaus.hashCode() : 0);
		result = 31 * result + (id != null ? id.hashCode() : 0);
		result = 31 * result + (tietokantateknologia != null ? tietokantateknologia.hashCode() : 0);
		result = 31 * result + (palvelutaso != null ? palvelutaso.hashCode() : 0);
		result = 31 * result + (koko != null ? koko.hashCode() : 0);
		result = 31 * result + (tietuemaara != null ? tietuemaara.hashCode() : 0);
		result = 31 * result + (linkki != null ? linkki.hashCode() : 0);
		result = 31 * result + (muuta != null ? muuta.hashCode() : 0);
		result = 31 * result + (teknologia != null ? teknologia.hashCode() : 0);
		result = 31 * result + (rivimuokkaajatunnus != null ? rivimuokkaajatunnus.hashCode() : 0);
		result = 31 * result + (sijainti != null ? sijainti.hashCode() : 0);
		result = 31 * result + (omistaja != null ? omistaja.hashCode() : 0);
		return result;
	}
}
