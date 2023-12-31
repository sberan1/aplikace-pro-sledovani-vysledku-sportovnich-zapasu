package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.AuthRequest;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.AuthenticationResponse;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.ChangePasswordDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.RegisterRequest;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.UserRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Trieda AuthServiceImpl - implementuje rozhranie AuthService a poskytuje konkrétnu implementáciu metód pre autentifikáciu a autorizáciu.
 *
 * @author Štěpán Beran, Zuzana Hadzimová, Sabína Hrabáriková, Julie Sanetrníková, Adam Škarvada
 * @version LS 2022/2023
 */

@Service @RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    /**
     * Metóda slúžiaca k registrácii používateľa pomocou jednotlivých požiadavok.
     * @param request
     * @return login token
     */
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        User user = new User();
        user.setName(request.getFirstName());
        user.setSurname(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwt)
                .build();
    }

    /**
     * Metóda pre autentifikáciu používateľa pomocou jednotlivých požiadavok.
     * @param request
     * @return token
     */

    @Override
    public AuthenticationResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getHashedPassword()
                ));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwt)
                .build();
    }

    /**
     * Metóda pre zmenu hesla.
     * @param changePasswordDto
     * @param jwt
     * @return aktualizácia hesla alebo null
     */

    @Override
    public User changePassword(ChangePasswordDto changePasswordDto, String jwt) {
        User user = userService.getUserFromToken(jwt);
        if(!passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword()))
        {
            return null;
        }
        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        userRepository.save(user);
        return user;
    }

    /**
     * Metóda pre aktualizáciu tokenu.
     * @param request
     * @param response
     * @return nový vygenerovaný token alebo null
     */

    @Override
    public AuthenticationResponse refreshToken(HttpServletRequest request, HttpServletResponse response){
            final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            final String userEmail;
            final String refreshToken;
            AuthenticationResponse authResponse;
            if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
                return null;
            }
            refreshToken = authHeader.substring(7);
            userEmail = jwtService.extractEmail(refreshToken);
            if (userEmail != null) {
                var user = userRepository.findByEmail(userEmail)
                        .orElseThrow();
                if (jwtService.isTokenValid(refreshToken, user)) {
                    var accessToken = jwtService.generateToken(user);
                     authResponse = AuthenticationResponse.builder()
                            .token(accessToken)
                            .build();
                    return authResponse;
                }
            }
            return null;
    }
}

