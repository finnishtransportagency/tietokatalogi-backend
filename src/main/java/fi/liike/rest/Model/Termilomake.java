package fi.liike.rest.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "termilomake")
public class Termilomake extends Haettava implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    public static final String seq_name = "termilomake_id_seq";


    @Id
    @Column(name = "termilomaketunnus", unique = true, nullable = false)
    private Integer tunnus;

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

    @Column(name = "huomautus_1")
    private String huomautus_1;

    @Column(name = "huomautus_2")
    private String huomautus_2;

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

    @Column(name = "rivimuokattupvm")
    private Timestamp rivimuokattupvm;

    @Column(name = "riviluotupvm")
    private Timestamp riviluotupvm;

    @Column(name = "rivimuokkaajatunnus")
    private String rivimuokkaajatunnus;


    public Termilomake() {}

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

    public String getHuomautus_1() {
        return huomautus_1;
    }

    public void setHuomautus_1(String huomautus_1) {
        this.huomautus_1 = huomautus_1;
    }

    public String getHuomautus_2() {
        return huomautus_2;
    }

    public void setHuomautus_2(String huomautus_2) {
        this.huomautus_2 = huomautus_2;
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
    public String getRivimuokkaajatunnus() {
        return rivimuokkaajatunnus;
    }

    @Override
    public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
        this.rivimuokkaajatunnus = rivimuokkaajatunnus;
    }

    @Override
    public String toString() {
        return "Termilomake [tunnus=" + tunnus + ", nimi=" + nimi + "]";
    }

}
