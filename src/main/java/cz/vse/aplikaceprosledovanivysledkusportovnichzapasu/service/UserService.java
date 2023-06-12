package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.LoginRespDTO;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.UserLoginDTO;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    LoginRespDTO loginUser(UserLoginDTO user);
}
