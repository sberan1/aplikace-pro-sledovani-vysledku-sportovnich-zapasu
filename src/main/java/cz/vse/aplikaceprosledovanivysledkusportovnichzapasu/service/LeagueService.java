package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.LeagueRespDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.League;
import java.util.List;

/**
 * Interface CountryService - rozhranie definuje metódy pre prácu s ligami.
 *
 * @author Štěpán Beran, Zuzana Hadzimová, Sabína Hrabáriková, Julie Sanetrníková, Adam Škarvada
 * @version LS 2022/2023
 */

public interface LeagueService {
    void fillBasketballLeagues();
    void fillHockeyLeagues();
    void fillFootballLeagues();
    List<League> getLeagues();
    List<LeagueRespDto> getLeagueMatchesByDateAndSport(String date, String sport);
    List<League> getLeaguesBySport(String sport);
    public void fillVolleyballLeagues();
}
