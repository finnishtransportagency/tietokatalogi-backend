package fi.liike.rest.Model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PAATIETORYHMA")
public class Paatietoryhma extends Haettava implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "paatietoryhmatunnus", unique = true, nullable = false)
	private Integer tunnus;
	
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

	@Column(name = "rivimuokattupvm")
	private Timestamp rivimuokattupvm;

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

	public Paatietoryhma(){
		
	}

	@Override
	public Integer getTunnus() {
		return tunnus;
	}
	
	@Override
	public void setTunnus(Integer tunnus) {
		this.tunnus = tunnus;
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

	public String getLahde() {
		return lahde;
	}

	@Override
	public Timestamp getRivimuokattupvm() {
		return rivimuokattupvm;
	}

	@Override
	public void setRivimuokattupvm(Timestamp rivimuokattupvm) {
		this.rivimuokattupvm = rivimuokattupvm;
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
