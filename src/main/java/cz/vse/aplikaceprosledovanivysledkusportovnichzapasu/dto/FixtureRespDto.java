package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Score;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Trieda FixtureRespDto - slúži k formátovaniu dát v endpointoch.
 *
 * @author Zuzana Hadzimová
 * @version LS 2022/2023
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FixtureRespDto {
    private long id;
    private String sport;
    private String date;
    private String time;
    private long homeTeamId;
    private long awayTeamId;
    private String homeTeamName;
    private String awayTeamName;
    private String homeTeamLogo;
    private String awayTeamLogo;
    private boolean alreadyPlayed;
    private boolean isFavourite;
    private Score score;
    private String leagueName;
    private String leagueFlag;

}
