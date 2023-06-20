package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.AuthRequest;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.AuthenticationResponse;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.ChangePasswordDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.RegisterRequest;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.UserRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getFirstName())
                .surname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwt)
                .build();
    }

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


    @Override
    public User changePassword(ChangePasswordDto changePasswordDto, String jwt) {
        User user = userService.getUserFromToken(jwt);
        if(!user.getPassword().equals( passwordEncoder.encode(changePasswordDto.getOldPassword())))
        {
            return null;
        }
            user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        return user;
    }
}
