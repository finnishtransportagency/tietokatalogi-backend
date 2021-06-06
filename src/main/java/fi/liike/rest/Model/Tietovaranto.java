package fi.liike.rest.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "TIETOVARANTO")
public class Tietovaranto extends Haettava implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String seq_name = "tietovaranto_id_seq";

    @Id
    @Column(name = "tietovarantotunnus", unique = true, nullable = false)
    private Integer tunnus;

    @Column(name = "nimi")
    private String nimi;

    @Column(name = "kuvaus")
    private String kuvaus;

    @Column(name = "vastaava")
    private String vastaava;

    @Column(name = "lisatieto")
    private String lisatieto;

    @Column(name = "kuvaus_sidoksesta")
    private String kuvaus_sidoksesta;

    @Column(name = "rekisterinpitaja")
    private String rekisterinpitaja;

    @Column(name = "kasittelyn_tarkoitus")
    private String kasittelyn_tarkoitus;

    @Column(name = "viittaus_henktieto_sopimukseen")
    private String viittaus_henktieto_sopimukseen;

    @Column(name = "kolmannet_maat_ja_jarjestot")
    private String kolmannet_maat_ja_jarjestot;

    @Column(name = "suojatoimia_dokumentaatio")
    private String suojatoimia_dokumentaatio;

    @Column(name = "tekniset_org_turvatoimenpiteet")
    private String tekniset_org_turvatoimenpiteet;

    @Column(name = "rivimuokkaajatunnus")
    private String rivimuokkaajatunnus;

    @Column(name = "rivimuokattupvm")
    private Timestamp rivimuokattupvm;

    @Column(name = "riviluotupvm")
    private Timestamp riviluotupvm;

    public Tietovaranto() {
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

    public String getVastaava() {
        return vastaava;
    }

    public void setVastaava(String vastaava) {
        this.vastaava = vastaava;
    }

    public String getLisatieto() {
        return lisatieto;
    }

    public void setLisatieto(String lisatieto) {
        this.lisatieto = lisatieto;
    }

    public String getKuvaus_sidoksesta() {
        return kuvaus_sidoksesta;
    }

    public void setKuvaus_sidoksesta(String kuvaus_sidoksesta) {
        this.kuvaus_sidoksesta = kuvaus_sidoksesta;
    }

    public String getRekisterinpitaja() {
        return rekisterinpitaja;
    }

    public void setRekisterinpitaja(String rekisterinpitaja) {
        this.rekisterinpitaja = rekisterinpitaja;
    }

    public String getKasittelyn_tarkoitus() {
        return kasittelyn_tarkoitus;
    }

    public void setKasittelyn_tarkoitus(String kasittelyn_tarkoitus) {
        this.kasittelyn_tarkoitus = kasittelyn_tarkoitus;
    }

    public String getViittaus_henktieto_sopimukseen() {
        return viittaus_henktieto_sopimukseen;
    }

    public void setViittaus_henktieto_sopimukseen(String viittaus_henktieto_sopimukseen) {
        this.viittaus_henktieto_sopimukseen = viittaus_henktieto_sopimukseen;
    }

    public String getKolmannet_maat_ja_jarjestot() {
        return kolmannet_maat_ja_jarjestot;
    }

    public void setKolmannet_maat_ja_jarjestot(String kolmannet_maat_ja_jarjestot) {
        this.kolmannet_maat_ja_jarjestot = kolmannet_maat_ja_jarjestot;
    }

    public String getSuojatoimia_dokumentaatio() {
        return suojatoimia_dokumentaatio;
    }

    public void setSuojatoimia_dokumentaatio(String suojatoimia_dokumentaatio) {
        this.suojatoimia_dokumentaatio = suojatoimia_dokumentaatio;
    }

    public String getTekniset_org_turvatoimenpiteet() {
        return tekniset_org_turvatoimenpiteet;
    }

    public void setTekniset_org_turvatoimenpiteet(String tekniset_org_turvatoimenpiteet) {
        this.tekniset_org_turvatoimenpiteet = tekniset_org_turvatoimenpiteet;
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
}
