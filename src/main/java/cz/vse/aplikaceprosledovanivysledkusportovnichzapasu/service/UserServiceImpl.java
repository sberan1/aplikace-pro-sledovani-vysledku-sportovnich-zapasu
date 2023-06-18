package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.ChangePasswordDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;


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
    public User getUserFromToken(String jwt){
        String email = jwtService.extractEmail(jwt);
        return userRepository.findByEmail(email).get();
    }

    @Override
    public void addFavouriteTeam(long teamId, String jwt) {
        User user= getUserFromToken(jwt);
        user.getFavouriteTeams().add(teamRepository.findById(teamId).get());
    }

}
