package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Trieda RegisterRequest - slúži k formátovaniu dát v endpointoch.
 *
 * @author Štepán Beran
 * @version LS 2022/2023
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
