package fi.liike.rest.Model;

import fi.liike.rest.api.HistoryType;

import java.sql.Timestamp;

public abstract class JoinHenkiloRooliTableHistory {
    public abstract Integer getRivitunnus();

    public abstract void setRivitunnus(Integer rivitunnus);

    public abstract Integer getHenkiloId();

    public abstract void setHenkiloId(Integer henkiloId);

    public abstract Integer getRooliId();

    public abstract void setRooliId(Integer rooliId);

    //E.g jarjestelmaId
    public abstract Integer getSysteemiId();

    public abstract void setSysteemiId(Integer systeemiId);

    public abstract HistoryType getHistoriatyyppi();

    public abstract void setHistoriatyyppi(HistoryType historiatyyppi);

    public abstract Timestamp getRiviluotupvm();

    public abstract void setRiviluotupvm(Timestamp riviluotupvm);

    public abstract String getRivimuokkaajatunnus();

    public abstract void setRivimuokkaajatunnus(String rivimuokkaajatunnus);

}
