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
@Table(name = "TIETORYHMAHISTORIA")
public class TietoryhmaHistory implements java.io.Serializable, HaettavaHistory {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
	@SequenceGenerator(name = "seq_gen", sequenceName = "tietoryhmahist_id_seq")
	@Column(name = "rivi_id", unique = true, nullable = false)
	private int rivi_id;

	@Column(name = "tietoryhmatunnus")
	private Integer tunnus;

	@Column(name = "HISTORIATYYPPI")
	@Enumerated(EnumType.STRING)
	private HistoryType historiatyyppi;

	@Column(name = "paatietoryhmatunnus")
	private Integer paatietoryhma;

	@Column(name = "nimi")
	private String nimi;

	@Column(name = "koodi")
	private String koodi;

	@Column(name = "kuvaus")
	private String kuvaus;

	@Column(name = "lahde")
	private String lahde;

	@Column(name = "omistaja")
	private String omistaja;

	@Column(name = "tila")
	private String tila;

	@Column(name = "synonyymi")
	private String synonyymi;

	@Column(name = "tietosuojataso")
	private String tietosuojataso;

	@Column(name = "tietovarantotunnus")
	private Integer tietovaranto;

	@Column(name = "riviluotupvm")
	private Timestamp riviluotupvm;

	@Column(name = "rivimuokkaajatunnus")
	private String rivimuokkaajatunnus;

	/*
	 * document_id missing from the DB, so commenting this out for now.
	 * 
	 * @Column(name="document_id") private String documentId;
	 * 
	 * public String getDocumentId() { return documentId; }
	 * 
	 * public void setDocumentId(String documentId) { this.documentId =
	 * documentId; }
	 */

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

	public TietoryhmaHistory() {
	}

	public Integer getPaatietoryhma() {
		return paatietoryhma;
	}

	public void setPaatietoryhma(Integer paatietoryhma) {
		this.paatietoryhma = paatietoryhma;
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

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public void setKoodi(String koodi) {
		this.koodi = koodi;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public Integer getTunnus() {
		return tunnus;
	}

	public String getNimi() {
		return nimi;
	}

	public String getKoodi() {
		return koodi;
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public Integer getRivi_id() {
		return rivi_id;
	}

	@Override
	public Timestamp getRiviluotupvm() {
		return riviluotupvm;
	}

	@Override
	public void setRiviluotupvm(Timestamp riviluotupvm) {
		this.riviluotupvm = riviluotupvm;
	}

	@Override
	public String toString() {
		return "Tietoryhma [tunnus=" + tunnus + ", nimi=" + nimi + "]";
	}

	public String getRivimuokkaajatunnus() {
		return rivimuokkaajatunnus;
	}

	@Override
	public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
		this.rivimuokkaajatunnus = rivimuokkaajatunnus;
	}

	public Integer getTietovaranto() {
		return tietovaranto;
	}

	public void setTietovaranto(Integer tietovaranto) {
		this.tietovaranto = tietovaranto;
	}
}
