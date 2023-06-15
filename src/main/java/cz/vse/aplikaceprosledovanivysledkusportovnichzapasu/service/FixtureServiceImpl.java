package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.MatchListDateDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.BasketballScore;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Fixture;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model.ApiSports;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public List<MatchListDateDto> getFixturesBySportAndDate(String sport, String date, long league) {
        List<MatchListDateDto> DTOList = new ArrayList<>();
        int [] datumString = Arrays.stream(date.split("-")).mapToInt(Integer::parseInt).toArray();
        List<Fixture> seznamZapasu =  fixtureRepository.findAllByDateAndSport(LocalDateTime.of(datumString[0], datumString[1], datumString[2], 0, 0),LocalDateTime.of(datumString[0], datumString[1], datumString[2] + 1, 0, 0),sport, league);
        for (Fixture fixture : seznamZapasu) {
            MatchListDateDto matchListDateDTO = new MatchListDateDto();
            matchListDateDTO.setId(fixture.getId());
            matchListDateDTO.setDate(fixture.getDate().format(DateTimeFormatter.ISO_DATE));
            matchListDateDTO.setTime(fixture.getDate().format(DateTimeFormatter.ofPattern("HH:mm")));
            matchListDateDTO.setHomeTeam(fixture.getHomeTeam().getName());
            matchListDateDTO.setAwayTeam(fixture.getAwayTeam().getName());
            matchListDateDTO.setHomeTeamScore(fixture.getScore().getFinalHomeScore());
            matchListDateDTO.setAwayTeamScore(fixture.getScore().getFinalAwayScore());
            matchListDateDTO.setHomeTeamLogo(fixture.getHomeTeam().getLogo());
            matchListDateDTO.setAwayTeamLogo(fixture.getAwayTeam().getLogo());
            DTOList.add(matchListDateDTO);
        }
        return DTOList;
    }

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
        if (teamRepository.findTeamByExternalIdAndSport(zapas.getJSONObject("teams").getJSONObject("home").getInt("id"), sport) != null) {
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
                BasketballScore basketballScore;
                if (fixtureEnt.getScore() == null){
                 basketballScore = new BasketballScore();}
                else {
                    basketballScore = (BasketballScore) fixtureEnt.getScore();
                }
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
