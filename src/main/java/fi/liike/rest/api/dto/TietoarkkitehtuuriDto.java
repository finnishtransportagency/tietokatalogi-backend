package fi.liike.rest.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import static java.lang.String.format;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TietoarkkitehtuuriDto {

    private FyysinenTietovarantoDto fyysinen;
    private List<LooginenPaaTietoryhmaTietolaji> looginenPaaTietoryhmaTietolajiList;

    public TietoarkkitehtuuriDto() {
    }

    public TietoarkkitehtuuriDto(FyysinenTietovarantoDto fyysinen,
                                 List<LooginenPaaTietoryhmaTietolaji> looginenPaaTietoryhmaTietolajiList) {
        this.fyysinen = fyysinen;
        this.looginenPaaTietoryhmaTietolajiList = looginenPaaTietoryhmaTietolajiList;
    }

    public FyysinenTietovarantoDto getFyysinen() {
        return fyysinen;
    }

    public List<LooginenPaaTietoryhmaTietolaji> getLooginenPaaTietoryhmaTietolajiList() {
        return looginenPaaTietoryhmaTietolajiList;
    }

    public void setFyysinen(FyysinenTietovarantoDto fyysinen) {
        this.fyysinen = fyysinen;
    }

    public void setLooginenPaaTietoryhmaTietolajiList(List<LooginenPaaTietoryhmaTietolaji> looginenPaaTietoryhmaTietolajiList) {
        this.looginenPaaTietoryhmaTietolajiList = looginenPaaTietoryhmaTietolajiList;
    }

    @Override
    public String toString() {
        return format("TietoarkkitehtuuriDto{fyysinen=%s, LooginenList=%s}", fyysinen, looginenPaaTietoryhmaTietolajiList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TietoarkkitehtuuriDto that = (TietoarkkitehtuuriDto) o;

        if (fyysinen != null ? !fyysinen.equals(that.fyysinen) : that.fyysinen != null) return false;
        return looginenPaaTietoryhmaTietolajiList != null ? looginenPaaTietoryhmaTietolajiList.equals(that.looginenPaaTietoryhmaTietolajiList) : that.looginenPaaTietoryhmaTietolajiList == null;
    }

    @Override
    public int hashCode() {
        int result = fyysinen != null ? fyysinen.hashCode() : 0;
        result = 31 * result + (looginenPaaTietoryhmaTietolajiList != null ? looginenPaaTietoryhmaTietolajiList.hashCode() : 0);
        return result;
    }
}
