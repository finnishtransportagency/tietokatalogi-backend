package fi.liike.rest.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "SOVELLUS_TUONTI")
public class SovellusImport implements Serializable {
    private static final String seqName = "SOVELLUS_TUONTI_ID_SEQ";
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TUONTI_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = seqName)
    private Integer tuontiId;

    @Column(name = "TUONTIAIKA")
    private Timestamp tuontiaika;

    @Column(name = "ONNISTUNUT")
    private Integer onnistunut;

    public Integer getTuontiId() {
        return tuontiId;
    }

    public void setTuontiId(Integer tuontiId) {
        this.tuontiId = tuontiId;
    }

    public Timestamp getTuontiaika() {
        return tuontiaika;
    }

    public void setTuontiaika(Timestamp tuontiaika) {
        this.tuontiaika = tuontiaika;
    }

    public Integer getOnnistunut() {
        return onnistunut;
    }

    public void setOnnistunut(Integer onnistunut) {
        this.onnistunut = onnistunut;
    }

}