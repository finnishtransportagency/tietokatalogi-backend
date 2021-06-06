package fi.liike.rest.Model;

import fi.liike.rest.api.HistoryType;
import org.hibernate.mapping.Join;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "TERMILOMAKEKOOSTKASIHISTORIA")
public class TermilomakeJoinKoostumussuhteinenKasiteHistory implements JoinHistory, java.io.Serializable {

    private static final long serialVersionUID = 1L;
    public static final String seq_name = "TERMILOMAKEKOOSTKASHIST_ID_SEQ";


    @Id
    @Column(name = "rivi_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = seq_name)
    private Long rivi_id;

    @Column(name = "koostumussuhteinen_ylakasite")
    private Integer parentNode;

    @Column(name = "koostumussuhteinen_alakasite")
    private Integer childNode;


    @Column(name = "HISTORIATYYPPI")
    @Enumerated(EnumType.STRING)
    private HistoryType historiatyyppi;

    @Column(name = "riviluotupvm")
    private Timestamp riviluotupvm;

    @Column(name = "rivimuokkaajatunnus")
    private String rivimuokkaajatunnus;

    @Override
    public Integer getChildNode() {
        return this.childNode;
    }

    @Override
    public void setChildNode(Integer childId) {
        this.childNode = childId;
    }

    @Override
    public Integer getParentNode() {
        return this.parentNode;
    }

    @Override
    public void setParentNode(Integer parentId) {
        this.parentNode = parentId;
    }

    @Override
    public HistoryType getHistoriatyyppi() {
        return this.historiatyyppi;
    }

    @Override
    public void setHistoriatyyppi(HistoryType historiatyyppi) {
        this.historiatyyppi = historiatyyppi;
    }

    @Override
    public Timestamp getRiviluotupvm() {
        return this.riviluotupvm;
    }

    @Override
    public void setRiviluotupvm(Timestamp created) {
        this.riviluotupvm = created;
    }

    @Override
    public String getRivimuokkaajatunnus() {
        return this.rivimuokkaajatunnus;
    }

    @Override
    public void setRivimuokkaajatunnus(String editor) {
        this.rivimuokkaajatunnus = editor;
    }

    @Override
    public Long getRivitunnus() {
        return this.rivi_id;
    }
}
