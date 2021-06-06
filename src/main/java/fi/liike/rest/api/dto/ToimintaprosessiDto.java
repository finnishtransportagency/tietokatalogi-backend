package fi.liike.rest.api.dto;

public class ToimintaprosessiDto extends ContentDtoWithRights {

    private String nimi;
    private Integer tunnus;
    private String rivimuokkaajatunnus;
    private String vastaava_organisaatio;
    private String tarkoitus;

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

    public String getVastaava_organisaatio() {
        return vastaava_organisaatio;
    }

    public void setVastaava_organisaatio(String vastaava_organisaatio) {
        this.vastaava_organisaatio = vastaava_organisaatio;
    }

    public String getTarkoitus() {
        return tarkoitus;
    }

    public void setTarkoitus(String tarkoitus) {
        this.tarkoitus = tarkoitus;
    }
}
