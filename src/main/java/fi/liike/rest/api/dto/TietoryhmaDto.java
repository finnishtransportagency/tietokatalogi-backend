package fi.liike.rest.api.dto;

public class TietoryhmaDto extends ContentDtoWithRights {

	private Integer tunnus;

	private Integer paatietoryhma;

	private String nimi;

	private String koodi;

	private String kuvaus;

	private String lahde;

	private String omistaja;

	private String tila;

	private String synonyymi;

	private String tietosuojataso;

	private Integer tietovaranto;

	private String rivimuokkaajatunnus;

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

	public Integer getPaatietoryhma() {
		return paatietoryhma;
	}

	public void setPaatietoryhma(Integer paatietoryhma) {
		this.paatietoryhma = paatietoryhma;
	}

	@Override
	public void setTunnus(Integer tunnus) {
		this.tunnus = tunnus;
	}


	@Override
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public void setKoodi(String koodi) {
		this.koodi = koodi;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}


	@Override
	public Integer getTunnus() {
		return tunnus;
	}

	@Override
	public String getNimi() {
		return nimi;
	}

	public String getKoodi() {
		return koodi;
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public Integer getTietovaranto() {
		return tietovaranto;
	}

	public void setTietovaranto(Integer tietovaranto) {
		this.tietovaranto = tietovaranto;
	}

	public String getRivimuokkaajatunnus() {
		return rivimuokkaajatunnus;
	}

	@Override
	public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
		this.rivimuokkaajatunnus = rivimuokkaajatunnus;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TietoryhmaDto that = (TietoryhmaDto) o;

		if (tunnus != null ? !tunnus.equals(that.tunnus) : that.tunnus != null) return false;
		if (paatietoryhma != null ? !paatietoryhma.equals(that.paatietoryhma) : that.paatietoryhma != null)
			return false;
		if (nimi != null ? !nimi.equals(that.nimi) : that.nimi != null) return false;
		if (koodi != null ? !koodi.equals(that.koodi) : that.koodi != null) return false;
		if (kuvaus != null ? !kuvaus.equals(that.kuvaus) : that.kuvaus != null) return false;
		if (lahde != null ? !lahde.equals(that.lahde) : that.lahde != null) return false;
		if (omistaja != null ? !omistaja.equals(that.omistaja) : that.omistaja != null) return false;
		if (tila != null ? !tila.equals(that.tila) : that.tila != null) return false;
		if (synonyymi != null ? !synonyymi.equals(that.synonyymi) : that.synonyymi != null) return false;
		if (tietosuojataso != null ? !tietosuojataso.equals(that.tietosuojataso) : that.tietosuojataso != null)
			return false;
		return rivimuokkaajatunnus != null ? rivimuokkaajatunnus.equals(that.rivimuokkaajatunnus) : that.rivimuokkaajatunnus == null;
	}

	@Override
	public int hashCode() {
		int result = tunnus != null ? tunnus.hashCode() : 0;
		result = 31 * result + (paatietoryhma != null ? paatietoryhma.hashCode() : 0);
		result = 31 * result + (nimi != null ? nimi.hashCode() : 0);
		result = 31 * result + (koodi != null ? koodi.hashCode() : 0);
		result = 31 * result + (kuvaus != null ? kuvaus.hashCode() : 0);
		result = 31 * result + (lahde != null ? lahde.hashCode() : 0);
		result = 31 * result + (omistaja != null ? omistaja.hashCode() : 0);
		result = 31 * result + (tila != null ? tila.hashCode() : 0);
		result = 31 * result + (synonyymi != null ? synonyymi.hashCode() : 0);
		result = 31 * result + (tietosuojataso != null ? tietosuojataso.hashCode() : 0);
		result = 31 * result + (rivimuokkaajatunnus != null ? rivimuokkaajatunnus.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "TietoryhmaDto{" +
				"tunnus=" + tunnus +
				", paatietoryhma=" + paatietoryhma +
				", nimi='" + nimi + '\'' +
				'}';
	}
}
