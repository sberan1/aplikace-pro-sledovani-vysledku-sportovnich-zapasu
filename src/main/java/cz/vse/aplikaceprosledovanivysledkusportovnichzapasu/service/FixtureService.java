package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.FixtureRespDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.MatchListDateDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Fixture;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;

import java.util.List;

/**
 * Interface CountryService - rozhranie definuje metódy pre prácu so zápasmi.
 *
 * @author Štěpán Beran, Zuzana Hadzimová, Sabína Hrabáriková, Julie Sanetrníková, Adam Škarvada
 * @version LS 2022/2023
 */

public interface FixtureService {

    List<MatchListDateDto> getFixturesBySportAndDate(String sport, String date, long league);
    List<MatchListDateDto> getFixturesByTeamIdAndDateBeforeToday(long teamId);
    List<MatchListDateDto> getFixturesByTeamIdAndDateFromToday(long teamId);


    void fillBasketballFixture(int leagueExternalId, String season);
    void fillHockeyFixture(int leagueExternalId, String season);
    void fillVolleyballFixture(int leagueExternalId, String season);
    void fillFootballFixture(int leagueExternalId, String season);
    Fixture getFixtureById(long id);
    FixtureRespDto getFixtureInfoById(long id, User user);
}
