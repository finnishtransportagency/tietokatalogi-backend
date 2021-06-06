package fi.liike.rest.api.dto;

public class TermilomakeHierarkkinenKasiteDto {

    private Integer id;
    private Integer hierarkkinenYlakasite;
    private Integer hierarkkinenAlakasite;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHierarkkinenYlakasite() {
        return hierarkkinenYlakasite;
    }

    public void setHierarkkinenYlakasite(Integer hierarkkinenYlakasite) {
        this.hierarkkinenYlakasite = hierarkkinenYlakasite;
    }

    public Integer getHierarkkinenAlakasite() {
        return hierarkkinenAlakasite;
    }

    public void setHierarkkinenAlakasite(Integer hierarkkinenAlakasite) {
        this.hierarkkinenAlakasite = hierarkkinenAlakasite;
    }
}
