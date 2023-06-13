package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto;

import lombok.Data;

@Data
public class MatchListDateDTO {
    private long id;
    private String date;
    private String time;
    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private String homeTeamLogo;
    private String awayTeamLogo;
}
