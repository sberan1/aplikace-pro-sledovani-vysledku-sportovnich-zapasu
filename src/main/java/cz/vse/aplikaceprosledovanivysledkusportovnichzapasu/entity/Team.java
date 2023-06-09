package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @ManyToOne
    private Country country;
    private String logo;
}
