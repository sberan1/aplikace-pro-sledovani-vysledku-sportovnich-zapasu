package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<Team> favouriteTeams = new HashSet<>();
    @ManyToMany
    @ToString.Exclude
    private Set<Fixture> favouriteFixtures = new HashSet<>();
}
