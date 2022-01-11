package fi.liike.rest.api.dto;

import java.sql.Timestamp;

public class TermilomakeHuomautusDto extends ContentDtoWithRights {

    private Integer tunnus;
    private String nimi;
    private String huomautus;

    private String rivimuokkaajatunnus;
    private Timestamp rivimuokattupvm;
    private Timestamp riviluotupvm;

    public String getHuomautus() {
        return huomautus;
    }

    public void setHuomautus(String huomautus) {
        this.huomautus = huomautus;
    }

    public Timestamp getRivimuokattupvm() {
        return rivimuokattupvm;
    }

    public void setRivimuokattupvm(Timestamp rivimuokattupvm) {
        this.rivimuokattupvm = rivimuokattupvm;
    }

    public Timestamp getRiviluotupvm() {
        return riviluotupvm;
    }

    public void setRiviluotupvm(Timestamp riviluotupvm) {
        this.riviluotupvm = riviluotupvm;
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

    @Override
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }


    @Override
    public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
        this.rivimuokkaajatunnus = rivimuokkaajatunnus;
    }

    @Override
    public String getRivimuokkaajatunnus() {
        return this.rivimuokkaajatunnus;
    }
}
