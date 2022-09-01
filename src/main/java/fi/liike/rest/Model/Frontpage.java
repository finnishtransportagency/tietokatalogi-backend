package fi.liike.rest.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "etusivu")
public class Frontpage {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "paateksti")
    private String mainText;

    @Column(name = "sivuteksti")
    private String sideText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getSideText() {
        return sideText;
    }

    public void setSideText(String sideText) {
        this.sideText = sideText;
    }
}
