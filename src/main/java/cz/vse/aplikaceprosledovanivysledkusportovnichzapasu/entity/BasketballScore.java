package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity @AllArgsConstructor @NoArgsConstructor @Data
public class BasketballScore extends Score{
    private int firstQuarterAwayScore;
    private int firstQuarterHomeScore;
    private int secondQuarterAwayScore;
    private int secondQuarterHomeScore;
    private int thirdQuarterAwayScore;
    private int thirdQuarterHomeScore;
    private int fourthQuarterAwayScore;
    private int fourthQuarterHomeScore;
    private int overtimeAwayScore;
    private int overtimeHomeScore;
}
