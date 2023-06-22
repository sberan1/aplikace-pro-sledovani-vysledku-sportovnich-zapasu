package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Trieda SearchBarDto - slúži k formátovaniu dát v endpointoch.
 *
 * @author Štěpán Beran, Zuzana Hadzimová, Sabína Hrabáriková, Julie Sanetrníková, Adam Škarvada
 * @version LS 2022/2023
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchBarDto {
    long id;
    String name;
    String logo;
}