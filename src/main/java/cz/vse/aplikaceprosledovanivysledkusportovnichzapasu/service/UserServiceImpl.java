package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.LoginRespDTO;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.UserLoginDTO;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


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
    public LoginRespDTO loginUser(UserLoginDTO user) {
        LoginRespDTO loginRespDTO = new LoginRespDTO();
        User uzivatel = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(uzivatel == null) {
            loginRespDTO.setSuccess(false);
            loginRespDTO.setUser(null);
            return loginRespDTO;
        }
        loginRespDTO.setSuccess(true);
        loginRespDTO.setUser(uzivatel);
        return loginRespDTO;
    }
}
