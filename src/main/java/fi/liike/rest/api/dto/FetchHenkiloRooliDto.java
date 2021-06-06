package fi.liike.rest.api.dto;

public class FetchHenkiloRooliDto {

    private RooliDto rooli;
    private HenkiloDto henkilo;

    public FetchHenkiloRooliDto(RooliDto rooli, HenkiloDto henkilo) {
        this.rooli = rooli;
        this.henkilo = henkilo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FetchHenkiloRooliDto that = (FetchHenkiloRooliDto) o;

        if (!rooli.equals(that.rooli)) return false;
        return henkilo.equals(that.henkilo);
    }

    @Override
    public int hashCode() {
        int result = rooli.hashCode();
        result = 31 * result + henkilo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FetchHenkiloRooliDto{" +
                "rooli=" + rooli +
                ", henkilo=" + henkilo +
                '}';
    }

    public RooliDto getRooli() {
        return rooli;
    }

    public HenkiloDto getHenkilo() {
        return henkilo;
    }
}
