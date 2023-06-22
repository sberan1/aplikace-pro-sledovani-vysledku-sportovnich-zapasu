package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Trieda ChangePasswordDto - slúži k formátovaniu dát v endpointoch.
 *
 * @author Zuzana Hadzimová
 * @version LS 2022/2023
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordDto {
    private String oldPassword;
    private String newPassword;
}
