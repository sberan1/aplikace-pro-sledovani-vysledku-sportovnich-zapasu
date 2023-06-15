package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.MatchListDateDto;

import java.util.List;

public interface FixtureService {

    void fillBasketballFixture(int leagueExternalId, String season);

    List<MatchListDateDto> getFixturesBySportAndDate(String sport, String date);
}
