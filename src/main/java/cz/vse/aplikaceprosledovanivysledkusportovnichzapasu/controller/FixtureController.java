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

@RestController
@RequestMapping(value = "/fixture")
@CrossOrigin
public class FixtureController {
    @Autowired
    UserService userService;
    @Autowired
    FixtureService fixtureService;

    FixtureRepository fixtureRepository;

    @PostMapping(value = "/fillFixturesBasketball")
    public void fillTeamsBasketball(@RequestParam int leagueExternalId, @RequestParam String season) {
        fixtureService.fillBasketballFixture(leagueExternalId, season);
    }

    @PostMapping(value = "/fillFixturesHockey")
    public void fillTeamsHockey(@RequestParam int leagueExternalId, @RequestParam String season) {
        fixtureService.fillHockeyFixture(leagueExternalId, season);
    }

    @PostMapping(value = "/fillFixturesVolleyball")
    public void fillTeamsVolleyball(@RequestParam int leagueExternalId, @RequestParam String season) {
        fixtureService.fillVolleyballFixture(leagueExternalId, season);
    }

    @PostMapping(value = "/fillFixturesFootball")
    public void fillTeamsFootball(@RequestParam int leagueExternalId, @RequestParam String season) {
        fixtureService.fillFootballFixture(leagueExternalId, season);
    }

    @GetMapping("/getFixturesBySportAndDate")
    public List<MatchListDateDto> getFixturesBySportAndDate(@RequestParam String sport, @RequestParam String date, @RequestParam long league)
    {
        return fixtureService.getFixturesBySportAndDate(sport, date, league);
    }

    @GetMapping("/getFixturesByTeamIdAndDateBeforeToday/{teamId}")
    public ResponseEntity<List<MatchListDateDto>> getFixturesByTeamIdAndDateBeforeToday(@PathVariable long teamId)
    {
        return ResponseEntity.ok(fixtureService.getFixturesByTeamIdAndDateBeforeToday(teamId));
    }

    @GetMapping("/getFixturesByTeamIdAndDateFromToday/{teamId}")
    public ResponseEntity<List<MatchListDateDto>> getFixturesByTeamIdAndDateFromToday(@PathVariable long teamId)
    {
        return ResponseEntity.ok(fixtureService.getFixturesByTeamIdAndDateFromToday(teamId));
    }

    @GetMapping("/getFixturesById")
    public Fixture getFixturesById(@RequestParam long id){
        return fixtureService.getFixtureById(id);
    }

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
