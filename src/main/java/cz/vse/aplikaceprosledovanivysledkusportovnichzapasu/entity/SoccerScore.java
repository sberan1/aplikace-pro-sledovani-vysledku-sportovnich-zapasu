package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.Entity;
import lombok.*;

/**
 * Trieda SoccerScore - táto entita reprezentuje tabuľku v databáze, ktorá uchováva skóre pri futbale.
 *
 * @author Štěpán Beran, Zuzana Hadzimová, Sabína Hrabáriková, Julie Sanetrníková, Adam Škarvada
 * @version LS 2022/2023
 */

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
