package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.BasketballScore;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Fixture;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Score;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model.ApiSports;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Service
public class FixtureServiceImpl implements FixtureService {

    @Autowired
    FixtureRepository fixtureRepository;
    @Autowired
    TeamService teamService;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    LeagueRepository leagueRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    BasketballScoreRepository basketballScoreRepository;
    ApiSports apiSports = ApiSports.getInstance();


    @Override
    public void fillBasketballFixture(int leagueExternalId, String season) {
        JSONObject resp = apiSports.basketbalZapasy(leagueExternalId, season);
        JSONArray fixtures = resp.getJSONArray("response");
        fixtures.forEach(o -> pridatZapasy((JSONObject) (o),"Basketball", resp));

    }

    private void pridatZapasy(JSONObject zapas, String sport, JSONObject resp) {
        Fixture fixtureEnt;
        if (fixtureRepository.findByExternalIdAndSport(zapas.getInt("id"), sport).isPresent()){
            fixtureEnt = fixtureRepository.findFixtureByExternalIdAndSport(zapas.getInt("id"), sport);
        }
        else {
            fixtureEnt = new Fixture();
        }
        fixtureEnt.setExternalId(zapas.getInt("id"));
        fixtureEnt.setDate(LocalDateTime.ofInstant(Instant.ofEpochSecond(zapas.getInt("timestamp")), TimeZone.getDefault().toZoneId()));
        fixtureEnt.setSport(sport);
        fixtureEnt.setLeague(leagueRepository.findLeagueByExternalIdAndSport(zapas.getJSONObject("league").getInt("id"), sport));
        fixtureEnt.setCountry(countryRepository.findCountryByExternalIdAndSport(zapas.getJSONObject("country").getInt("id"), sport));
        if (teamRepository.findByExternalIdAndSportAndName(zapas.getJSONObject("teams").getJSONObject("home").getInt("id"), sport, zapas.getJSONObject("teams").getJSONObject("home").getString("name")).isPresent()) {
            fixtureEnt.setHomeTeam(teamRepository.findTeamByExternalIdAndSport(zapas.getJSONObject("teams").getJSONObject("home").getInt("id"), sport));
            fixtureEnt.setAwayTeam(teamRepository.findTeamByExternalIdAndSport(zapas.getJSONObject("teams").getJSONObject("away").getInt("id"), sport));
        }
        else {
            teamService.fillBasketballTeamsByLeagueExternalIdAndSeason(zapas.getJSONObject("league").getInt("id"), zapas.getJSONObject("league").get("season").toString());
            fixtureEnt.setHomeTeam(teamRepository.findTeamByExternalIdAndSport(zapas.getJSONObject("teams").getJSONObject("home").getInt("id"), sport));
            fixtureEnt.setAwayTeam(teamRepository.findTeamByExternalIdAndSport(zapas.getJSONObject("teams").getJSONObject("away").getInt("id"), sport));
        }
        fillScore(fixtureEnt, zapas, sport);
        fixtureRepository.save(fixtureEnt);
    }

    private void fillScore(Fixture fixtureEnt, JSONObject zapas, String sport) {
        switch (sport){
            case "Basketball":
                BasketballScore basketballScore = new BasketballScore();
                if (zapas.getJSONObject("scores").getJSONObject("away").get("quarter_1") != JSONObject.NULL) {
                    basketballScore.setFirstQuarterHomeScore(zapas.getJSONObject("scores").getJSONObject("home").getInt("quarter_1"));
                    basketballScore.setSecondQuarterHomeScore(zapas.getJSONObject("scores").getJSONObject("home").getInt("quarter_2"));
                    basketballScore.setThirdQuarterHomeScore(zapas.getJSONObject("scores").getJSONObject("home").getInt("quarter_3"));
                    basketballScore.setFourthQuarterHomeScore(zapas.getJSONObject("scores").getJSONObject("home").getInt("quarter_4"));
                    basketballScore.setFinalHomeScore(zapas.getJSONObject("scores").getJSONObject("home").getInt("total"));

                    basketballScore.setFirstQuarterAwayScore(zapas.getJSONObject("scores").getJSONObject("away").getInt("quarter_1"));
                    basketballScore.setSecondQuarterAwayScore(zapas.getJSONObject("scores").getJSONObject("away").getInt("quarter_2"));
                    basketballScore.setThirdQuarterAwayScore(zapas.getJSONObject("scores").getJSONObject("away").getInt("quarter_3"));
                    basketballScore.setFourthQuarterAwayScore(zapas.getJSONObject("scores").getJSONObject("away").getInt("quarter_4"));
                    basketballScore.setFinalAwayScore(zapas.getJSONObject("scores").getJSONObject("away").getInt("total"));
                }
                else
                {
                    basketballScore.setFirstQuarterHomeScore(0);
                    basketballScore.setSecondQuarterHomeScore(0);
                    basketballScore.setThirdQuarterHomeScore(0);
                    basketballScore.setFourthQuarterHomeScore(0);
                    basketballScore.setFinalHomeScore(0);

                    basketballScore.setFirstQuarterAwayScore(0);
                    basketballScore.setSecondQuarterAwayScore(0);
                    basketballScore.setThirdQuarterAwayScore(0);
                    basketballScore.setFourthQuarterAwayScore(0);
                    basketballScore.setFinalAwayScore(0);
                }

                if (zapas.getJSONObject("scores").getJSONObject("home").get("over_time") != JSONObject.NULL){
                    basketballScore.setOvertimeHomeScore(zapas.getJSONObject("scores").getJSONObject("home").getInt("over_time"));
                    basketballScore.setOvertimeAwayScore(zapas.getJSONObject("scores").getJSONObject("away").getInt("over_time"));
                }
                else {
                    basketballScore.setOvertimeHomeScore(0);
                    basketballScore.setOvertimeAwayScore(0);
                }

                fixtureEnt.setScore(basketballScore);
                basketballScoreRepository.save(basketballScore);
                break;
            case "Football":
                break;
            case "Hockey":
                break;
            case "Volleyball":
                break;
        }

    }
}
