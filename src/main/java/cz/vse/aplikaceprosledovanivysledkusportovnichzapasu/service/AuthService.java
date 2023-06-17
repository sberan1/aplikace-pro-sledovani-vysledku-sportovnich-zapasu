package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.AuthRequest;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.AuthenticationResponse;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.ChangePasswordDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.RegisterRequest;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthRequest request);
    User changePassword(ChangePasswordDto changePasswordDto, String jwt);


}
