package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.League;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/league")
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @PostMapping("/fillBasketballLeagues")
    public void fillBasketballLeagues() {
        leagueService.fillBasketballLeagues();
    }

    @PostMapping("/fillHockeyLeagues")
    public void fillHockeyLeagues() {
        leagueService.fillHockeyLeagues();
    }
    @GetMapping("/getLeagues")
    public List<League> getLeaguesBySport(@RequestParam String sport) {
        return leagueService.getLeaguesBySport(sport);
    }
}
