package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
