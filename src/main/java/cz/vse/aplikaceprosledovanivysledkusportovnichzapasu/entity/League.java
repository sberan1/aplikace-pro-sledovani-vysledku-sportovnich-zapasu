package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;


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
    @Lob
    private Blob flag;
}
