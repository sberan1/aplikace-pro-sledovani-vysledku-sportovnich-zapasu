package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.FixtureRespDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.MatchListDateDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.FixtureRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Fixture;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.FixtureService;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Trieda FixtureController - slúži pre manipuláciu s databázou zápasov pri jednotlivých športoch.
 *
 * @author Štěpán Beran, Zuzana Hadzimová, Sabína Hrabáriková, Julie Sanetrníková, Adam Škarvada
 * @version LS 2022/2023
 */

@RestController
@RequestMapping(value = "/fixture")
@CrossOrigin
public class FixtureController {
    @Autowired
    UserService userService;
    @Autowired
    FixtureService fixtureService;

    FixtureRepository fixtureRepository;

    /**
     * Metóda pre naplnenie databáze zápasmi pri športe basketbal.
     * @param leagueExternalId
     * @param season
     *
     */

    @PostMapping(value = "/fillFixturesBasketball")
    public void fillTeamsBasketball(@RequestParam int leagueExternalId, @RequestParam String season) {
        fixtureService.fillBasketballFixture(leagueExternalId, season);
    }

    /**
     * Metóda pre naplnenie databáze zápasmi pri športe hokej.
     * @param leagueExternalId
     * @param season
     */

    @PostMapping(value = "/fillFixturesHockey")
    public void fillTeamsHockey(@RequestParam int leagueExternalId, @RequestParam String season) {
        fixtureService.fillHockeyFixture(leagueExternalId, season);
    }

    /**
     * Metóda pre naplnenie databáze zápasmi pri športe volejbal.
     * @param leagueExternalId
     * @param season
     */

    @PostMapping(value = "/fillFixturesVolleyball")
    public void fillTeamsVolleyball(@RequestParam int leagueExternalId, @RequestParam String season) {
        fixtureService.fillVolleyballFixture(leagueExternalId, season);
    }

    /**
     * Metóda pre naplnenie databáze zápasmi pri športe futbal.
     * @param leagueExternalId
     * @param season
     */

    @PostMapping(value = "/fillFixturesFootball")
    public void fillTeamsFootball(@RequestParam int leagueExternalId, @RequestParam String season) {
        fixtureService.fillFootballFixture(leagueExternalId, season);
    }

    /**
     * Metóda pre získanie zápasov podľa športu, dátumu a ligy.
     * @param sport
     * @param date
     * @param league
     * @return List s informáciami o zápasoch
     */

    @GetMapping("/getFixturesBySportAndDate")
    public List<MatchListDateDto> getFixturesBySportAndDate(@RequestParam String sport, @RequestParam String date, @RequestParam long league)
    {
        return fixtureService.getFixturesBySportAndDate(sport, date, league);
    }

    /**
     * Metóda pre získanie zápasov podľa id týmu, pre už odohrané zápasy.
     * @param teamId
     * @return List s informáciami o zápaspch
     */

    @GetMapping("/getFixturesByTeamIdAndDateBeforeToday/{teamId}")
    public List<MatchListDateDto> getFixturesByTeamIdAndDateBeforeToday(@PathVariable long teamId)
    {
        return fixtureService.getFixturesByTeamIdAndDateBeforeToday(teamId);
    }

    /**
     * Metóda pre získanie zápasov podľa id týmu, pre naplánované zápasy.
     * @param teamId
     * @return List s informáciami o zápasoch
     */

    @GetMapping("/getFixturesByTeamIdAndDateFromToday/{teamId}")
    public List<MatchListDateDto> getFixturesByTeamIdAndDateFromToday(@PathVariable long teamId)
    {
        return fixtureService.getFixturesByTeamIdAndDateFromToday(teamId);
    }

    /**
     * Metóda pre získanie zápasu podľa id.
     * @return informácie o zápase
     */

    @GetMapping("/getFixturesById")
    public Fixture getFixturesById(@RequestParam long id){
        return fixtureService.getFixtureById(id);
    }

    /**
     * Metóda pre získanie informácií o zápasoch podľa identifikačného čísla a užívateľa.
     * @param id
     * @param request
     * @return informácie o zápase
     */

    @GetMapping("/getFixtureInfoById")
    public ResponseEntity<FixtureRespDto> getFixtureById(@RequestParam Long id, HttpServletRequest request) {
        if(request.getHeader("Authorization") != null){
            String jwt = request.getHeader("Authorization").substring(7);
            User user = userService.getUserFromToken(jwt);
            return ResponseEntity.ok(fixtureService.getFixtureInfoById(id, user));
        }
        return ResponseEntity.ok(fixtureService.getFixtureInfoById(id, null));
    }
}
