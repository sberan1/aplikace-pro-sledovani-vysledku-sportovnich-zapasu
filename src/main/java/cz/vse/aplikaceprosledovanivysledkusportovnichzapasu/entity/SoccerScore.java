package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
