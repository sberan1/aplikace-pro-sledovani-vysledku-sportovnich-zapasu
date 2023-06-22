package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Trieda TeamRespDto - slúži k formátovaniu dát v endpointoch.
 *
 * @author  Zuzana Hadzimová
 * @version LS 2022/2023
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamRespDto {
    private long id;
    private String name;
    private String sport;
    private String teamLogo;
    private String country;
    private String countryLogo;
    private boolean isFavourite;
}
