package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
