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
@Table(name = "PAATIETORYHMAHISTORIA")
public class PaatietoryhmaHistory implements java.io.Serializable, HaettavaHistory {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
	@SequenceGenerator(name = "seq_gen", sequenceName = "paatietoryhmahist_id_seq")
	@Column(name = "RIVI_ID", unique = true, nullable = false)
	private int rivi_id;

	@Column(name = "paatietoryhmatunnus")
	private Integer tunnus;

	@Column(name = "HISTORIATYYPPI")
	@Enumerated(EnumType.STRING)
	private HistoryType historiatyyppi;
	
	@Column(name="nimi")
	private String nimi;
	
	@Column(name="koodi")
	private String koodi;
	
	@Column(name="kuvaus")
	private String kuvaus;
	
	@Column(name="lahde")
	private String lahde;
	
	@Column(name="omistaja")
	private String omistaja;
	
	@Column(name="tila")
	private String tila;
	
	@Column(name="synonyymi")
	private String synonyymi;
	
	@Column(name="tietosuojataso")
	private String tietosuojataso;
	
	@Column(name="julkaisutieto")
	private String julkaisutieto;
	
	@Column(name="julkaisuhuomio")
	private String julkaisuhuomio;
	
	@Column(name="document_id")
	private String documentId;

	@Column(name = "riviluotupvm")
	private Timestamp riviluotupvm;

	@Column(name = "rivimuokkaajatunnus")
	private String rivimuokkaajatunnus;

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
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

	public String getJulkaisutieto() {
		return julkaisutieto;
	}

	public void setJulkaisutieto(String julkaisutieto) {
		this.julkaisutieto = julkaisutieto;
	}

	public String getJulkaisuhuomio() {
		return julkaisuhuomio;
	}

	public void setJulkaisuhuomio(String julkaisuhuomio) {
		this.julkaisuhuomio = julkaisuhuomio;
	}

	public PaatietoryhmaHistory(){
		
	}

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

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}


	public void setKoodi(String koodi) {
		this.koodi = koodi;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}


	public void setLahde(String lahde) {
		this.lahde = lahde;
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

	public String getLahde() {
		return lahde;
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
		return "Paatietoryhma [tunnus=" + tunnus + ", nimi=" + nimi + "]";
	}

	public String getRivimuokkaajatunnus() {
		return rivimuokkaajatunnus;
	}

	@Override
	public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
		this.rivimuokkaajatunnus = rivimuokkaajatunnus;
	}

}
