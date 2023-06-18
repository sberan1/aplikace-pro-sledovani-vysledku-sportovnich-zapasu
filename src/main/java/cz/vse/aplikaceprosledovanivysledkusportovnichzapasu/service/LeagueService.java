package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.LeagueRespDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.ContentHolder;
import java.util.List;


public interface LeagueService {
    void fillBasketballLeagues();
    void fillHockeyLeagues();
    List<ContentHolder> getLeagues();
    List<LeagueRespDto> getLeagueMatchesByDateAndSport(String date, String sport);
    List<ContentHolder> getLeaguesBySport(String sport);
}
