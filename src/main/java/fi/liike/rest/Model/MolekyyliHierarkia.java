package fi.liike.rest.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Deprecated
@Entity
@Table(name="apu_hierarkia_3_2")
public class MolekyyliHierarkia extends Haettava implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="tietovirta_id", unique=true)
    private String id;

    @Column(name="liittyy_1")
    private String lahdeNimi;

    @Column(name="tunnus_1")
    private String lahdeTunnus;

    @Column(name="varanto_1")
    private String lahdeTaulu;

    @Column(name="liittyy_2")
    private String kohdeNimi;

    @Column(name="tunnus_2")
    private String kohdeTunnus;

    @Column(name="varanto_2")
    private String kohdeTaulu;

    @Column(name="tyyppi")
    private String tyyppiNimi;

    @Column(name="linkki")
    private String linkki;

    @Column(name="tietosisalto")
    private String tietosisalto;

    public String getLahdeNimi() {
        return lahdeNimi;
    }

    public void setLahdeNimi(String lahdeNimi) {
        this.lahdeNimi = lahdeNimi;
    }

    public String getLahdeTunnus() {
        return lahdeTunnus;
    }

    public void setLahdeTunnus(String lahdeTunnus) {
        this.lahdeTunnus = lahdeTunnus;
    }

    public String getLahdeTaulu() {
        return lahdeTaulu;
    }

    public void setLahdeTaulu(String lahdeTaulu) {
        this.lahdeTaulu = lahdeTaulu;
    }

    public String getKohdeNimi() {
        return kohdeNimi;
    }

    public void setKohdeNimi(String kohdeNimi) {
        this.kohdeNimi = kohdeNimi;
    }

    public String getKohdeTunnus() {
        return kohdeTunnus;
    }

    public void setKohdeTunnus(String kohdeTunnus) {
        this.kohdeTunnus = kohdeTunnus;
    }

    public String getKohdeTaulu() {
        return kohdeTaulu;
    }

    public void setKohdeTaulu(String kohdeTaulu) {
        this.kohdeTaulu = kohdeTaulu;
    }
    //tyyppi kertoo onko kyseessä tietoarkkitehtuuri vai jarjestelmapuolen tietoja
    /*public String TyypiNimi() {
        return tyyppiNimi;
    }*/

    public void setTyyppiNimi(String tyyppiNimi) {
        this.tyyppiNimi = kohdeTaulu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLinkki() {
        return linkki;
    }

    public void setLinkki(String linkki) {
        this.linkki = linkki;
    }

    public String getTietosisalto() {
        return tietosisalto;
    }

    public void setTietosisalto(String tietosisalto) {
        this.tietosisalto = tietosisalto;
    }

    public String getTyyppiNimi() {
        return tyyppiNimi;
    }



    /*
	@EmbeddedId
	private MolekyyliPK molekyyliPK;

	public MolekyyliHierarkia(){
	}

	public MolekyyliPK getMolekyyliPK() {
		return molekyyliPK;
	}

	public void setMolekyyliPK(MolekyyliPK molekyyliPK) {
		this.molekyyliPK = molekyyliPK;
	}*/

    /*@Embeddable
	public static class MolekyyliPK implements Serializable{

		public MolekyyliPK(){
		}

		private static final long serialVersionUID = 1L;

		@Column(name="liittyy_1")
		private String lahdeNimi;

		@Column(name="tunnus_1")
		private String lahdeTunnus;

		@Column(name="varanto_1")
		private String lahdeTaulu;

		@Column(name="liittyy_2")
		private String kohdeNimi;

		@Column(name="tunnus_2")
		private String kohdeTunnus;

		@Column(name="varanto_2")
		private String kohdeTaulu;

		@Column(name="tyyppi")
		private String tyyppiNimi;

		public String getLahdeNimi() {
			return lahdeNimi;
		}

		public void setLahdeNimi(String lahdeNimi) {
			this.lahdeNimi = lahdeNimi;
		}

		public String getLahdeTunnus() {
			return lahdeTunnus;
		}

		public void setLahdeTunnus(String lahdeTunnus) {
			this.lahdeTunnus = lahdeTunnus;
		}

		public String getLahdeTaulu() {
			return lahdeTaulu;
		}

		public void setLahdeTaulu(String lahdeTaulu) {
			this.lahdeTaulu = lahdeTaulu;
		}

		public String getKohdeNimi() {
			return kohdeNimi;
		}

		public void setKohdeNimi(String kohdeNimi) {
			this.kohdeNimi = kohdeNimi;
		}

		public String getKohdeTunnus() {
			return kohdeTunnus;
		}

		public void setKohdeTunnus(String kohdeTunnus) {
			this.kohdeTunnus = kohdeTunnus;
		}

		public String getKohdeTaulu() {
			return kohdeTaulu;
		}

		public void setKohdeTaulu(String kohdeTaulu) {
			this.kohdeTaulu = kohdeTaulu;
		}
		//tyyppi kertoo onko kyseessä tietoarkkitehtuuri vai jarjestelmapuolen tietoja
		public String TyypiNimi() {
			return tyyppiNimi;
		}

		public void setTyyppiNimi(String tyyppiNimi) {
			this.tyyppiNimi = kohdeTaulu;
		}
	}*/
}
