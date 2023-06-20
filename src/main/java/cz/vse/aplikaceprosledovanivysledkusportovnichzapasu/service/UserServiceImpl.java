package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Fixture;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.ChangePasswordDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Fixture;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.League;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Team;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.FixtureRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private FixtureRepository fixtureRepository;


    @Override
    public User saveUser(User user) {
        if(user.getEmail() == null || user.getPassword() == null || user.getName() == null || user.getSurname() == null) {
            return null;
        }
        for (User u : userRepository.findAll()) {
            if (u.getEmail().equals(user.getEmail())) {
                return null;
            }
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public String getTextOfFavTeams(User user) {
        StringBuilder sb = new StringBuilder();
        for (Team f : user.getFavouriteTeams()) {
            sb.append(f.getName());
            sb.append(", ");
        }
        return sb.toString();
    }

    @Override
    public User getUserFromToken(String jwt){
        String email = jwtService.extractEmail(jwt);
        return userRepository.findByEmail(email).get();
    }

    @Override
    public void addFavouriteTeam(long teamId, String jwt) {
        User user= getUserFromToken(jwt);
        user.getFavouriteTeams().add(teamRepository.findById(teamId).get());
    }

    @Override
    public void removeFavouriteTeam(long teamId, String jwt) {
        User user= getUserFromToken(jwt);
        user.getFavouriteTeams().remove(teamRepository.findById(teamId).get());
    }

    @Override
    public Set<Team> getFavouriteTeams(String jwt) {
        User user= getUserFromToken(jwt);
        return user.getFavouriteTeams();
    }

    @Override
    public Set<Fixture> getFavouriteFixtures(String jwt) {
        User user= getUserFromToken(jwt);
        return user.getFavouriteFixtures();
    }

    @Override
    public User deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
        return user;
    }

    @Override
    public void addFavouriteFixture(long id, String jwt) {
        User user= getUserFromToken(jwt);
        user.getFavouriteFixtures().add(fixtureRepository.findById(id).get());
    }

    @Override
    public void removeFavouriteFixture(long id, String jwt) {
        User user= getUserFromToken(jwt);
        user.getFavouriteFixtures().remove(fixtureRepository.findById(id));
    }



}
