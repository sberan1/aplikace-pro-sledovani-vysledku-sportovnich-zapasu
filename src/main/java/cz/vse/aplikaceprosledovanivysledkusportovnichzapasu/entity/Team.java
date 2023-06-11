package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Team {
    @Id @GeneratedValue
    private Long id;
    private String sport;
    private String name;
    private int externalId;
    @ManyToMany
    private List<League> leagues = new ArrayList<>();
    @ManyToOne
    private Country country;
    private String logo;
}
