package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HockeyScore extends Score{
    private int firstPeriodAwayScore;
    private int firstPeriodHomeScore;
    private int secondPeriodAwayScore;
    private int secondPeriodHomeScore;
    private int thirdPeriodAwayScore;
    private int thirdPeriodHomeScore;
    private int overtimeAwayScore;
    private int overtimeHomeScore;
    private int shootoutAwayScore;
    private int shootoutHomeScore;
}