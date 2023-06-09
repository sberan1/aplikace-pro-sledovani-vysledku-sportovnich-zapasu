package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.League;
import java.util.List;


public interface LeagueService {
    void fillBasketballLeagues();
    void fillHockeyLeagues();
    List<League> getLeagues();

    List<League> getLeaguesBySport(String sport);
}
