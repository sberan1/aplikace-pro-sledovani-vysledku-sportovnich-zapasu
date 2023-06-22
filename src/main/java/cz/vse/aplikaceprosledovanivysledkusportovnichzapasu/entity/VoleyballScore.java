package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Trieda VoleyballScore - táto entita reprezentuje tabuľku v databáze, ktorá uchováva skóre pri volejbale.
 *
 * @author Štěpán Beran, Zuzana Hadzimová, Sabína Hrabáriková, Julie Sanetrníková, Adam Škarvada
 * @version LS 2022/2023
 */

@EqualsAndHashCode(callSuper = true)
@Entity @Data
@AllArgsConstructor
@NoArgsConstructor
public class VoleyballScore extends Score{
    private int firstSetAwayScore;
    private int firstSetHomeScore;
    private int secondSetAwayScore;
    private int secondSetHomeScore;
    private int thirdSetAwayScore;
    private int thirdSetHomeScore;
    private int fourthSetAwayScore;
    private int fourthSetHomeScore;
    private int fifthSetAwayScore;
    private int fifthSetHomeScore;
}
