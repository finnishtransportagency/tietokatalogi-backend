package fi.liike.rest.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tietotietoryhma")
public class JoinTietolajiTietoryhma extends JoinTable implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
	@SequenceGenerator(name = "seq_gen", sequenceName = "jointietolajitietoryhma_id_seq")
	@Column(name = "rivitunnus", unique = true, nullable = false)
	private int rivi_id;

	@Column(name = "tietotunnus")
	private Integer childNode;

	@Column(name = "tietoryhmatunnus")
	private Integer parentNode;

	@Column(name = "rivitila")
	private String rivitila = "A";

	public JoinTietolajiTietoryhma() {
	}

	public JoinTietolajiTietoryhma(int childNode) {
		this.childNode = childNode;
	}

	public JoinTietolajiTietoryhma(int childNode, int parentNode) {
		this.childNode = childNode;
		this.parentNode = parentNode;
	}

	@Override
	public String toString() {
		return "JoinTietolajiTietoryhma[childNode=" + childNode + ", parentNode=" + parentNode + "]";
	}

	@Override
	public Integer getChildNode() {
		return childNode;
	}

	@Override
	public void setChildNode(Integer childNode) {
		this.childNode = childNode;
	}

	@Override
	public Integer getParentNode() {
		return parentNode;
	}

	@Override
	public void setParentNode(Integer parentNode) {
		this.parentNode = parentNode;
	}

	@Override
	public String getRivitila() {
		return rivitila;
	}

	@Override
	public void setRivitila(String rivitila) {
		this.rivitila = rivitila;
	}

}
