package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Fixture;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.League;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Team;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    boolean emailExists(String email);
    String getTextOfFavTeams(User user);
    User getUserFromToken(String jwt);
    void addFavouriteTeam(long teamId, String jwt);
    void removeFavouriteTeam(long teamId, String jwt);
    Set<Team> getFavouriteTeams(String jwt);
    Set<Fixture> getFavouriteFixtures(String jwt);
    User deleteUser(Long id);
    void addFavouriteFixture(long id, String jwt);
    void removeFavouriteFixture(long id, String jwt);



}
