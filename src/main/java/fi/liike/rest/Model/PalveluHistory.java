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
@Table(name = "palveluhistoria")
public class PalveluHistory implements Serializable, HaettavaHistory {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
	@SequenceGenerator(name = "seq_gen", sequenceName = "palveluhist_id_seq")
	@Column(name = "RIVI_ID", unique = true, nullable = false)
	private Long rivi_id;

	@Column(name = "PALVELUTUNNUS")
	private Integer tunnus;

	@Column(name = "HISTORIATYYPPI")
	@Enumerated(EnumType.STRING)
	private HistoryType historiatyyppi;

    @Column(name="YLATASO")
    private String ylataso;

    @Column(name="OTSIKKO")
    private String otsikko;
    
    @Column(name="NIMI")
    private String nimi;

    @Column(name="KUVAUS")
    private String kuvaus;

    @Column(name="VASTUUHENKILO")
    private String vastuuhenkilo;
    
    @Column(name="ASIAKKAAT")
    private String asiakkaat;

	@Column(name="SAATAVUUS")
    private String saatavuus;

    @Column(name="VASTEAJAT")
    private String vasteajat;

    @Column(name="OHJE_PT")
    private String ohje_pt;

    @Column(name="OHJEISTUS")
    private String ohjeistus;

    @Column(name="OHJESAANNOT")
    private String ohjesaannot;

    @Column(name="RIVILUOTUPVM")
	private Timestamp riviluotupvm;

    @Column(name="RIVIMUOKKAAJATUNNUS")
    private String rivimuokkaajatunnus;

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

	public String getYlataso() {
		return ylataso;
	}

	public void setYlataso(String ylataso) {
		this.ylataso = ylataso;
	}

	public String getOtsikko() {
		return otsikko;
	}

	public void setOtsikko(String otsikko) {
		this.otsikko = otsikko;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	@Override
	public String toString() {
		return "PalveluHistory[tunnus=" + tunnus + ", nimi=" + nimi + "]";
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public String getVastuuhenkilo() {
		return vastuuhenkilo;
	}

	public void setVastuuhenkilo(String vastuuhenkilo) {
		this.vastuuhenkilo = vastuuhenkilo;
	}
	
    public String getAsiakkaat() {
		return asiakkaat;
	}

	public void setAsiakkaat(String asiakkaat) {
		this.asiakkaat = asiakkaat;
	}

	public String getSaatavuus() {
		return saatavuus;
	}

	public void setSaatavuus(String saatavuus) {
		this.saatavuus = saatavuus;
	}

	public String getVasteajat() {
		return vasteajat;
	}

	public void setVasteajat(String vasteajat) {
		this.vasteajat = vasteajat;
	}

	public String getOhje_pt() {
		return ohje_pt;
	}

	public void setOhje_pt(String ohje_pt) {
		this.ohje_pt = ohje_pt;
	}

	public String getOhjeistus() {
		return ohjeistus;
	}

	public void setOhjeistus(String ohjeistus) {
		this.ohjeistus = ohjeistus;
	}

	public String getOhjesaannot() {
		return ohjesaannot;
	}

	public void setOhjesaannot(String ohjesaannot) {
		this.ohjesaannot = ohjesaannot;
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

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public Long getRivi_id() {
		return rivi_id;
	}

}
