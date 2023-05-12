package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;


import jakarta.persistence.*;
import lombok.*;

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
