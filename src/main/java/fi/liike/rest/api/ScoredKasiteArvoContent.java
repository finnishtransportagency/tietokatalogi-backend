package fi.liike.rest.api;

public class ScoredKasiteArvoContent extends KasiteArvoContent {
    private Integer score;

    public ScoredKasiteArvoContent(Integer id, String resourceName, String value, Integer score) {
        super(id, resourceName, value);
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
