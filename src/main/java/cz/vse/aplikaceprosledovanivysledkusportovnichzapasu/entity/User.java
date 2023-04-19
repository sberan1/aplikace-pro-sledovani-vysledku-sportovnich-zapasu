package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class User {
    @Id @GeneratedValue private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    @OneToMany
    private List<Team> favouriteTeams;
    @OneToMany
    private List<Match> favouriteMathches;
}
