package fi.liike.rest.api.dto;

import java.util.List;

public class LooginenTietovarantoDto extends ContentDtoWithRights {

	private Integer tunnus;

	private String nimi;

	private List<Integer> jarjestelmaIds;

	private String kuvaus;

	private String omistaja;

	private String paivitystiheys;

	private String kuvauskayttaja;

	private String kayttaja;

	private String tietomalli;

	private Integer fyysinenTietovarantoId;

	private String rivimuokkaajatunnus;

	private String alue;

	public String getOmistaja() {
		return omistaja;
	}

	public void setOmistaja(String omistaja) {
		this.omistaja = omistaja;
	}

	public String getPaivitystiheys() {
		return paivitystiheys;
	}

	public void setPaivitystiheys(String paivitystiheys) {
		this.paivitystiheys = paivitystiheys;
	}

	public String getKuvauskayttaja() {
		return kuvauskayttaja;
	}

	public void setKuvauskayttaja(String kuvauskayttaja) {
		this.kuvauskayttaja = kuvauskayttaja;
	}

	public String getKayttaja() {
		return kayttaja;
	}

	public void setKayttaja(String kayttaja) {
		this.kayttaja = kayttaja;
	}

	public String getTietomalli() {
		return tietomalli;
	}

	public void setTietomalli(String tietomalli) {
		this.tietomalli = tietomalli;
	}

	public LooginenTietovarantoDto() {
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

	public List<Integer> getJarjestelmaIds() {
		return jarjestelmaIds;
	}

	public void setJarjestelmaIds(List<Integer> jarjestelmaIds) {
		this.jarjestelmaIds = jarjestelmaIds;
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public void setFyysinenTietovaranto(Integer fyysinenTietovarantoId) {
		this.fyysinenTietovarantoId = fyysinenTietovarantoId;
	}

	public Integer getFyysinenTietovarantoId() {
		return fyysinenTietovarantoId;
	}

	public String getRivimuokkaajatunnus() {
		return rivimuokkaajatunnus;
	}

	@Override
	public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
		this.rivimuokkaajatunnus = rivimuokkaajatunnus;
	}

	public String getAlue() {
		return alue;
	}

	public void setAlue(String alue) {
		this.alue = alue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		LooginenTietovarantoDto that = (LooginenTietovarantoDto) o;

		if (tunnus != null ? !tunnus.equals(that.tunnus) : that.tunnus != null) return false;
		if (nimi != null ? !nimi.equals(that.nimi) : that.nimi != null) return false;
		if (jarjestelmaIds != null ? !jarjestelmaIds.equals(that.jarjestelmaIds) : that.jarjestelmaIds != null) return false;
		if (kuvaus != null ? !kuvaus.equals(that.kuvaus) : that.kuvaus != null) return false;
		if (omistaja != null ? !omistaja.equals(that.omistaja) : that.omistaja != null) return false;
		if (paivitystiheys != null ? !paivitystiheys.equals(that.paivitystiheys) : that.paivitystiheys != null)
			return false;
		if (kuvauskayttaja != null ? !kuvauskayttaja.equals(that.kuvauskayttaja) : that.kuvauskayttaja != null)
			return false;
		if (kayttaja != null ? !kayttaja.equals(that.kayttaja) : that.kayttaja != null) return false;
		if (tietomalli != null ? !tietomalli.equals(that.tietomalli) : that.tietomalli != null) return false;
		if (fyysinenTietovarantoId != null ? !fyysinenTietovarantoId.equals(that.fyysinenTietovarantoId) : that.fyysinenTietovarantoId != null)
			return false;
		if (rivimuokkaajatunnus != null ? !rivimuokkaajatunnus.equals(that.rivimuokkaajatunnus) : that.rivimuokkaajatunnus != null)
			return false;
		return alue != null ? alue.equals(that.alue) : that.alue == null;
	}

	@Override
	public int hashCode() {
		int result = tunnus != null ? tunnus.hashCode() : 0;
		result = 31 * result + (nimi != null ? nimi.hashCode() : 0);
		result = 31 * result + (jarjestelmaIds != null ? jarjestelmaIds.hashCode() : 0);
		result = 31 * result + (kuvaus != null ? kuvaus.hashCode() : 0);
		result = 31 * result + (omistaja != null ? omistaja.hashCode() : 0);
		result = 31 * result + (paivitystiheys != null ? paivitystiheys.hashCode() : 0);
		result = 31 * result + (kuvauskayttaja != null ? kuvauskayttaja.hashCode() : 0);
		result = 31 * result + (kayttaja != null ? kayttaja.hashCode() : 0);
		result = 31 * result + (tietomalli != null ? tietomalli.hashCode() : 0);
		result = 31 * result + (fyysinenTietovarantoId != null ? fyysinenTietovarantoId.hashCode() : 0);
		result = 31 * result + (rivimuokkaajatunnus != null ? rivimuokkaajatunnus.hashCode() : 0);
		result = 31 * result + (alue != null ? alue.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "LooginenTietovarantoDto{" +
				"tunnus=" + tunnus +
				", nimi='" + nimi + '\'' +
				", fyysinenTietovarantoId=" + fyysinenTietovarantoId +
				'}';
	}
}
