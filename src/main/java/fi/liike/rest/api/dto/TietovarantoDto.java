package fi.liike.rest.api.dto;

import java.util.List;

public class TietovarantoDto extends ContentDtoWithRights {

    private String nimi;
    private Integer tunnus;
    private String rivimuokkaajatunnus;
    private String kuvaus;
    private String vastaava;
    private String lisatieto;
    private List<Integer> toimintaprosessiIds;
    private String kuvaus_sidoksesta;
    private String rekisterinpitaja;
    private String kasittelyn_tarkoitus;
    private String viittaus_henktieto_sopimukseen;
    private String kolmannet_maat_ja_jarjestot;
    private String suojatoimia_dokumentaatio;
    private String tekniset_org_turvatoimenpiteet;
    private List<String> yhteisrekisterinpitajat;
    private List<String> rekisteroityjenryhmat;
    private List<String> henkilotietojenryhmat;
    private List<String> turvatoimenpiteet;
    private List<String> kasittelyn_perusteet;
    private List<String> yllapito_muut_tahot;
    private List<String> vastaanottajaryhmat;
    private List<String> tiedonohjaussuunnitelmat;

    @Override
    public void setNimi(String name) {
        this.nimi = name;
    }

    @Override
    public void setTunnus(Integer id) {
        this.tunnus = id;
    }

    @Override
    public Integer getTunnus() {
        return this.tunnus;
    }

    @Override
    public String getNimi() {
        return this.nimi;
    }

    @Override
    public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
        this.rivimuokkaajatunnus = rivimuokkaajatunnus;
    }

    @Override
    public String getRivimuokkaajatunnus() {
        return this.rivimuokkaajatunnus;
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

    public List<Integer> getToimintaprosessiIds() {
        return toimintaprosessiIds;
    }

    public void setToimintaprosessiIds(List<Integer> toimintaprosessiIds) {
        this.toimintaprosessiIds = toimintaprosessiIds;
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

    public List<String> getYhteisrekisterinpitajat() {
        return yhteisrekisterinpitajat;
    }

    public void setYhteisrekisterinpitajat(List<String> yhteisrekisterinpitajat) {
        this.yhteisrekisterinpitajat = yhteisrekisterinpitajat;
    }

    public List<String> getRekisteroityjenryhmat() {
        return rekisteroityjenryhmat;
    }

    public void setRekisteroityjenryhmat(List<String> rekisteroityjenryhmat) {
        this.rekisteroityjenryhmat = rekisteroityjenryhmat;
    }

    public List<String> getHenkilotietojenryhmat() {
        return henkilotietojenryhmat;
    }

    public void setHenkilotietojenryhmat(List<String> henkilotietojenryhmat) {
        this.henkilotietojenryhmat = henkilotietojenryhmat;
    }

    public List<String> getTurvatoimenpiteet() {
        return turvatoimenpiteet;
    }

    public void setTurvatoimenpiteet(List<String> turvatoimenpiteet) {
        this.turvatoimenpiteet = turvatoimenpiteet;
    }

    public List<String> getKasittelyn_perusteet() {
        return kasittelyn_perusteet;
    }

    public void setKasittelyn_perusteet(List<String> kasittelyn_perusteet) {
        this.kasittelyn_perusteet = kasittelyn_perusteet;
    }

    public List<String> getYllapito_muut_tahot() {
        return yllapito_muut_tahot;
    }

    public void setYllapito_muut_tahot(List<String> yllapito_muut_tahot) {
        this.yllapito_muut_tahot = yllapito_muut_tahot;
    }

    public List<String> getVastaanottajaryhmat() {
        return vastaanottajaryhmat;
    }

    public void setVastaanottajaryhmat(List<String> vastaanottajaryhmat) {
        this.vastaanottajaryhmat = vastaanottajaryhmat;
    }

    public List<String> getTiedonohjaussuunnitelmat() {
        return tiedonohjaussuunnitelmat;
    }

    public void setTiedonohjaussuunnitelmat(List<String> tiedonohjaussuunnitelmat) {
        this.tiedonohjaussuunnitelmat = tiedonohjaussuunnitelmat;
    }
}
