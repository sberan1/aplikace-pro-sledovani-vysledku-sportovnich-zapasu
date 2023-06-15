package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.time.LocalDateTime;

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

