package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import lombok.Data;

@Data
public class LoginRespDTO {
    boolean success;
    User user;
}
