package fi.liike.rest.Model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="tieto")
public class Tietolaji extends Haettava implements Serializable {

	private static final long serialVersionUID = -1L;
	
	@Id
	@Column(name = "TIETOTUNNUS", unique = true, nullable = false)
	private Integer tunnus;

	@Column(name="LOOGINENTIETOVARANTOTUNNUS")
	private Integer looginenTietovarantoTunnus;
	
	@Column(name="TIETORYHMATUNNUS")
	private Integer tietoryhmatunnus;
	
	@Column(name="NIMI")
	private String nimi;

	@Column(name="KOODI")
	private String koodi;
	
	@Column(name="KUVAUS")
	private String kuvaus;
	
	@Column(name="OMISTAJA")
	private String omistaja;
	
	@Column(name="LAHDE")
	private String lahde;
	
	@Column(name="TILA")
	private String tila;
	
	@Column(name="SYNONYYMI")
	private String synonyymi;
	
	@Column(name="TIETOSUOJATASO")
	private String tietosuojataso;

	@Column(name="YLEMPITASO")
	private Integer ylempitaso;

	@Column(name="RIVITUNNUS")
	private Integer rivitunnus;
	
	@Column(name="RIVITILA")
	private String rivitila;
	
	@Column(name="RIVIMUOKATTUPVM")
	private Timestamp rivimuokattupvm;
	
	@Column(name="RIVIMUOKKAAJATUNNUS")
	private String rivimuokkaajatunnus;
	
	@Column(name="RIVILUOTUPVM")
	private Timestamp riviluotupvm;
	
	@Column(name="DOCUMENT_ID")
	private String documentId;

	@ManyToMany(mappedBy = "tietolajit")
	private Set<TietojarjestelmapalveluFetch> tietojarjestelmapalvelut;

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

	public String getDocumentId() {
		return documentId;
	}

	public void setDocument_id(String documentId) {
		this.documentId = documentId;
	}


	public Set<TietojarjestelmapalveluFetch> getTietojarjestelmapalvelut() {
		return tietojarjestelmapalvelut;
	}

	public void setTietojarjestelmapalvelut(Set<TietojarjestelmapalveluFetch> tietojarjestelmapalvelut) {
		this.tietojarjestelmapalvelut = tietojarjestelmapalvelut;
	}


	@Override
	public String toString() {
		return "Tietolaji{" +
				"tunnus=" + tunnus +
				", looginenTietovarantoTunnus=" + looginenTietovarantoTunnus +
				", tietoryhmatunnus=" + tietoryhmatunnus +
				", nimi='" + nimi + '\'' +
				", kuvaus='" + kuvaus + '\'' +
				'}';
	}
}
