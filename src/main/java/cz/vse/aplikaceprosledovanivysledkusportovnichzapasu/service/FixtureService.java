package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.MatchListDateDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Fixture;

import java.util.List;

public interface FixtureService {

    List<MatchListDateDto> getFixturesBySportAndDate(String sport, String date, long league);

    void fillBasketballFixture(int leagueExternalId, String season);
    void fillHockeyFixture(int leagueExternalId, String season);
    void fillVolleyballFixture(int leagueExternalId, String season);
    void fillFootballFixture(int leagueExternalId, String season);
    Fixture getFixtureById(long id);
}
