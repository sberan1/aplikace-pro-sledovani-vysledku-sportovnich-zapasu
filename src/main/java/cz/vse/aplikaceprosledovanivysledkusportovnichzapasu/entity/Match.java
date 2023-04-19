package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Match {
    @Id @GeneratedValue
    private Long id;
    @OneToOne
    private Team homeTeam;
    @OneToOne
    private Team awayTeam;
}

