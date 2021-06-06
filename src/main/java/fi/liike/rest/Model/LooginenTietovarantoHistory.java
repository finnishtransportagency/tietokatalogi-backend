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
@Table(name = "LOOGINENTIETOVARANTOHISTORIA")
public class LooginenTietovarantoHistory implements java.io.Serializable, HaettavaHistory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
	@SequenceGenerator(name = "seq_gen", sequenceName = "looginentvhist_id_seq")
	@Column(name = "RIVI_ID", unique = true, nullable = false)
	private int rivi_id;

	@Column(name = "looginentietovarantotunnus")
	private Integer tunnus;

	@Column(name = "HISTORIATYYPPI")
	@Enumerated(EnumType.STRING)
	private HistoryType historiatyyppi;

	@Column(name = "nimi")
	private String nimi;

	@Column(name = "koodi")
	private String koodi;

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

	public LooginenTietovarantoHistory() {
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

	@Override
	public Timestamp getRiviluotupvm() {
		return riviluotupvm;
	}

	@Override
	public void setRiviluotupvm(Timestamp riviluotupvm) {
		this.riviluotupvm = riviluotupvm;
	}

	public Integer getRivi_id() {
		return rivi_id;
	}

	public void setRivi_id(int rivi_id) {
		this.rivi_id = rivi_id;
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

}
