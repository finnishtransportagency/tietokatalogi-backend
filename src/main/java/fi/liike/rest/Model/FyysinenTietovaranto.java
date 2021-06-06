package fi.liike.rest.Model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fyysinentietovaranto")
public class FyysinenTietovaranto extends Haettava implements Serializable {

	@Id
	@Column(name = "fyysinentietovarantotunnus", unique = true, nullable = false)
	private Integer tunnus;
	
	@Column(name="nimi")
	private String nimi;
	
	@Column(name="koodi")
	private String koodi;
	
	@Column(name="sislt")
	private String kuvaus;
	
	@Column(name = "id")
	private String id;

	@Column(name="tietokantateknologia")
	private String tietokantateknologia;
	
	@Column(name="palvelutaso")
	private String palvelutaso;
	
	@Column(name="koko")
	private String koko;
	
	@Column(name="tietuemaara")
	private String tietuemaara;
	
	@Column(name="linkki")
	private String linkki;
	
	@Column(name="muuta")
	private String muuta;
	
	@Column(name="teknologia")
	private String teknologia;
	
	@Column(name="document_id")
	private String documentId;

	@Column(name = "rivimuokattupvm")
	private Timestamp rivimuokattupvm;

	@Column(name = "riviluotupvm")
	private Timestamp riviluotupvm;

	@Column(name = "rivimuokkaajatunnus")
	private String rivimuokkaajatunnus;

	@Column(name = "omistaja")
	private String omistaja;

	@Column(name = "sijainti")
	private String sijainti;

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FyysinenTietovaranto() {
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

	public String getKoko() {
		return koko;
	}

	public void setKoko(String koko) {
		this.koko = koko;
	}

	public String getTietuemaara() {
		return tietuemaara;
	}

	public void setTietuemaara(String tietuemaara) {
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

	public String getRivimuokkaajatunnus() {
		return rivimuokkaajatunnus;
	}

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

}
