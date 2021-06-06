package fi.liike.rest.api.dto;

public class HenkiloRooliDto {
    private Integer rooliId;
    private Integer henkiloId;

    public HenkiloRooliDto () {}

    public HenkiloRooliDto (Integer rooliId, Integer henkiloId) {
        this.rooliId = rooliId;
        this.henkiloId = henkiloId;
    }

    public Integer getRooliId() {
        return rooliId;
    }

    public Integer getHenkiloId() {
        return henkiloId;
    }

    @Override
    public String toString() {
        return "HenkiloRooliDto{" +
                "rooliId=" + rooliId +
                ", henkiloId=" + henkiloId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HenkiloRooliDto that = (HenkiloRooliDto) o;

        if (rooliId != null ? !rooliId.equals(that.rooliId) : that.rooliId != null) return false;
        return henkiloId != null ? henkiloId.equals(that.henkiloId) : that.henkiloId == null;
    }

    @Override
    public int hashCode() {
        int result = rooliId != null ? rooliId.hashCode() : 0;
        result = 31 * result + (henkiloId != null ? henkiloId.hashCode() : 0);
        return result;
    }
}
