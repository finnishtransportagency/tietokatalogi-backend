package fi.liike.rest.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static java.lang.String.format;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaatietoTietoryhmaTietolaji {
    public TietolajiDto tietolaji;
    public TietoryhmaDto tietoryhma;
    public PaatietoryhmaDto paatieto;

    public PaatietoTietoryhmaTietolaji() {}

    public PaatietoTietoryhmaTietolaji(TietolajiDto tietolaji, TietoryhmaDto tietoryhma, PaatietoryhmaDto paatieto) {
        this.tietolaji =  tietolaji;
        this.tietoryhma = tietoryhma;
        this.paatieto = paatieto;
    }

    public TietolajiDto getTietolaji() {
        return tietolaji;
    }

    public void setTietolaji(TietolajiDto tietolaji) {
        this.tietolaji = tietolaji;
    }

    public TietoryhmaDto getTietoryhma() {
        return tietoryhma;
    }

    public void setTietoryhma(TietoryhmaDto tietoryhma) {
        this.tietoryhma = tietoryhma;
    }

    public PaatietoryhmaDto getPaatieto() {
        return paatieto;
    }

    public void setPaatieto(PaatietoryhmaDto paatieto) {
        this.paatieto = paatieto;
    }

    @Override
    public String toString() {
        return format("tietolaji=%s, tietoryhma=%s, paatieto=%s", tietolaji, tietoryhma, paatieto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaatietoTietoryhmaTietolaji that = (PaatietoTietoryhmaTietolaji) o;

        if (tietolaji != null ? !tietolaji.equals(that.tietolaji) : that.tietolaji != null) return false;
        if (tietoryhma != null ? !tietoryhma.equals(that.tietoryhma) : that.tietoryhma != null) return false;
        return paatieto != null ? paatieto.equals(that.paatieto) : that.paatieto == null;
    }

    @Override
    public int hashCode() {
        int result = tietolaji != null ? tietolaji.hashCode() : 0;
        result = 31 * result + (tietoryhma != null ? tietoryhma.hashCode() : 0);
        result = 31 * result + (paatieto != null ? paatieto.hashCode() : 0);
        return result;
    }
}
