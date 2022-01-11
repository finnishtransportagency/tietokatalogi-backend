package fi.liike.rest.api.dto;

import java.util.List;
import java.util.Set;

public class TietojarjestelmapalveluDto extends ContentDtoWithRights {
    private Integer tunnus;

    private String nimi;

    private String kuvaus;

    private String kayttajaroolit;

    private Integer jarjestelma;

    private String elinkaaritila;

    private Set<AnnotatedTietolajiDto> tietolajit;

    private Set<TietoryhmaMinimalDto> tietoryhmat;

    private String rivimuokkaajatunnus;

    private List<FetchHenkiloRooliDto> fetchRooliHenkiloList;

    private List<Integer> relatedJarjestelmaIds;


    public String getKayttajaroolit() {
        return kayttajaroolit;
    }

    public void setKayttajaroolit(String kayttajaroolit) {
        this.kayttajaroolit = kayttajaroolit;
    }

    public Integer getJarjestelma() {
        return jarjestelma;
    }

    public void setJarjestelma(Integer jarjestelma) {
        this.jarjestelma = jarjestelma;
    }

    public String getElinkaaritila() {
        return elinkaaritila;
    }

    public void setElinkaaritila(String elinkaaritila) {
        this.elinkaaritila = elinkaaritila;
    }

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
        return tunnus;
    }

    @Override
    public String getNimi() {
        return nimi;
    }

    @Override
    public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
        this.rivimuokkaajatunnus = rivimuokkaajatunnus;
    }

    @Override
    public String getRivimuokkaajatunnus() {
        return rivimuokkaajatunnus;
    }

    public Set<AnnotatedTietolajiDto> getTietolajit() {
        return tietolajit;
    }

    public void setTietolajit(Set<AnnotatedTietolajiDto> tietolajit) {
        this.tietolajit = tietolajit;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public Set<TietoryhmaMinimalDto> getTietoryhmat() {
        return tietoryhmat;
    }

    public void setTietoryhmat(Set<TietoryhmaMinimalDto> tietoryhmat) {
        this.tietoryhmat = tietoryhmat;
    }

    public void setHenkiloRooliList(List<FetchHenkiloRooliDto> henkiloRooliList) {
        this.fetchRooliHenkiloList = henkiloRooliList;
    }

    public List<Integer> getRelatedJarjestelmaIds() {
        return relatedJarjestelmaIds;
    }

    public void setRelatedJarjestelmaIds(List<Integer> relatedJarjestelmaIds) {
        this.relatedJarjestelmaIds = relatedJarjestelmaIds;
    }
}
