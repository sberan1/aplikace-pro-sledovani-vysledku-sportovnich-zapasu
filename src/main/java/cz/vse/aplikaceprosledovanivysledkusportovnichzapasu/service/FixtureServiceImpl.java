package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.MatchListDateDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.BasketballScore;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Fixture;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.HockeyScore;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.VoleyballScore;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.SoccerScore;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model.ApiSports;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.*;
import org.apache.catalina.Store;
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
    @Autowired
    private HockeyScoreRepository hockeyScoreRepository;
    @Autowired
    private VoleyballScoreRepository voleyballScoreRepository;
    @Autowired
    SoccerScoreRepository footballScoreRepository;
    ApiSports apiSports = ApiSports.getInstance();


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

    @Override
    public void fillHockeyFixture(int leagueExternalId, String season) {
        JSONObject resp = apiSports.hokejZapasy(leagueExternalId, season);
        JSONArray fixtures = resp.getJSONArray("response");
        fixtures.forEach(o -> pridatZapasy((JSONObject) (o),"Hockey", resp));
    }

    @Override
    public void fillVolleyballFixture(int leagueExternalId, String season) {
        JSONObject resp = apiSports.hokejZapasy(leagueExternalId, season);
        JSONArray fixtures = resp.getJSONArray("response");
        fixtures.forEach(o -> pridatZapasy((JSONObject) (o), "Volleyball", resp));
    }

    @Override
    public void fillFootballFixture(int leagueExternalId, String season){
        JSONObject resp = apiSports.fotbalZapasy(leagueExternalId, season);
        JSONArray fixtures = resp.getJSONArray("response");
        fixtures.forEach(o ->{
            JSONObject zapas = (JSONObject)o;
            Fixture fixtureEnt;
            if (fixtureRepository.findByExternalIdAndSport(zapas.getJSONObject("fixture").getInt("id"), "Football").isPresent()){
                fixtureEnt = fixtureRepository.findFixtureByExternalIdAndSport(zapas.getJSONObject("fixture").getInt("id"), "Football");
            }
            else {
                fixtureEnt = new Fixture();
            }
            fixtureEnt.setExternalId(zapas.getJSONObject("fixture").getInt("id"));
            fixtureEnt.setDate(LocalDateTime.ofInstant(Instant.ofEpochSecond(zapas.getJSONObject("fixture").getInt("timestamp")), TimeZone.getDefault().toZoneId()));
            fixtureEnt.setSport("Football");
            fixtureEnt.setLeague(leagueRepository.findLeagueByExternalIdAndSport(zapas.getJSONObject("league").getInt("id"), "Football"));

            fixtureEnt.setCountry(countryRepository.findCountryByNameAndSport(zapas.getJSONObject("league").getString("country"), "Football").get());
            if (teamRepository.findTeamByExternalIdAndSport(zapas.getJSONObject("teams").getJSONObject("home").getInt("id"),"Football") != null) {
                fixtureEnt.setHomeTeam(teamRepository.findTeamByExternalIdAndSport(zapas.getJSONObject("teams").getJSONObject("home").getInt("id"), "Football"));
                fixtureEnt.setAwayTeam(teamRepository.findTeamByExternalIdAndSport(zapas.getJSONObject("teams").getJSONObject("away").getInt("id"), "Football"));
            }
            else {
                teamService.fillFootballTeamsByLeagueExternalIdAndSeason(zapas.getJSONObject("league").getInt("id"),zapas.getJSONObject("league").get("season").toString());
                fixtureEnt.setHomeTeam(teamRepository.findTeamByExternalIdAndSport(zapas.getJSONObject("teams").getJSONObject("home").getInt("id"), "Football"));
                fixtureEnt.setAwayTeam(teamRepository.findTeamByExternalIdAndSport(zapas.getJSONObject("teams").getJSONObject("away").getInt("id"), "Football"));
            }
            fillScore(fixtureEnt, zapas, "Football");
            fixtureRepository.save(fixtureEnt);
        });
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
            switch (sport) {
                case "Basketball":
                    teamService.fillBasketballTeamsByLeagueExternalIdAndSeason(zapas.getJSONObject("league").getInt("id"), zapas.getJSONObject("league").get("season").toString());
                case "Hockey":
                    teamService.fillHockeyTeamsByLeagueExternalIdAndSeason(zapas.getJSONObject("league").getInt("id"), zapas.getJSONObject("league").get("season").toString());
                case "Volleyball":
                    teamService.fillVolleyballTeamsByLeagueExternalIdAndSeason(zapas.getJSONObject("league").getInt("id"), zapas.getJSONObject("league").get("season").toString());
            }
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
                basketballScoreRepository.save(basketballScore);
                fixtureEnt.setScore(basketballScore);
                break;

            case "Football":
                SoccerScore footballScore;
                if (fixtureEnt.getScore()==null){
                    footballScore = new SoccerScore();}
                else {
                    footballScore =(SoccerScore) fixtureEnt.getScore();
                }
                if (zapas.getJSONObject("score").get("fulltime") != JSONObject.NULL){
                    footballScore.setFirstHalfHomeScore(zapas.getJSONObject("score").getJSONObject("halftime").getInt("home"));
                    footballScore.setFirstHalfAwayScore(zapas.getJSONObject("score").getJSONObject("halftime").getInt("away"));

                    footballScore.setSecondHalfHomeScore(zapas.getJSONObject("score").getJSONObject("fulltime").getInt("home"));
                    footballScore.setSecondHalfAwayScore(zapas.getJSONObject("score").getJSONObject("fulltime").getInt("away"));
                }
                else {
                    footballScore.setFirstHalfHomeScore(0);
                    footballScore.setFirstHalfAwayScore(0);

                    footballScore.setSecondHalfHomeScore(0);
                    footballScore.setSecondHalfAwayScore(0);
                }
                if (zapas.getJSONObject("score").getJSONObject("extratime").get("home") != JSONObject.NULL){
                    footballScore.setOvertimeHomeScore(zapas.getJSONObject("score").getJSONObject("extratime").getInt("home"));
                    footballScore.setOvertimeAwayScore(zapas.getJSONObject("score").getJSONObject("extratime").getInt("away"));
                }
                else {
                    footballScore.setOvertimeHomeScore(0);
                    footballScore.setOvertimeAwayScore(0);
                }
                if (zapas.getJSONObject("score").getJSONObject("penalty").get("away") != JSONObject.NULL){
                    footballScore.setPenaltyHomeScore(zapas.getJSONObject("score").getJSONObject("penalty").getInt("home"));
                    footballScore.setPenaltyAwayScore(zapas.getJSONObject("score").getJSONObject("penalty").getInt("away"));
                    break;
                }
                else {
                    footballScore.setPenaltyHomeScore(0);
                    footballScore.setPenaltyAwayScore(0);
                }
                footballScoreRepository.save(footballScore);
                fixtureEnt.setScore(footballScore);
                break;


            case "Hockey":
                HockeyScore hockeyScore;
                if (fixtureEnt.getScore() == null){
                    hockeyScore = new HockeyScore();}
                else {
                    hockeyScore = (HockeyScore) fixtureEnt.getScore();
                }
                if (zapas.getJSONObject("periods").get("first") != JSONObject.NULL) {
                    try {
                        hockeyScore.setFirstPeriodHomeScore(Integer.parseInt(zapas.getJSONObject("periods").getString("first").split("-")[0]));
                        hockeyScore.setSecondPeriodHomeScore(Integer.parseInt(zapas.getJSONObject("periods").getString("second").split("-")[0]));
                        hockeyScore.setThirdPeriodHomeScore(Integer.parseInt(zapas.getJSONObject("periods").getString("third").split("-")[0]));
                        hockeyScore.setFinalHomeScore(zapas.getJSONObject("scores").getInt("home"));

                        hockeyScore.setFirstPeriodAwayScore(Integer.parseInt(zapas.getJSONObject("periods").getString("first").split("-")[1]));
                        hockeyScore.setSecondPeriodAwayScore(Integer.parseInt(zapas.getJSONObject("periods").getString("second").split("-")[1]));
                        hockeyScore.setThirdPeriodAwayScore(Integer.parseInt(zapas.getJSONObject("periods").getString("third").split("-")[1]));
                        hockeyScore.setFinalAwayScore(zapas.getJSONObject("scores").getInt("away"));

                        if (zapas.getJSONObject("periods").get("penalties") != JSONObject.NULL) {
                            hockeyScore.setOvertimeHomeScore(Integer.parseInt(zapas.getJSONObject("periods").getString("overtime").split("-")[0]));
                            hockeyScore.setShootoutHomeScore(Integer.parseInt(zapas.getJSONObject("periods").getString("penalties").split("-")[0]));
                            hockeyScore.setOvertimeAwayScore(Integer.parseInt(zapas.getJSONObject("periods").getString("overtime").split("-")[1]));
                            hockeyScore.setShootoutAwayScore(Integer.parseInt(zapas.getJSONObject("periods").getString("penalties").split("-")[1]));
                        }
                        else {
                            hockeyScore.setOvertimeHomeScore(0);
                            hockeyScore.setShootoutHomeScore(0);
                            hockeyScore.setOvertimeAwayScore(0);
                            hockeyScore.setShootoutAwayScore(0);
                        }
                    }
                    catch (Exception e){
                        hockeyScore.setFirstPeriodHomeScore(0);
                        hockeyScore.setSecondPeriodHomeScore(0);
                        hockeyScore.setThirdPeriodHomeScore(0);
                        hockeyScore.setFinalHomeScore(0);

                        hockeyScore.setFirstPeriodAwayScore(0);
                        hockeyScore.setSecondPeriodAwayScore(0);
                        hockeyScore.setThirdPeriodAwayScore(0);
                        hockeyScore.setFinalAwayScore(0);
                    }
                }
                else
                {
                    hockeyScore.setFirstPeriodHomeScore(0);
                    hockeyScore.setSecondPeriodHomeScore(0);
                    hockeyScore.setThirdPeriodHomeScore(0);
                    hockeyScore.setFinalHomeScore(0);

                    hockeyScore.setFirstPeriodAwayScore(0);
                    hockeyScore.setSecondPeriodAwayScore(0);
                    hockeyScore.setThirdPeriodAwayScore(0);
                    hockeyScore.setFinalAwayScore(0);
                }
                hockeyScoreRepository.save(hockeyScore);
                fixtureEnt.setScore(hockeyScore);
                break;

            case "Volleyball":
                VoleyballScore voleyballScore;
                if (fixtureEnt.getScore() == null){
                    voleyballScore = new VoleyballScore();}
                else {
                    voleyballScore = (VoleyballScore) fixtureEnt.getScore();
                }
                if (zapas.getJSONObject("periods").getJSONObject("first")!= JSONObject.NULL) {

                    voleyballScore.setFirstSetAwayScore(zapas.getJSONObject("periods").getJSONObject("first").getInt("away"));
                    voleyballScore.setFirstSetHomeScore(zapas.getJSONObject("periods").getJSONObject("first").getInt("home"));

                    voleyballScore.setSecondSetAwayScore(zapas.getJSONObject("periods").getJSONObject("second").getInt("away"));
                    voleyballScore.setSecondSetHomeScore(zapas.getJSONObject("periods").getJSONObject("second").getInt("home"));

                    voleyballScore.setThirdSetAwayScore(zapas.getJSONObject("periods").getJSONObject("third").getInt("away"));
                    voleyballScore.setThirdSetHomeScore(zapas.getJSONObject("periods").getJSONObject("third").getInt("home"));

                    voleyballScore.setFourthSetAwayScore(zapas.getJSONObject("periods").getJSONObject("fourth").getInt("away"));
                    voleyballScore.setFourthSetHomeScore(zapas.getJSONObject("periods").getJSONObject("fourth").getInt("home"));

                    voleyballScore.setFifthSetAwayScore(zapas.getJSONObject("periods").getJSONObject("fifth").getInt("away"));
                    voleyballScore.setFifthSetHomeScore(zapas.getJSONObject("periods").getJSONObject("fifth").getInt("home"));
                    }
                    else {
                        voleyballScore.setFirstSetAwayScore(0);
                        voleyballScore.setFirstSetHomeScore(0);
                        voleyballScore.setSecondSetAwayScore(0);
                        voleyballScore.setSecondSetHomeScore(0);
                        voleyballScore.setThirdSetAwayScore(0);
                        voleyballScore.setThirdSetHomeScore(0);
                        voleyballScore.setFourthSetAwayScore(0);
                        voleyballScore.setFourthSetHomeScore(0);
                        voleyballScore.setFifthSetAwayScore(0);
                        voleyballScore.setFifthSetHomeScore(0);
                    }
                voleyballScoreRepository.save(voleyballScore);
                fixtureEnt.setScore(voleyballScore);
                break;
        }

    }



}
