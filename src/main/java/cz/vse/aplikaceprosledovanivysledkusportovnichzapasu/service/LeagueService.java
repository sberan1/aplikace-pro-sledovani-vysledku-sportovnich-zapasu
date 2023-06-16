package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.LeagueRespDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.League;
import java.util.List;


public interface LeagueService {
    void fillBasketballLeagues();
    void fillHockeyLeagues();
    void fillFootballLeagues();
    List<League> getLeagues();
    List<LeagueRespDto> getLeagueMatchesByDateAndSport(String date, String sport);
    List<League> getLeaguesBySport(String sport);
    public void fillVolleyballLeagues();
}
