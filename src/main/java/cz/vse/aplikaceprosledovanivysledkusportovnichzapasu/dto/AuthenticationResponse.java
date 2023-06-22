package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Trieda AuthenticationResponse - slúži k formátovaniu dát v endpointoch.
 *
 * @author Štepán Beran
 * @version LS 2022/2023
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    private String token;
}
