package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.AuthRequest;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.AuthenticationResponse;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.ChangePasswordDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.RegisterRequest;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.JwtService;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.UserRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.AuthService;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<AuthenticationResponse> register
            (@RequestBody RegisterRequest request) {
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
        return ResponseEntity.ok(userService.getUserFromToken(authentication.getName()));
    }

    @PutMapping( value = "/changePassword")
    public ResponseEntity<Object> changePassword(@RequestBody ChangePasswordDto changePasswordDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = authService.changePassword(changePasswordDto, authentication.getName());
        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Old password didn't match");
        }
        return ResponseEntity.ok(user);
    }


}
