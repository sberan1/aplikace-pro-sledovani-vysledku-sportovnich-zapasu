package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
