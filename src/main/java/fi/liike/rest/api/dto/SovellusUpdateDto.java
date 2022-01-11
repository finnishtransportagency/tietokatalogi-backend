package fi.liike.rest.api.dto;

import fi.liike.rest.api.ContentDto;

import java.util.List;

public class SovellusUpdateDto extends ContentDto {
    private Integer tunnus;
    private String elinkaaritieto;
    private List<HenkiloRooliDto> henkiloRooliList;
    private Boolean poistunut;
    private String rivimuokkaajatunnus;

    public SovellusUpdateDto() {}

    public SovellusUpdateDto(Integer tunnus, String elinkaaritieto, List<HenkiloRooliDto> henkiloRooliList,
                             Boolean poistunut) {
        this.tunnus = tunnus;
        this.elinkaaritieto = elinkaaritieto;
        this.henkiloRooliList = henkiloRooliList;
        this.poistunut = poistunut;
    }

    public Integer getTunnus() {
        return tunnus;
    }

    @Override
    public String getNimi() {
        return null;
    }

    @Override
    public void setNimi(String name) {

    }

    public void setTunnus(Integer tunnus) {
        this.tunnus = tunnus;
    }

    public String getElinkaaritieto() {
        return elinkaaritieto;
    }

    public void setElinkaaritieto(String elinkaaritieto) {
        this.elinkaaritieto = elinkaaritieto;
    }

    public List<HenkiloRooliDto> getHenkiloRooliList() {
        return henkiloRooliList;
    }

    public void setHenkiloRooliList(List<HenkiloRooliDto> henkiloRooliList) {
        this.henkiloRooliList = henkiloRooliList;
    }

    public String getRivimuokkaajatunnus() {
        return rivimuokkaajatunnus;
    }

    public void setRivimuokkaajatunnus(String rivimuokkaajatunnus) {
        this.rivimuokkaajatunnus = rivimuokkaajatunnus;
    }

    public Boolean getPoistunut() {
        return poistunut;
    }

    public void setPoistunut(Boolean poistunut) {
        this.poistunut = poistunut;
    }

    @Override
    public String toString() {
        return "SovellusUpdateDto{" +
                "tunnus=" + tunnus +
                ", elinkaaritieto='" + elinkaaritieto + '\'' +
                ", henkiloRooliList=" + henkiloRooliList +
                ", poistunut=" + poistunut +
                ", rivimuokkaajatunnus='" + rivimuokkaajatunnus + '\'' +
                '}';
    }
}
