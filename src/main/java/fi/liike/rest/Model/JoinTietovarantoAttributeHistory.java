package fi.liike.rest.Model;

import fi.liike.rest.api.HistoryType;

import java.sql.Timestamp;

public abstract class JoinTietovarantoAttributeHistory extends JoinTable {
    public abstract Integer getRivi_id();

    public abstract void setRivi_id(Integer rivi_id);

    public abstract String getAttribuuttiarvo();

    public abstract void setAttribuuttiarvo(String attribuuttiarvo);

    public abstract HistoryType getHistoriatyyppi();

    public abstract void setHistoriatyyppi(HistoryType historiatyyppi);

    public abstract Timestamp getRiviluotupvm();

    public abstract void setRiviluotupvm(Timestamp riviluotupvm);

    public abstract String getRivimuokkaajatunnus();

    public abstract void setRivimuokkaajatunnus(String rivimuokkaajatunnus);
}
