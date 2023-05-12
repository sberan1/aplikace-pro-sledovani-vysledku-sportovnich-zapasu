package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class User {
    @Id @GeneratedValue private Long id;
    private String name;
    private String surname;
    private String password;
    @Column(unique = true)
    private String email;
    @ManyToMany
    @ToString.Exclude
    private List<Team> favouriteTeams;
    @ManyToMany
    @ToString.Exclude
    private List<Fixture> favouriteFixtures;
}
