package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.time.LocalDateTime;

/**
 * Trieda Fixture - táto entita reprezentuje tabuľku v databáze, ktorá uchováva informácie o zápase .
 *
 * @author Štěpán Beran, Zuzana Hadzimová, Sabína Hrabáriková, Julie Sanetrníková, Adam Škarvada
 * @version LS 2022/2023
 */

@Data @AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fixture {
    private @Id @GeneratedValue Long id;
    private String sport;
    private int externalId;
    @ManyToOne
    private Team homeTeam;
    @ManyToOne
    private Team awayTeam;
    private LocalDateTime date;
    @ManyToOne
    private Country country;
    @ManyToOne
    private League league;
    @OneToOne
    private Score score;
}

