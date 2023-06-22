package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Trieda emailBodyDto - slúži k formátovaniu dát v endpointoch.
 *
 * @author Štepán Beran
 * @version LS 2022/2023
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class emailBodyDto {
    private String email;
}
