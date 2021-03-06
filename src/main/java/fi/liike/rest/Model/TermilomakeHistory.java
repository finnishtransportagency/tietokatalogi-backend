package fi.liike.rest.Model;


import fi.liike.rest.api.HistoryType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "TERMILOMAKEHISTORIA")
public class TermilomakeHistory implements java.io.Serializable, HaettavaHistory {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "termilomakehist_id_seq")
    @Column(name = "RIVI_ID", unique = true, nullable = false)
    private int rivi_id;

    @Column(name = "termilomaketunnus")
    private Integer tunnus;

    @Column(name = "muokattava_tunnus")
    private Integer muokattava_tunnus;

    @Column(name = "HISTORIATYYPPI")
    @Enumerated(EnumType.STRING)
    private HistoryType historiatyyppi;

    @Column(name = "nimi")
    private String nimi;

    @Column(name = "ensisij_termi")
    private String ensisij_termi;

    @Column(name = "synonyymit")
    private String synonyymit;

    @Column(name = "ei_suosit_termi")
    private String ei_suosit_termi;

    @Column(name = "kayttoaluerajaus")
    private String kayttoaluerajaus;

    @Column(name = "maaritelma")
    private String maaritelma;

    @Column(name = "kommentit")
    private String kommentit;

    @Column(name = "valmis")
    private String valmis;

    @Column(name = "lahde")
    private String lahde;

    @Column(name = "omistaja")
    private String omistaja;

    @Column(name = "ydinkasite")
    private String ydinkasite;

    @Column(name = "hakutermit")
    private String hakutermit;

    @Column(name = "pdf_linkki")
    private String pdf_linkki;

    @Column(name = "pdf_linkki_nimi")
    private String pdf_linkki_nimi;

    @Column(name = "kasitekaavio_linkki")
    private String kasitekaavio_linkki;

    @Column(name = "kasitekaavio_linkki_nimi")
    private String kasitekaavio_linkki_nimi;

    @Column(name = "riviluotupvm")
    private Timestamp riviluotupvm;

    @Column(name = "rivimuokattupvm")
    private Timestamp rivimuokattupvm;

    @Column(name = "rivimuokkaajatunnus")
    private String rivimuokkaajatunnus;

    public TermilomakeHistory() {}

    public Integer getTunnus() {
        return tunnus;
    }

    public void setTunnus(Integer tunnus) {
        this.tunnus = tunnus;
    }

    public HistoryType getHistoriatyyppi() {
        return historiatyyppi;
    }

    public void setHistoriatyyppi(HistoryType historiatyyppi) {
        this.historiatyyppi = historiatyyppi;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getEnsisij_termi() {
        return ensisij_termi;
    }

    public void setEnsisij_termi(String ensisij_termi) {
        this.ensisij_termi = ensisij_termi;
    }

    public String getSynonyymit() {
        return synonyymit;
    }

    public void setSynonyymit(String synonyymit) {
        this.synonyymit = synonyymit;
    }

    public String getEi_suosit_termi() {
        return ei_suosit_termi;
    }

    public void setEi_suosit_termi(String ei_suosit_termi) {
        this.ei_suosit_termi = ei_suosit_termi;
    }

    public String getKayttoaluerajaus() {
        return kayttoaluerajaus;
    }

    public void setKayttoaluerajaus(String kayttoaluerajaus) {
        this.kayttoaluerajaus = kayttoaluerajaus;
    }

    public String getMaaritelma() {
        return maaritelma;
    }

    public void setMaaritelma(String maaritelma) {
        this.maaritelma = maaritelma;
    }

    public String getKommentit() {
        return kommentit;
    }

    public void setKommentit(String kommentit) {
        this.kommentit = kommentit;
    }

    public String getValmis() {
        return valmis;
    }

    public void setValmis(String valmis) {
        this.valmis = valmis;
    }

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

    public String getYdinkasite() {
        return ydinkasite;
    }

    public void setYdinkasite(String ydinkasite) {
        this.ydinkasite = ydinkasite;
    }

    public String getHakutermit() {
        return hakutermit;
    }

    public void setHakutermit(String hakutermit) {
        this.hakutermit = hakutermit;
    }

    public String getPdf_linkki() {
        return pdf_linkki;
    }

    public void setPdf_linkki(String pdf_linkki) {
        this.pdf_linkki = pdf_linkki;
    }

    public Integer getMuokattava_tunnus() {
        return muokattava_tunnus;
    }

    public void setMuokattava_tunnus(Integer muokattava_tunnus) {
        this.muokattava_tunnus = muokattava_tunnus;
    }

    public String getPdf_linkki_nimi() {
        return pdf_linkki_nimi;
    }

    public void setPdf_linkki_nimi(String pdf_linkki_nimi) {
        this.pdf_linkki_nimi = pdf_linkki_nimi;
    }

    public String getKasitekaavio_linkki() {
        return kasitekaavio_linkki;
    }

    public void setKasitekaavio_linkki(String kasitekaavio_linkki) {
        this.kasitekaavio_linkki = kasitekaavio_linkki;
    }

    public String getKasitekaavio_linkki_nimi() {
        return kasitekaavio_linkki_nimi;
    }

    public void setKasitekaavio_linkki_nimi(String kasitekaavio_linkki_nimi) {
        this.kasitekaavio_linkki_nimi = kasitekaavio_linkki_nimi;
    }

    @Override
    public Timestamp getRiviluotupvm() {
        return this.riviluotupvm;
    }

    @Override
    public void setRiviluotupvm(Timestamp riviluotupvm) {
        this.riviluotupvm = riviluotupvm;
    }

    public Timestamp getRivimuokattupvm() {
        return rivimuokattupvm;
    }

    public void setRivimuokattupvm(Timestamp rivimuokattupvm) {
        this.rivimuokattupvm = rivimuokattupvm;
    }

    @Override
    public void setRivimuokkaajatunnus(String remoteUser) {
        this.rivimuokkaajatunnus = remoteUser;
    }

    @Override
    public String getRivimuokkaajatunnus() {
        return this.rivimuokkaajatunnus;
    }

    @Override
    public String toString() {
        return "Termilomake [tunnus=" + tunnus + ", nimi=" + nimi + "]";
    }
}
