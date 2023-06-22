package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto;

import lombok.*;

/**
 * Trieda LeagueRespDto - slúži k formátovaniu dát v endpointoch.
 *
 * @author Štěpán Beran, Zuzana Hadzimová, Sabína Hrabáriková, Julie Sanetrníková, Adam Škarvada
 * @version LS 2022/2023
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeagueRespDto {
    private long id;
    private String name;
    private String flag;
}
