package fi.liike.rest.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity @IdClass(SovellusTempID.class)
@Table(name = "SOVELLUS_TEMP")
public class SovellusTemp implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NIMI", nullable = false)
    private String nimi;

    @Column(name = "VALMISTAJA")
    private String valmistaja;

    @Column(name = "ALIAS_NIMET")
    private String aliasNimet;

    @Id
    @Column(name = "VERSIO")
    private String versio;

    @Id
    @Column(name = "TUOTEKOODI")
    private String tuotekoodi;

    @Column(name = "KONFIGURAATIO_VERSIO")
    private String konfiguraatioVersio;

    @Column(name = "LISATIETOJA")
    private String lisatietoja;

    @Column(name = "SOVELLUS_TYYPPI")
    private String sovellusTyyppi;

    @Column(name = "KIELISYYS")
    private String kielisyys;

    @Column(name = "KAYTTOJARJESTELMAVAATIMUS")
    private String kayttojarjestelmaVaatimus;

    @Column(name = "ARKKITEHTUURI")
    private String arkkitehtuuri;

    @Column(name = "ALUSTA")
    private String alusta;

    @Column(name = "RIIPPUVUUSTIETO")
    private String riippuvuustieto;

    @Column(name = "LIITTYMAT_JARJESTELMIIN")
    private String liittymatJarjestelmiin;

    @Column(name = "TUOTANTOON_HYVAKSYMISPAIVA")
    private Date tuotantoonHyvaksymisPaiva;

    @Column(name = "KRIITTISYYS")
    private String kriittisyys;

    public SovellusTemp(String nimi, String versio, String tuotekoodi) {
        this.nimi = nimi;
        this.versio = versio;
        this.tuotekoodi = tuotekoodi;
    }

    public SovellusTemp() {
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getValmistaja() {
        return valmistaja;
    }

    public void setValmistaja(String valmistaja) {
        this.valmistaja = valmistaja;
    }

    public String getAliasNimet() {
        return aliasNimet;
    }

    public void setAliasNimet(String aliasNimet) {
        this.aliasNimet = aliasNimet;
    }

    public String getVersio() {
        return versio;
    }

    public void setVersio(String versio) {
        this.versio = versio;
    }

    public String getTuotekoodi() {
        return tuotekoodi;
    }

    public void setTuotekoodi(String tuotekoodi) {
        this.tuotekoodi = tuotekoodi;
    }

    public String getKonfiguraatioVersio() {
        return konfiguraatioVersio;
    }

    public void setKonfiguraatioVersio(String konfiguraatioVersio) {
        this.konfiguraatioVersio = konfiguraatioVersio;
    }

    public String getLisatietoja() {
        return lisatietoja;
    }

    public void setLisatietoja(String lisatietoja) {
        this.lisatietoja = lisatietoja;
    }

    public String getSovellusTyyppi() {
        return sovellusTyyppi;
    }

    public void setSovellusTyyppi(String sovellusTyyppi) {
        this.sovellusTyyppi = sovellusTyyppi;
    }

    public String getKielisyys() {
        return kielisyys;
    }

    public void setKielisyys(String kielisyys) {
        this.kielisyys = kielisyys;
    }

    public String getKayttojarjestelmaVaatimus() {
        return kayttojarjestelmaVaatimus;
    }

    public void setKayttojarjestelmaVaatimus(String kayttojarjestelmaVaatimus) {
        this.kayttojarjestelmaVaatimus = kayttojarjestelmaVaatimus;
    }

    public String getArkkitehtuuri() {
        return arkkitehtuuri;
    }

    public void setArkkitehtuuri(String arkkitehtuuri) {
        this.arkkitehtuuri = arkkitehtuuri;
    }

    public String getAlusta() {
        return alusta;
    }

    public void setAlusta(String alusta) {
        this.alusta = alusta;
    }

    public String getRiippuvuustieto() {
        return riippuvuustieto;
    }

    public void setRiippuvuustieto(String riippuvuustieto) {
        this.riippuvuustieto = riippuvuustieto;
    }

    public String getLiittymatJarjestelmiin() {
        return liittymatJarjestelmiin;
    }

    public void setLiittymatJarjestelmiin(String liittymatJarjestelmiin) {
        this.liittymatJarjestelmiin = liittymatJarjestelmiin;
    }

    public Date getTuotantoonHyvaksymisPaiva() {
        return tuotantoonHyvaksymisPaiva;
    }

    public void setTuotantoonHyvaksymisPaiva(Date tuotantoonHyvaksymisPaiva) {
        this.tuotantoonHyvaksymisPaiva = tuotantoonHyvaksymisPaiva;
    }

    public String getKriittisyys() {
        return kriittisyys;
    }

    public void setKriittisyys(String kriittisyys) {
        this.kriittisyys = kriittisyys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SovellusTemp that = (SovellusTemp) o;

        if (!nimi.equals(that.nimi)) return false;
        if (valmistaja != null ? !valmistaja.equals(that.valmistaja) : that.valmistaja != null) return false;
        if (aliasNimet != null ? !aliasNimet.equals(that.aliasNimet) : that.aliasNimet != null) return false;
        if (versio != null ? !versio.equals(that.versio) : that.versio != null) return false;
        if (tuotekoodi != null ? !tuotekoodi.equals(that.tuotekoodi) : that.tuotekoodi != null) return false;
        if (konfiguraatioVersio != null ? !konfiguraatioVersio.equals(that.konfiguraatioVersio) : that.konfiguraatioVersio != null)
            return false;
        if (lisatietoja != null ? !lisatietoja.equals(that.lisatietoja) : that.lisatietoja != null) return false;
        if (sovellusTyyppi != null ? !sovellusTyyppi.equals(that.sovellusTyyppi) : that.sovellusTyyppi != null)
            return false;
        if (kielisyys != null ? !kielisyys.equals(that.kielisyys) : that.kielisyys != null) return false;
        if (kayttojarjestelmaVaatimus != null ? !kayttojarjestelmaVaatimus.equals(that.kayttojarjestelmaVaatimus) : that.kayttojarjestelmaVaatimus != null)
            return false;
        if (arkkitehtuuri != null ? !arkkitehtuuri.equals(that.arkkitehtuuri) : that.arkkitehtuuri != null)
            return false;
        if (alusta != null ? !alusta.equals(that.alusta) : that.alusta != null) return false;
        if (riippuvuustieto != null ? !riippuvuustieto.equals(that.riippuvuustieto) : that.riippuvuustieto != null)
            return false;
        if (liittymatJarjestelmiin != null ? !liittymatJarjestelmiin.equals(that.liittymatJarjestelmiin) : that.liittymatJarjestelmiin != null)
            return false;
        if (tuotantoonHyvaksymisPaiva != null ? !tuotantoonHyvaksymisPaiva.equals(that.tuotantoonHyvaksymisPaiva) : that.tuotantoonHyvaksymisPaiva != null)
            return false;
        return kriittisyys != null ? kriittisyys.equals(that.kriittisyys) : that.kriittisyys == null;
    }

    @Override
    public int hashCode() {
        int result = nimi.hashCode();
        result = 31 * result + (valmistaja != null ? valmistaja.hashCode() : 0);
        result = 31 * result + (aliasNimet != null ? aliasNimet.hashCode() : 0);
        result = 31 * result + (versio != null ? versio.hashCode() : 0);
        result = 31 * result + (tuotekoodi != null ? tuotekoodi.hashCode() : 0);
        result = 31 * result + (konfiguraatioVersio != null ? konfiguraatioVersio.hashCode() : 0);
        result = 31 * result + (lisatietoja != null ? lisatietoja.hashCode() : 0);
        result = 31 * result + (sovellusTyyppi != null ? sovellusTyyppi.hashCode() : 0);
        result = 31 * result + (kielisyys != null ? kielisyys.hashCode() : 0);
        result = 31 * result + (kayttojarjestelmaVaatimus != null ? kayttojarjestelmaVaatimus.hashCode() : 0);
        result = 31 * result + (arkkitehtuuri != null ? arkkitehtuuri.hashCode() : 0);
        result = 31 * result + (alusta != null ? alusta.hashCode() : 0);
        result = 31 * result + (riippuvuustieto != null ? riippuvuustieto.hashCode() : 0);
        result = 31 * result + (liittymatJarjestelmiin != null ? liittymatJarjestelmiin.hashCode() : 0);
        result = 31 * result + (tuotantoonHyvaksymisPaiva != null ? tuotantoonHyvaksymisPaiva.hashCode() : 0);
        result = 31 * result + (kriittisyys != null ? kriittisyys.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SovellusTemp{" +
                "nimi='" + nimi + '\'' +
                ", valmistaja='" + valmistaja + '\'' +
                ", aliasNimet='" + aliasNimet + '\'' +
                ", versio='" + versio + '\'' +
                ", tuotekoodi='" + tuotekoodi + '\'' +
                ", konfiguraatioVersio='" + konfiguraatioVersio + '\'' +
                ", lisatietoja='" + lisatietoja + '\'' +
                ", sovellusTyyppi='" + sovellusTyyppi + '\'' +
                ", kielisyys='" + kielisyys + '\'' +
                ", kayttojarjestelmaVaatimus='" + kayttojarjestelmaVaatimus + '\'' +
                ", arkkitehtuuri='" + arkkitehtuuri + '\'' +
                ", alusta='" + alusta + '\'' +
                ", riippuvuustieto='" + riippuvuustieto + '\'' +
                ", liittymatJarjestelmiin='" + liittymatJarjestelmiin + '\'' +
                ", tuotantoonHyvaksymisPaiva=" + tuotantoonHyvaksymisPaiva +
                ", kriittisyys='" + kriittisyys + '\'' +
                '}';
    }

    @Entity
    @Table(name = "TEST_TABLE")
    public static class TestObj implements Serializable, Cloneable {
        private static final long serialVersionUID = 1L;

        @Id
        @Column(name = "ID", unique = true, nullable = false)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
        @SequenceGenerator(name = "seq_gen", sequenceName = "seq_TEST_TABLE")
        private Integer id;

        @Column(name = "OBJECT_ID")
        private String objectID;

        @Column(name = "NAME")
        private String name;

        @Column(name = "POISTUNUT")
        private Integer inactive;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setInactive(Integer inactive) {
            this.inactive = inactive;
        }

        public Integer getInactive() {
            return inactive;
        }

        public String getObjectID() {
            return objectID;
        }

        public void setObjectID(String objectID) {
            this.objectID = objectID;
        }

        @Override
        public String toString() {
            return "TestObj{" +
                    "id=" + id +
                    ", objectID='" + objectID + '\'' +
                    ", name='" + name + '\'' +
                    ", inactive=" + inactive +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestObj testObj = (TestObj) o;

            if (id != null ? !id.equals(testObj.id) : testObj.id != null) return false;
            if (objectID != null ? !objectID.equals(testObj.objectID) : testObj.objectID != null) return false;
            if (name != null ? !name.equals(testObj.name) : testObj.name != null) return false;
            return inactive != null ? inactive.equals(testObj.inactive) : testObj.inactive == null;
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (objectID != null ? objectID.hashCode() : 0);
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (inactive != null ? inactive.hashCode() : 0);
            return result;
        }
    }

    @Entity
    @Table(name = "TEST_TABLE_TEMP")
    public static class TestObjTemp implements Serializable, Cloneable {
        @Id
        @Column(name = "ID", unique = true, nullable = false)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
        @SequenceGenerator(name = "seq_gen", sequenceName = "seq_TEST_TABLE_TEMP")
        private Integer id;

        @Column(name = "OBJECT_ID")
        private String objectID;

        @Column(name = "NAME")
        private String name;

        @Column(name = "POISTUNUT")
        private Integer inactive;


        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getInactive() {
            return inactive;
        }

        public String getObjectID() {
            return objectID;
        }

        public void setObjectID(String objectID) {
            this.objectID = objectID;
        }

        @Override
        public String toString() {
            return "TestObjTemp{" +
                    "id=" + id +
                    ", objectID='" + objectID + '\'' +
                    ", name='" + name + '\'' +
                    ", inactive=" + inactive +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestObjTemp testObj = (TestObjTemp) o;

            if (id != null ? !id.equals(testObj.id) : testObj.id != null) return false;
            if (objectID != null ? !objectID.equals(testObj.objectID) : testObj.objectID != null) return false;
            if (name != null ? !name.equals(testObj.name) : testObj.name != null) return false;
            return inactive != null ? inactive.equals(testObj.inactive) : testObj.inactive == null;
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (objectID != null ? objectID.hashCode() : 0);
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (inactive != null ? inactive.hashCode() : 0);
            return result;
        }
    }
}
