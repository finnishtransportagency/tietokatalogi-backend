package fi.liike.rest.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static java.lang.String.format;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LooginenPaaTietoryhmaTietolaji {
	public LooginenTietovarantoDto looginen;
    public List<PaatietoTietoryhmaTietolaji> paatietoTietoryhmaTietolajiList;

    public LooginenPaaTietoryhmaTietolaji() {}

    public LooginenPaaTietoryhmaTietolaji(LooginenTietovarantoDto looginen, List<PaatietoTietoryhmaTietolaji> paatietoTietoryhmaTietolajiList) {
        this.looginen = looginen;
    	this.paatietoTietoryhmaTietolajiList = paatietoTietoryhmaTietolajiList;
    }
    
    public LooginenTietovarantoDto getLooginen() {
        return looginen;
    }

    public void setLooginen(LooginenTietovarantoDto looginen) {
        this.looginen = looginen;
    }

    public List<PaatietoTietoryhmaTietolaji> getPaatietoTietoryhmaTietolajiList() {
        return paatietoTietoryhmaTietolajiList;
    }

    public void setPaatietoTietoryhmaTietolajiList(List<PaatietoTietoryhmaTietolaji> paatietoTietoryhmaTietolajiList) {
        this.paatietoTietoryhmaTietolajiList = paatietoTietoryhmaTietolajiList;
    }


    @Override
    public String toString() {
        return format("{looginen=%s, tietolajiList=%s}", looginen, paatietoTietoryhmaTietolajiList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LooginenPaaTietoryhmaTietolaji that = (LooginenPaaTietoryhmaTietolaji) o;

        if (looginen != null ? !looginen.equals(that.looginen) : that.looginen != null) return false;
        return paatietoTietoryhmaTietolajiList != null ? paatietoTietoryhmaTietolajiList.equals(that.paatietoTietoryhmaTietolajiList) : that.paatietoTietoryhmaTietolajiList == null;
    }

    @Override
    public int hashCode() {
        int result = looginen != null ? looginen.hashCode() : 0;
        result = 31 * result + (paatietoTietoryhmaTietolajiList != null ? paatietoTietoryhmaTietolajiList.hashCode() : 0);
        return result;
    }
}
