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

/**
 * Trieda UserController - slúži pre manipuláciu s databázou obsahujúcou rôzne informácie o jednotlivých používateľoch.
 *
 * @author Štepán Beran, Zuzana Hadzimová, Sabína Hrabáriková
 * @version LS 2022/2023
 */

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    /**
     * Metóda pre kontrolu mailu.
     * @param emailBodyDto
     * @return boolean podľa toho, či sa mail nachádza v databáze
     */

    @PostMapping(value = "/checkEmail")
    public ResponseEntity<Boolean> checkEmail(@RequestBody emailBodyDto emailBodyDto){
        return ResponseEntity.ok(userService.emailExists(emailBodyDto.getEmail()));
    }

    /**
     * Metóda pre zmenu hesla používateľa.
     * @param changePasswordDto
     * @param request
     * @return aktualizácia hesla alebo zobrazenie chybovej hláška
     */


    @PutMapping( value = "/changePassword")
    public ResponseEntity<Object> changePassword(@RequestBody ChangePasswordDto changePasswordDto, HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        User user = authService.changePassword(changePasswordDto, jwt);
        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Old password didn't match");
        }
        return ResponseEntity.ok(user);
    }

    /**
     * Metóda pre pridanie týmu medzi obľúbené na základe team id.
     * @param teamId
     * @param request
     * @return zvolený tým sa pridá medzi obľúbené
     */

    @PutMapping(value = "/addFavouriteTeam/{teamId}")
    public ResponseEntity<String> addFavouriteTeam(@PathVariable long teamId, HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        userService.addFavouriteTeam(teamId, jwt);
        return ResponseEntity.ok("Team was added");
    }

    /**
     * Metóda pre odstránenie týmu z obľúbených na základe team id.
     * @param teamId
     * @param request
     * @return zvolený tým sa odstráni z obľúbených
     */

    @DeleteMapping(value = "/removeFavouriteTeam/{teamId}")
    public ResponseEntity<String> removeFavouriteTeam(@PathVariable long teamId, HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        userService.removeFavouriteTeam(teamId, jwt);
        return ResponseEntity.ok("Team was removed");
    }

    /**
     * Metóda pre odstránenie užívateľa z databáze registrovaných.
     * @return užívateľ sa odstráni z databázy a zobrazí sa správa o úspešnom odstránení
     */

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> deleteUser(HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        userService.deleteUser(userService.getUserFromToken(jwt).getId());
        return ResponseEntity.ok("User deleted");
    }

    /**
     * Metóda pre možnosť pridania týmu medzi obľúbené
     * @return zvolený tým sa pridá medzi obľúbené
     */

    @GetMapping (value = "/getFavouriteTeams")
    public ResponseEntity<Set<SearchBarDto>> getFavouriteTeams(HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        return ResponseEntity.ok(userService.getFavouriteTeams(jwt));
    }

    /**
     * Metóda pre možnosť pridania zápasu medzi obľúbené
     * @return zvolený zápas sa pridá medzi obľúbené
     */

    @GetMapping (value = "/getFavouriteFixtures")
    public ResponseEntity<Set<MatchListDateDto>> getFavouriteFixtures(HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        return ResponseEntity.ok(userService.getFavouriteFixtures(jwt));
    }

    /**
     * Metóda pre možnosť pridania zápasu do obľúbeneých na základe identifikačného čísla .
     * @param id
     * @param request
     * @return zvolený zápas sa pridá medzi obľúbené
     */


    @PutMapping (value = "/addFavouriteFixture/{id}")
    public ResponseEntity<String> addFavouriteFixture(@PathVariable long id, HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        userService.addFavouriteFixture(id, jwt);
        return ResponseEntity.ok("Fixture was added");
    }

    /**
     * Metóda pre možnosť odstránenia zápasu z obľúbeneých na základe identifikačného čísla .
     * @param id
     * @param request
     * @return zvolený zápas sa odstráni z obľúbených
     */

    @DeleteMapping (value = "/removeFavouriteFixture/{id}")
    public ResponseEntity<String> removeFavouriteFixture(@PathVariable long id, HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        userService.removeFavouriteFixture(id, jwt);
        return ResponseEntity.ok("Fixture was removed");
    }
}
