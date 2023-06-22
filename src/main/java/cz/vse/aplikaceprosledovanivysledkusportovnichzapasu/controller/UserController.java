package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.*;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Fixture;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Team;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model.OpenAI;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.JwtService;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.UserRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.AuthService;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PostMapping(value = "/checkEmail")
    public ResponseEntity<Boolean> checkEmail(@RequestBody emailBodyDto emailBodyDto){
        return ResponseEntity.ok(userService.emailExists(emailBodyDto.getEmail()));
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
    @PutMapping(value = "/addFavouriteTeam/{teamId}")
    public ResponseEntity<String> addFavouriteTeam(@PathVariable long teamId, HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        userService.addFavouriteTeam(teamId, jwt);
        return ResponseEntity.ok("Team was added");
    }

    @DeleteMapping(value = "/removeFavouriteTeam/{teamId}")
    public ResponseEntity<String> removeFavouriteTeam(@PathVariable long teamId, HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        userService.removeFavouriteTeam(teamId, jwt);
        return ResponseEntity.ok("Team was removed");
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> deleteUser(HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        userService.deleteUser(userService.getUserFromToken(jwt).getId());
        return ResponseEntity.ok("User deleted");
    }

    @GetMapping (value = "/getFavouriteTeams")
    public ResponseEntity<Set<SearchBarDto>> getFavouriteTeams(HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        return ResponseEntity.ok(userService.getFavouriteTeams(jwt));
    }

    @GetMapping (value = "/getFavouriteFixtures")
    public ResponseEntity<Set<MatchListDateDto>> getFavouriteFixtures(HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        return ResponseEntity.ok(userService.getFavouriteFixtures(jwt));
    }

    @PutMapping (value = "/addFavouriteFixture/{id}")
    public ResponseEntity<String> addFavouriteFixture(@PathVariable long id, HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        userService.addFavouriteFixture(id, jwt);
        return ResponseEntity.ok("Fixture was added");
    }

    @DeleteMapping (value = "/removeFavouriteFixture/{id}")
    public ResponseEntity<String> removeFavouriteFixture(@PathVariable long id, HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        userService.removeFavouriteFixture(id, jwt);
        return ResponseEntity.ok("Fixture was removed");
    }
}
