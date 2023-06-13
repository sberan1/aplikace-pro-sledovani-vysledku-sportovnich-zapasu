package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.MatchListDateDTO;

import java.util.List;

public interface FixtureService {

    void fillBasketballFixture(int leagueExternalId, String season);

    List<MatchListDateDTO> getFixturesBySportAndDate(String sport, String date);
}
