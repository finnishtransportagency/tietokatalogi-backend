package fi.liike.rest.Model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="palvelu")
public class Palvelu extends Haettava implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PALVELUTUNNUS", unique = true, nullable = false)
	private Integer tunnus;

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

	@Column(name = "RIVIMUOKATTUPVM")
	private Timestamp rivimuokattupvm;

    @Column(name="RIVIMUOKKAAJATUNNUS")
    private String rivimuokkaajatunnus;

    @Column(name="DOCUMENT_ID")
    private String documentId;

	@Override
	public Integer getTunnus() {
		return tunnus;
	}

	@Override
	public void setTunnus(Integer tunnus) {
		this.tunnus = tunnus;
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

	@Override
	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	@Override
	public String toString() {
		return "Palvelu[tunnus=" + tunnus + ", nimi=" + nimi + "]";
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

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

}
