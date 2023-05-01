package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity @AllArgsConstructor
@NoArgsConstructor
@Data
public class League {
    @Id @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private String type;
    private String season;
    private int externalId;
}
