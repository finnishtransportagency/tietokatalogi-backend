package fi.liike.rest.Model;

import java.sql.Timestamp;

import fi.liike.rest.api.HistoryType;

public interface HaettavaHistory {

	public HistoryType getHistoriatyyppi();

	public void setHistoriatyyppi(HistoryType historiatyyppi);

	Timestamp getRiviluotupvm();

	void setRiviluotupvm(Timestamp riviluotupvm);

	public void setRivimuokkaajatunnus(String remoteUser);

	public String getRivimuokkaajatunnus();
}
