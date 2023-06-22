package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Trieda Country - táto entita reprezentuje tabuľku v databáze, ktorá uchováva informácie o krajine .
 *
 * @author Štepán Beran
 * @version LS 2022/2023
 */

@Entity @Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private int externalId;
    private String flag;
    private String sport;
}
