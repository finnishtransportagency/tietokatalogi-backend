package fi.liike.rest.Model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import fi.liike.rest.api.HistoryType;

@Entity
@Table(name = "TIETOHISTORIA")
public class TietolajiHistory implements java.io.Serializable, HaettavaHistory {

	private static final long serialVersionUID = -1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
	@SequenceGenerator(name = "seq_gen", sequenceName = "tietolajihist_id_seq")
	@Column(name = "RIVI_ID", unique = true, nullable = false)
	private int rivi_id;

	@Column(name = "TIETOTUNNUS", nullable = false)
	private Integer tunnus;

	@Column(name = "HISTORIATYYPPI")
	@Enumerated(EnumType.STRING)
	private HistoryType historiatyyppi;

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
	
	@Column(name="RIVIMUOKKAAJATUNNUS")
	private String rivimuokkaajatunnus;
	
	@Column(name="RIVILUOTUPVM")
	private Timestamp riviluotupvm;
	
	@Column(name="DOCUMENT_ID")
	private String documentId;

	public Integer getTunnus() {
		return tunnus;
	}

	public void setTunnus(Integer tunnus) {
		this.tunnus = tunnus;
	}

	@Override
	public HistoryType getHistoriatyyppi() {
		return historiatyyppi;
	}

	@Override
	public void setHistoriatyyppi(HistoryType historiatyyppi) {
		this.historiatyyppi = historiatyyppi;
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

	public Integer getRivi_id() {
		return rivi_id;
	}

	@Override
	public String toString() {
		return "Tietolaji [tunnus=" + tunnus + ", nimi=" + nimi + "]";
	}

}
