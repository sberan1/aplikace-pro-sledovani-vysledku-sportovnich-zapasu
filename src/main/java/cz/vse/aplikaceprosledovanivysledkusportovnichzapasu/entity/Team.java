package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

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
    @Lob
    private Blob flag;
    private String country;
    @Lob
    private Blob logo;
}
