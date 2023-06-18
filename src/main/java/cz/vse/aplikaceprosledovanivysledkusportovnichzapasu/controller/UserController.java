package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.AuthRequest;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.AuthenticationResponse;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.ChangePasswordDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.RegisterRequest;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Fixture;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Team;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model.OpenAI;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.JwtService;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.UserRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.AuthService;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @GetMapping(value = "/OpenAiCall")
    public ResponseEntity<String> OpenAiCall(){
        return ResponseEntity.ok("");
    }
    @GetMapping(value = "/getUserInfo")
    public ResponseEntity<User> getUserInfo(HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        return ResponseEntity.ok(userService.getUserFromToken(jwt));
    }

    @PutMapping( value = "/changePassword")
    public ResponseEntity<Object> changePassword(@RequestBody ChangePasswordDto changePasswordDto, HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        User user = authService.changePassword(changePasswordDto, jwt);
        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Old password didn't match");
        }
        return ResponseEntity.ok(user);
    }
    @PutMapping(value = "/addFavouriteTeam")
    public ResponseEntity<String> addFavouriteTeam(@RequestParam long teamId, HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        userService.addFavouriteTeam(teamId, jwt);
        return ResponseEntity.ok("Team was added");
    }

    @DeleteMapping(value = "/removeFavouriteTeam")
    public ResponseEntity<String> removeFavouriteTeam(@RequestParam long teamId, HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        userService.removeFavouriteTeam(teamId, jwt);
        return ResponseEntity.ok("Team was removed");
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteUser(userService.getUserFromToken(authentication.getName()).getId());
        return ResponseEntity.ok("User deleted");
    }

    @GetMapping (value = "/getFavouriteTeams")
    public ResponseEntity<Set<Team>> getFavouriteTeams(HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        return ResponseEntity.ok(userService.getFavouriteTeams(jwt));
    }

    @GetMapping (value = "/getFavouriteFixtures")
    public ResponseEntity<Set<Fixture>> getFavouriteFixtures(HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        return ResponseEntity.ok(userService.getFavouriteFixtures(jwt));
    }

    @PutMapping (value = "/addFavouriteFixture")
    public ResponseEntity<String> addFavouriteFixture(@RequestParam long id, HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        userService.addFavouriteFixture(id, jwt);
        return ResponseEntity.ok("Fixture was added");
    }

    @DeleteMapping (value = "/removeFavouriteFixture")
    public ResponseEntity<String> removeFavouriteFixture(@RequestParam long id, HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        userService.removeFavouriteFixture(id, jwt);
        return ResponseEntity.ok("Fixture was removed");
    }
}
