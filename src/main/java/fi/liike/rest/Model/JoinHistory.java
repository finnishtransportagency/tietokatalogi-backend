package fi.liike.rest.Model;

import java.sql.Timestamp;

import fi.liike.rest.api.HistoryType;

public interface JoinHistory {

	public Integer getChildNode();

	public void setChildNode(Integer childId);

	public Integer getParentNode();

	public void setParentNode(Integer parentId);

	public HistoryType getHistoriatyyppi();

	public void setHistoriatyyppi(HistoryType historiatyyppi);

	public Timestamp getRiviluotupvm();

	public void setRiviluotupvm(Timestamp created);

	public String getRivimuokkaajatunnus();

	public void setRivimuokkaajatunnus(String editor);

	public Long getRivitunnus();

}
