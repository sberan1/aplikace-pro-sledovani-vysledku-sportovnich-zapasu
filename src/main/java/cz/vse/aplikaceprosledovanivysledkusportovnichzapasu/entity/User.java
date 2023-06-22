package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Trieda User - táto entita reprezentuje tabuľku v databáze, ktorá uchováva informácie o používateľovi.
 *
 * @author Štěpán Beran, Zuzana Hadzimová, Sabína Hrabáriková, Julie Sanetrníková, Adam Škarvada
 * @version LS 2022/2023
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class User implements UserDetails{
    @Id @GeneratedValue private Long id;
    private String name;
    private String surname;
    private String password;
    @Column(unique = true)
    private String email;
    @ManyToMany
    private Set<Team> favouriteTeams = new HashSet<>();
    @ManyToMany
    private Set<Fixture> favouriteFixtures = new HashSet<>();
    @Enumerated
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
