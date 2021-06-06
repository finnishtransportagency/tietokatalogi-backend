package fi.liike.rest.Model;


public abstract class JoinTable {

	public abstract Integer getChildNode();

	public abstract void setChildNode(Integer childNode);

	public abstract Integer getParentNode();

	public abstract void setParentNode(Integer parentNode);

	public abstract void setRivitila(String rivitila);

	public abstract String getRivitila();

}
