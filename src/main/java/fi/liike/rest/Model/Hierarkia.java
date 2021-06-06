package fi.liike.rest.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Deprecated
@Entity
@Table(name="apu_hierarkia")
public class Hierarkia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -612079249739557828L;
	
	@EmbeddedId
	private HierarkiaPK hierarkiaPK;
	
	public Hierarkia() {
	}
	
	public HierarkiaPK getHierarkiaPK() {
		return hierarkiaPK;
	}

	public void setHierarkiaPK(HierarkiaPK hierarkiaPK) {
		this.hierarkiaPK = hierarkiaPK;
	}

	@Embeddable
	public static class HierarkiaPK implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6189704258758190407L;
		
		@Column(name="tr_nimi")
		private String tietoryhma;
		
		@Column(name="ptr_nimi")
		private String paatietoryhma;
		
		@Column(name="lt_nimi")
		private String logTietovaranto;
		
		@Column(name="ft_nimi")
		private String fysTietovaranto;
		
		public String getTietoryhma() {
			return tietoryhma;
		}

		public void setTietoryhma(String tietoryhma) {
			this.tietoryhma = tietoryhma;
		}

		public String getPaatietoryhma() {
			return paatietoryhma;
		}

		public void setPaatietoryhma(String paatietoryhma) {
			this.paatietoryhma = paatietoryhma;
		}

		public String getLogTietovaranto() {
			return logTietovaranto;
		}

		public void setLogTietovaranto(String logTietovaranto) {
			this.logTietovaranto = logTietovaranto;
		}

		public String getFysTietovaranto() {
			return fysTietovaranto;
		}

		public void setFysTietovaranto(String fysTietovaranto) {
			this.fysTietovaranto = fysTietovaranto;
		}
		
		public HierarkiaPK(){
		}
		
		public HierarkiaPK(String fysTietovaranto, String logTietovaranto, String tietoryhma,
				String paatietoryhma){
			this.fysTietovaranto = fysTietovaranto;
			this.logTietovaranto = logTietovaranto;
			this.tietoryhma = tietoryhma;
			this.paatietoryhma = paatietoryhma;
		}
	}
}
