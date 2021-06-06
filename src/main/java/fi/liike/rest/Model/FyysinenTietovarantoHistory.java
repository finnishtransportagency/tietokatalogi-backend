package fi.liike.rest.Model;

import java.io.Serializable;
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
@Table(name = "fyysinentietovarantohistoria")
public class FyysinenTietovarantoHistory implements Serializable, HaettavaHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
	@SequenceGenerator(name = "seq_gen", sequenceName = "fyysinentvhist_id_seq")
	@Column(name = "RIVI_ID", unique = true, nullable = false)
	private Long rivi_id;
	
	@Column(name = "fyysinentietovarantotunnus")
	private Integer tunnus;

	@Column(name = "HISTORIATYYPPI")
	@Enumerated(EnumType.STRING)
	private HistoryType historiatyyppi;
	
	@Column(name="nimi")
	private String nimi;
	
	@Column(name="koodi")
	private String koodi;
	
	@Column(name="sislt")
	private String kuvaus;
	
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

	public FyysinenTietovarantoHistory() {
	}

	public FyysinenTietovarantoHistory(String remoteUser) {
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

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	@Override
	public String toString() {
		return "FyysinenHistory [tunnus=" + tunnus + ", nimi=" + nimi + "]";
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

}
