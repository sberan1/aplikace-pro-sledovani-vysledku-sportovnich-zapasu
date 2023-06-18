package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserFromToken(String jwt);
 

    void addFavouriteTeam(long teamId, String jwt);


 
    User deleteUser(Long id);
 
}
