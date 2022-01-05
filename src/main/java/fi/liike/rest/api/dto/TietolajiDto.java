package fi.liike.rest.api.dto;

public class TietolajiDto extends ContentDtoWithRights {

	private Integer tunnus;

	private Integer looginenTietovarantoTunnus;

	private Integer tietoryhmatunnus;

	private String nimi;

	private String koodi;

	private String kuvaus;

	private String omistaja;

	private String lahde;

	private String tila;

	private String synonyymi;

	private String tietosuojataso;

	private Integer ylempitaso;

	private Integer rivitunnus;

	private String rivitila;

	private String rivimuokkaajatunnus;

	@Override
	public Integer getTunnus() {
		return tunnus;
	}

	@Override
	public void setTunnus(Integer tunnus) {
		this.tunnus = tunnus;
	}

	public Integer getLooginenTietovarantoTunnus() {
		return looginenTietovarantoTunnus;
	}

	public void setLooginenTietovarantoTunnus(Integer looginenTietovarantoTunnus) {
		this.looginenTietovarantoTunnus = looginenTietovarantoTunnus;
	}

	public Integer getTietoryhmatunnus() {
		return tietoryhmatunnus;
	}

	public void setTietoryhmatunnus(Integer tietoryhmatunnus) {
		this.tietoryhmatunnus = tietoryhmatunnus;
	}

	@Override
	public String getNimi() {
		return nimi;
	}

	@Override
	public void setNimi(String nimi) {
		this.nimi = nimi;
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

	public String getOmistaja() {
		return omistaja;
	}

	public void setOmistaja(String omistaja) {
		this.omistaja = omistaja;
	}

	public String getLahde() {
		return lahde;
	}

	public void setLahde(String lahde) {
		this.lahde = lahde;
	}

	public String getTila() {
		return tila;
	}

	public void setTila(String tila) {
		this.tila = tila;
	}

	public String getSynonyymi() {
		return synonyymi;
	}

	public void setSynonyymi(String synonyymi) {
		this.synonyymi = synonyymi;
	}

	public String getTietosuojataso() {
		return tietosuojataso;
	}

	public void setTietosuojataso(String tietosuojataso) {
		this.tietosuojataso = tietosuojataso;
	}

	public Integer getYlempitaso() {
		return ylempitaso;
	}

	public void setYlempitaso(Integer ylempitaso) {
		this.ylempitaso = ylempitaso;
	}

	public Integer getRivitunnus() {
		return rivitunnus;
	}

	public void setRivitunnus(Integer rivitunnus) {
		this.rivitunnus = rivitunnus;
	}

	public String getRivitila() {
		return rivitila;
	}

	public void setRivitila(String rivitila) {
		this.rivitila = rivitila;
	}

	public String getRivimuokkaajatunnus() {
		return rivimuokkaajatunnus;
	}

	@Override
	public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
		this.rivimuokkaajatunnus = rivimuokkaajatunnus;
	}

	@Override
	public String toString() {
		return "TietolajiDto{" +
				"tunnus=" + tunnus +
				", looginenTietovarantoTunnus=" + looginenTietovarantoTunnus +
				", tietoryhmatunnus=" + tietoryhmatunnus +
				", nimi='" + nimi + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TietolajiDto that = (TietolajiDto) o;

		if (tunnus != null ? !tunnus.equals(that.tunnus) : that.tunnus != null) return false;
		if (looginenTietovarantoTunnus != null ? !looginenTietovarantoTunnus.equals(that.looginenTietovarantoTunnus) : that.looginenTietovarantoTunnus != null)
			return false;
		if (tietoryhmatunnus != null ? !tietoryhmatunnus.equals(that.tietoryhmatunnus) : that.tietoryhmatunnus != null)
			return false;
		if (nimi != null ? !nimi.equals(that.nimi) : that.nimi != null) return false;
		if (koodi != null ? !koodi.equals(that.koodi) : that.koodi != null) return false;
		if (kuvaus != null ? !kuvaus.equals(that.kuvaus) : that.kuvaus != null) return false;
		if (omistaja != null ? !omistaja.equals(that.omistaja) : that.omistaja != null) return false;
		if (lahde != null ? !lahde.equals(that.lahde) : that.lahde != null) return false;
		if (tila != null ? !tila.equals(that.tila) : that.tila != null) return false;
		if (synonyymi != null ? !synonyymi.equals(that.synonyymi) : that.synonyymi != null) return false;
		if (tietosuojataso != null ? !tietosuojataso.equals(that.tietosuojataso) : that.tietosuojataso != null)
			return false;
		if (ylempitaso != null ? !ylempitaso.equals(that.ylempitaso) : that.ylempitaso != null) return false;
		if (rivitunnus != null ? !rivitunnus.equals(that.rivitunnus) : that.rivitunnus != null) return false;
		if (rivitila != null ? !rivitila.equals(that.rivitila) : that.rivitila != null) return false;
		return rivimuokkaajatunnus != null ? rivimuokkaajatunnus.equals(that.rivimuokkaajatunnus) : that.rivimuokkaajatunnus == null;
	}

	@Override
	public int hashCode() {
		int result = tunnus != null ? tunnus.hashCode() : 0;
		result = 31 * result + (looginenTietovarantoTunnus != null ? looginenTietovarantoTunnus.hashCode() : 0);
		result = 31 * result + (tietoryhmatunnus != null ? tietoryhmatunnus.hashCode() : 0);
		result = 31 * result + (nimi != null ? nimi.hashCode() : 0);
		result = 31 * result + (koodi != null ? koodi.hashCode() : 0);
		result = 31 * result + (kuvaus != null ? kuvaus.hashCode() : 0);
		result = 31 * result + (omistaja != null ? omistaja.hashCode() : 0);
		result = 31 * result + (lahde != null ? lahde.hashCode() : 0);
		result = 31 * result + (tila != null ? tila.hashCode() : 0);
		result = 31 * result + (synonyymi != null ? synonyymi.hashCode() : 0);
		result = 31 * result + (tietosuojataso != null ? tietosuojataso.hashCode() : 0);
		result = 31 * result + (ylempitaso != null ? ylempitaso.hashCode() : 0);
		result = 31 * result + (rivitunnus != null ? rivitunnus.hashCode() : 0);
		result = 31 * result + (rivitila != null ? rivitila.hashCode() : 0);
		result = 31 * result + (rivimuokkaajatunnus != null ? rivimuokkaajatunnus.hashCode() : 0);
		return result;
	}
}
