package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class SoccerScore extends Score{
    private int firstHalfAwayScore;
    private int firstHalfHomeScore;
    private int secondHalfAwayScore;
    private int secondHalfHomeScore;
    private int overtimeAwayScore;
    private int overtimeHomeScore;
    private int penaltyAwayScore;
    private int penaltyHomeScore;
}
