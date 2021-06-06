package fi.liike.rest.Model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import fi.liike.rest.api.HistoryType;

@Entity
@Table(name = "tietoryhmapaatietohistoria")
public class JoinTietoryhmaPaatietoryhmaHistory implements java.io.Serializable, JoinHistory {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "RIVITUNNUS", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
	@SequenceGenerator(name = "seq_gen", sequenceName = "jtietorpaatietorhist_id_seq")
	private Long rivitunnus;

	@Column(name = "tietoryhmatunnus")
	private Integer childNode;

	@Column(name = "paatietoryhmatunnus")
	private Integer parentNode;

	@Column(name = "HISTORIATYYPPI")
	@Enumerated(EnumType.STRING)
	private HistoryType historiatyyppi;

	@Column(name = "rivitila")
	private String rivitila = "A";

	@Column(name = "riviluotupvm")
	private Timestamp riviluotupvm;

	@Column(name = "rivimuokkaajatunnus")
	private String rivimuokkaajatunnus;

	public JoinTietoryhmaPaatietoryhmaHistory() {
	}

	public JoinTietoryhmaPaatietoryhmaHistory(int childNode) {
		this.childNode = childNode;
	}

	public JoinTietoryhmaPaatietoryhmaHistory(int childNode, int parentNode) {
		this.childNode = childNode;
		this.parentNode = parentNode;
	}

	@Override
	public String toString() {
		return "JoinTietoryhmaPaatietoryhmaHistory[childNode=" + childNode + ", parentNode=" + parentNode
				+ ", historyType=" + historiatyyppi + "]";
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
	public Timestamp getRiviluotupvm() {
		return riviluotupvm;
	}

	@Override
	public void setRiviluotupvm(Timestamp riviluotupvm) {
		this.riviluotupvm = riviluotupvm;
	}

	@Override
	public String getRivimuokkaajatunnus() {
		return rivimuokkaajatunnus;
	}

	@Override
	public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
		this.rivimuokkaajatunnus = rivimuokkaajatunnus;
	}

	@Override
	public Long getRivitunnus() {
		return rivitunnus;
	}

	@Override
	public HistoryType getHistoriatyyppi() {
		return historiatyyppi;
	}

	@Override
	public void setHistoriatyyppi(HistoryType historiatyyppi) {
		this.historiatyyppi = historiatyyppi;
	}

}
