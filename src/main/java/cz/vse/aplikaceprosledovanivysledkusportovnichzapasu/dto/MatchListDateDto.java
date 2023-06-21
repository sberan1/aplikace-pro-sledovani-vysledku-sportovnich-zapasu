package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto;

import lombok.Data;

@Data

public class MatchListDateDto {
    private long id;
    private String date;
    private String time;
    private long homeTeamId;
    private String homeTeam;
    private long awayTeamId;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private String homeTeamLogo;
    private String awayTeamLogo;
}
