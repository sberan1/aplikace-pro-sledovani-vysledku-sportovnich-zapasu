package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Trieda Team- táto entita reprezentuje tabuľku v databáze, ktorá uchováva informácie o týme.
 *
 * @author Štepán Beran, Zuzana Hadzimová, Adam Škarvada
 * @version LS 2022/2023
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Team {
    @Id @GeneratedValue
    private Long id;
    private String sport;
    private String name;
    private int externalId;
    @ManyToMany
    private Set<League> leagues = new HashSet<>();
    @ManyToOne
    private Country country;
    private String logo;
}
