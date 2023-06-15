package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeagueRespDto {
    private long id;
    private String name;
    private String flag;
}
