package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.MatchListDateDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.FixtureRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/fixture")
@CrossOrigin
public class FixtureController {
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

    @GetMapping("/getFixturesByTeamIdAndDateBeforeToday")
    public List<MatchListDateDto> getFixturesByTeamIdAndDateBeforeToday( @RequestParam long id)
    {
        return fixtureRepository.findFixturesByTeamIdAndDateBeforeToday(id);
    }

    @GetMapping("/getFixturesByTeamIdAndDateFromToday")
    public List<MatchListDateDto> getFixturesByTeamIdAndDateFromToday( @RequestParam long id)
    {
        return fixtureRepository.findFixturesByTeamIdAndDateFromToday(id);

    }


}
