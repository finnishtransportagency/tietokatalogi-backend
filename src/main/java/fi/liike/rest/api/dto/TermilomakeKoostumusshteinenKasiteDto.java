package fi.liike.rest.api.dto;

public class TermilomakeKoostumusshteinenKasiteDto {

    private Integer id;
    private Integer koostumusSuhdeYlakasite;
    private Integer koostumusSuhdeAlakasite;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHierarkkinenYlakasite() {
        return koostumusSuhdeYlakasite;
    }

    public void setHierarkkinenYlakasite(Integer koostumusSuhdeYlakasite) {
        this.koostumusSuhdeYlakasite = koostumusSuhdeYlakasite;
    }

    public Integer getHierarkkinenAlakasite() {
        return koostumusSuhdeAlakasite;
    }

    public void setHierarkkinenAlakasite(Integer koostumusSuhdeAlakasite) {
        this.koostumusSuhdeAlakasite = koostumusSuhdeAlakasite;
    }
}
