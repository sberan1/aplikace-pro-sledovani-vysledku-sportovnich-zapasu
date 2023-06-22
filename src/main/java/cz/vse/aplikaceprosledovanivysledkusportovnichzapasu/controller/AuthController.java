package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.AuthRequest;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.AuthenticationResponse;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.RegisterRequest;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.SearchBarDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model.OpenAI;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.AuthService;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.UserService;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Trieda AuthController - slúží k spracovaniu požiadaviek súvisiacich s autentifikáciou a autorizáciou používateľa.
 *
 * @author Štěpán Beran, Zuzana Hadzimová, Sabína Hrabáriková, Julie Sanetrníková, Adam Škarvada
 * @version LS 2022/2023
 */


@RestController
@CrossOrigin
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    /**
     * Metóda pre spracovanie požiadavky na registráciu používateľa.
     * @param request
     * @return úspešnú registráciu alebo zobrazenie chybovej hlášky
     */


    @PostMapping(value = "/register")
    public ResponseEntity<Object> register
            (@RequestBody RegisterRequest request) {
        if (userService.emailExists(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already taken");
        }
        return ResponseEntity.ok(authService.register(request));
    }

    /**
     * Metóda pre zmenu value login tokenu.
     * @param request
     * @param response
     * @return  zmenená value
     */

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request, HttpServletResponse response)
    {
        return ResponseEntity.ok(authService.refreshToken(request, response));
    }

    /**
     * Metóda pre spracovanie požiadavky na overenie používateľa.
     * @param request
     * @return autentifikáciu používateľa
     */

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate
            (@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    /**
     * Metóda pre získanie informácií o používateľovi.
     * @param request
     * @return informácie o používateľovi
     */

    @GetMapping(value = "/getUserInfo")
    public User getUserInfo(HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        return userService.getUserFromToken(jwt);
    }

    @GetMapping(value = "/OpenAiCall")
    public ResponseEntity<List<SearchBarDto>> OpenAiCall(HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        return ResponseEntity.ok(userService.callFavouriteTeamsOpenAi(jwt));
    }

}
