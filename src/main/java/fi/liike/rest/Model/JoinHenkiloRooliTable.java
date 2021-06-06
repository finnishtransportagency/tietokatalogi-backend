package fi.liike.rest.Model;

public abstract class JoinHenkiloRooliTable {
    public abstract Integer getRivitunnus();

    public abstract void setRivitunnus(Integer rivitunnus);

    public abstract Integer getHenkiloId();

    public abstract void setHenkiloId(Integer henkiloId);

    public abstract Integer getRooliId();

    public abstract void setRooliId(Integer rooliId);

    //E.g jarjestelmaId
    public abstract Integer getSysteemiId();

    public abstract void setSysteemiId(Integer systeemiId);

    public abstract String getRivimuokkaajatunnus();

    public abstract void setRivimuokkaajatunnus(String rivimuokkaajatunnus);

}
