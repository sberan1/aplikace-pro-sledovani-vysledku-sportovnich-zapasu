package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.AuthRequest;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.AuthenticationResponse;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.RegisterRequest;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model.OpenAI;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.JwtService;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.UserRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.AuthService;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/add")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable Long id) {
       return userService.getUserById(id);
    }
    @GetMapping(value = "/getAll")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Object> register
            (@RequestBody RegisterRequest request) {
        if (userService.emailExists(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already taken");
        }
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate
            (@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping(value = "/getUserInfo")
    public ResponseEntity<User> getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).get();
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/OpenAiCall")
    public ResponseEntity<String> OpenAiCall(){
        return ResponseEntity.ok(OpenAI.authentikace("Sparta Praha, Slavia Praha, Detroit Pistons", "Ligue 1, NBA").getBody());
    }
}
