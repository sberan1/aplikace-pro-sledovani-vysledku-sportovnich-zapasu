package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Trieda HockejScore - táto entita reprezentuje tabuľku v databáze, ktorá uchováva informácie o lige.
 *
 * @author Štepán Beran, Adam Škarvada
 * @version LS 2022/2023
 */

@Entity @AllArgsConstructor
@NoArgsConstructor
@Data
public class League {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private String sport;
    private int externalId;
    private String logo;
    @ManyToOne
    private Country country;
}
