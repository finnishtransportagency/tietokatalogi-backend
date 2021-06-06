package fi.liike.rest.api.dto;


public class RooliDto {
    private Integer tunnus;
    private String nimi;

    public RooliDto(Integer tunnus, String nimi) {
        this.tunnus = tunnus;
        this.nimi = nimi;
    }

    public Integer getTunnus() {
        return tunnus;
    }

    public String getNimi() {
        return nimi;
    }

    public void setTunnus(Integer tunnus) {
        this.tunnus = tunnus;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RooliDto rooliDto = (RooliDto) o;

        if (!tunnus.equals(rooliDto.tunnus)) return false;
        return nimi.equals(rooliDto.nimi);
    }

    @Override
    public int hashCode() {
        int result = tunnus.hashCode();
        result = 31 * result + nimi.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RooliDto{" +
                "tunnus=" + tunnus +
                ", nimi='" + nimi + '\'' +
                '}';
    }
}
