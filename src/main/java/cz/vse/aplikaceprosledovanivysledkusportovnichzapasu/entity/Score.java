package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;


import jakarta.persistence.*;
import lombok.*;

/**
 * Trieda Score - táto entita reprezentuje tabuľku v databáze, ktorá uchováva informácie o skóre.
 *
 * @author Štěpán Beran, Zuzana Hadzimová, Sabína Hrabáriková, Julie Sanetrníková, Adam Škarvada
 * @version LS 2022/2023
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Score {
    @Id @GeneratedValue
    private Long id;
    private int finalAwayScore;
    private int finalHomeScore;

}
