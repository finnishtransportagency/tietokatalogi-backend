package fi.liike.rest.api.dto;

import static java.lang.String.format;

public class JarjestelmaLinkkausDto {
    private Integer id;
    private Integer tietojarjestelmaTunnus;
    private Integer linkattavaTunnus;
    private String suunta;
    private Integer tietojarjestelmapalveluTunnus;
    private String tietojarjestelmapalveluNimi;
    private String tietovirta;
    private String tietovirtaNimi;
    private String tyyppi;
    private String elinkaaritila;

    public void setTietojarjestelmaTunnus(Integer tietojarjestelmaTunnus) {
        this.tietojarjestelmaTunnus = tietojarjestelmaTunnus;
    }

    public void setLinkattavaTunnus(Integer linkattavaTunnus) {
        this.linkattavaTunnus = linkattavaTunnus;
    }

    public void setSuunta(String suunta) {
        this.suunta = suunta;
    }

    public void setTietojarjestelmapalveluTunnus(Integer tietojarjestelmapalveluTunnus) {
        this.tietojarjestelmapalveluTunnus = tietojarjestelmapalveluTunnus;
    }

    public void setTietovirta(String kuvaus) {
        this.tietovirta = kuvaus;
    }

    public void setTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    public Integer getTietojarjestelmaTunnus() {
        return tietojarjestelmaTunnus;
    }

    public Integer getLinkattavaTunnus() {
        return linkattavaTunnus;
    }

    public String getSuunta() {
        return suunta;
    }

    public Integer getTietojarjestelmapalveluTunnus() {
        return tietojarjestelmapalveluTunnus;
    }

    public String getTietovirta() {
        return tietovirta;
    }

    public String getTyyppi() {
        return tyyppi;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() { return id; }

    public String getTietojarjestelmapalveluNimi() {
        return tietojarjestelmapalveluNimi;
    }

    public void setTietojarjestelmapalveluNimi(String tietojarjestelmapalveluNimi) {
        this.tietojarjestelmapalveluNimi = tietojarjestelmapalveluNimi;
    }

    public String getTietovirtaNimi() {
        return tietovirtaNimi;
    }

    public void setTietovirtaNimi(String tietovirtaNimi) {
        this.tietovirtaNimi = tietovirtaNimi;
    }

    public String getElinkaaritila() {
        return elinkaaritila;
    }

    public void setElinkaaritila(String elinkaaritila) {
        this.elinkaaritila = elinkaaritila;
    }

    public String toString() {
        return format("JarjestelmaLinkkausDto [tietojarjestelmaTunnus=%s, linkattavaTunnus=%s, suunta=%s," +
                        "tietojarjestelmapalveluTunnus=%s, kuvaus=%s, tyyppi=%s, elinkaaritila=%s]",
                tietojarjestelmaTunnus, linkattavaTunnus, suunta, tietojarjestelmapalveluTunnus, tietovirta, tyyppi,
                elinkaaritila);
    }

    @Override
    public boolean equals(Object o) {
        return this.compare(o, false) || this.compare(o, true);
    }

    public boolean compare(Object o, Boolean isReversed) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JarjestelmaLinkkausDto that = (JarjestelmaLinkkausDto) o;
        if (!isReversed) {
            if (tietojarjestelmaTunnus != null ? !tietojarjestelmaTunnus.equals(that.tietojarjestelmaTunnus) : that.tietojarjestelmaTunnus != null)
                return false;
            if (linkattavaTunnus != null ? !linkattavaTunnus.equals(that.linkattavaTunnus) : that.linkattavaTunnus != null)
                return false;
            if (suunta != null ? !suunta.equals(that.suunta) : that.suunta != null) return false;
        }
        else {
            // If the link is considered from the point of view of a different jarjestelma, tietojarjestelmaTunnus and
            // linkattavaTunnus will be swapped and suunta will be reversed.
            if (tietojarjestelmaTunnus != null ? !tietojarjestelmaTunnus.equals(that.linkattavaTunnus) : that.linkattavaTunnus != null)
                return false;
            if (linkattavaTunnus != null ? !linkattavaTunnus.equals(that.tietojarjestelmaTunnus) : that.tietojarjestelmaTunnus != null)
                return false;
            if (suunta != null ? suunta.equals(that.suunta) : that.suunta != null)
                return false;
        }

        if (tietojarjestelmapalveluTunnus != null ? !tietojarjestelmapalveluTunnus.equals(that.tietojarjestelmapalveluTunnus) : that.tietojarjestelmapalveluTunnus != null) return false;
        if (tietovirta != null ? !tietovirta.equals(that.tietovirta) : that.tietovirta != null) return false;
        return tyyppi != null ? tyyppi.equals(that.tyyppi) : that.tyyppi == null;
    }

    @Override
    public int hashCode() {
        int result = tietojarjestelmaTunnus != null ? tietojarjestelmaTunnus.hashCode() : 0;
        result = 31 * result + (linkattavaTunnus != null ? linkattavaTunnus.hashCode() : 0);
        result = 31 * result + (suunta != null ? suunta.hashCode() : 0);
        result = 31 * result + (tietojarjestelmapalveluTunnus != null ? tietojarjestelmapalveluTunnus.hashCode() : 0);
        result = 31 * result + (tietovirta != null ? tietovirta.hashCode() : 0);
        result = 31 * result + (tyyppi != null ? tyyppi.hashCode() : 0);
        result = 31 * result + (elinkaaritila != null ? elinkaaritila.hashCode() : 0);
        return result;
    }
}
