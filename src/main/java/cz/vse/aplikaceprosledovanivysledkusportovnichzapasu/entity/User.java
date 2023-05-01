package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id @GeneratedValue private Long id;
    private String name;
    private String surname;
    private String password;
    @Column(unique = true)
    private String email;
    @ManyToMany
    private List<Team> favouriteTeams;
    @ManyToMany
    private List<Match> favouriteMatches;
}
