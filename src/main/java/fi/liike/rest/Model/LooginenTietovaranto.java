package fi.liike.rest.Model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "looginentietovaranto")
public class LooginenTietovaranto extends Haettava implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "looginentietovarantotunnus", unique = true, nullable = false)
	private Integer tunnus;

	@Column(name = "nimi")
	private String nimi;

	@Column(name = "kuvaus")
	private String kuvaus;

	@Column(name = "omistaja")
	private String omistaja;

	@Column(name = "kuvauskeskeinentieto")
	private String kuvauskeskeinentieto;

	@Column(name = "kuvausvarastointifyysinen")
	private String kuvausvarastointifyysinen;

	@Column(name = "paivitystiheys")
	private String paivitystiheys;

	@Column(name = "kuvauskayttaja")
	private String kuvauskayttaja;

	@Column(name = "kayttaja")
	private String kayttaja;

	@Column(name = "kuvaustoimitus")
	private String kuvaustoimitus;

	@Column(name = "kasitemalli")
	private String kasitemalli;

	@Column(name = "document_id")
	private String documentId;

	@Column(name = "rivimuokattupvm")
	private Timestamp rivimuokattupvm;

	@Column(name = "riviluotupvm")
	private Timestamp riviluotupvm;

	@Column(name = "rivimuokkaajatunnus")
	private String rivimuokkaajatunnus;

	@Column(name = "alue")
	private String alue;

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

	public String getKuvauskeskeinentieto() {
		return kuvauskeskeinentieto;
	}

	public void setKuvauskeskeinentieto(String kuvauskeskeinentieto) {
		this.kuvauskeskeinentieto = kuvauskeskeinentieto;
	}

	public String getKuvausvarastointifyysinen() {
		return kuvausvarastointifyysinen;
	}

	public void setKuvausvarastointifyysinen(String kuvausvarastointifyysinen) {
		this.kuvausvarastointifyysinen = kuvausvarastointifyysinen;
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

	public String getKuvaustoimitus() {
		return kuvaustoimitus;
	}

	public void setKuvaustoimitus(String kuvaustoimitus) {
		this.kuvaustoimitus = kuvaustoimitus;
	}

	public String getKasitemalli() {
		return kasitemalli;
	}

	public void setKasitemalli(String kasitemalli) {
		this.kasitemalli = kasitemalli;
	}

	public LooginenTietovaranto() {
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

	public String getKuvaus() {
		return kuvaus;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
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
		return "Looginen [tunnus=" + tunnus + ", nimi=" + nimi + "]";
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

}
