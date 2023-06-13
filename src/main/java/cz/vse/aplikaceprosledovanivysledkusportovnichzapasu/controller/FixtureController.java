package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.MatchListDateDTO;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/fixture")
@CrossOrigin
public class FixtureController {
    @Autowired
    FixtureService fixtureService;

    @PostMapping(value = "/fillFixturesBasketball")
    public void fillTeamsBasketball(@RequestParam int leagueExternalId, @RequestParam String season) {
        fixtureService.fillBasketballFixture(leagueExternalId, season);
    }

    @GetMapping("/getFixturesBySportAndDate")
    public List<MatchListDateDTO> getFixturesBySportAndDate(@RequestParam String sport, @RequestParam String date)
    {
        return fixtureService.getFixturesBySportAndDate(sport, date);
    }
}
