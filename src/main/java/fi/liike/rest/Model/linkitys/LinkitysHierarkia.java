package fi.liike.rest.Model.linkitys;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Service.MolekyyliLinkkiService;

import java.util.Objects;

/**
 * Describes links between entities, e.g. jarjestelma, sovellus, looginen,...
 */
public class LinkitysHierarkia {
    private String lahdeNimi;
    private String kohdeNimi;
    private String lahdeTunnus;
    private String kohdeTunnus;
    private String lahdeTaulu;
    private String kohdeTaulu;


    public void setUp(Haettava from, Haettava to) {
        this.lahdeNimi = from.getNimi();
        this.kohdeNimi = to.getNimi();
        this.lahdeTunnus = from.getTunnus().toString();
        this.kohdeTunnus = to.getTunnus().toString();
    }

    public void setUp(MolekyyliLinkkiService.IdNamePair from, MolekyyliLinkkiService.IdNamePair to) {
        this.lahdeNimi = from.getNimi();
        this.kohdeNimi = to.getNimi();
        this.lahdeTunnus = from.getTunnus().toString();
        this.kohdeTunnus = to.getTunnus().toString();
    }

    public void setLahdeNimi(String lahdeNimi) {
        this.lahdeNimi = lahdeNimi;
    }

    public void setKohdeNimi(String kohdeNimi) {
        this.kohdeNimi = kohdeNimi;
    }

    public String getLahdeNimi() {
        return lahdeNimi;
    }

    public String getKohdeNimi() {
        return kohdeNimi;
    }

    public void setLahdeTunnus(String lahdeTunnus) {
        this.lahdeTunnus = lahdeTunnus;
    }

    public void setKohdeTunnus(String kohdeTunnus) {
        this.kohdeTunnus = kohdeTunnus;
    }

    public String getLahdeTunnus() {
        return lahdeTunnus;
    }

    public String getKohdeTunnus() {
        return kohdeTunnus;
    }

    public void setLahdeTaulu(String lahdeTaulu) {
        this.lahdeTaulu = lahdeTaulu;
    }

    public void setKohdeTaulu(String kohdeTaulu) {
        this.kohdeTaulu = kohdeTaulu;
    }

    public String getLahdeTaulu() {
        return lahdeTaulu;
    }

    public String getKohdeTaulu() {
        return kohdeTaulu;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LinkitysHierarkia)) return false;

        LinkitysHierarkia other = (LinkitysHierarkia) obj;
        return Objects.equals(this.lahdeNimi, other.lahdeNimi) &&
                Objects.equals(this.kohdeNimi, other.kohdeNimi) &&
                Objects.equals(this.lahdeTunnus, other.lahdeTunnus) &&
                Objects.equals(this.kohdeTunnus, other.kohdeTunnus) &&
                Objects.equals(this.lahdeTaulu, other.lahdeTaulu) &&
                Objects.equals(this.kohdeTaulu, other.kohdeTaulu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getKohdeNimi(), this.getKohdeTaulu(), this.getKohdeTunnus(),
                            this.getLahdeNimi(), this.getLahdeTaulu(), this.getLahdeTunnus());
    }
}
