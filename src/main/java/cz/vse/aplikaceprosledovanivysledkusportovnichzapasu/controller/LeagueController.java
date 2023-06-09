package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.League;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/league")
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @PostMapping("/fillBasketballLeagues")
    public void fillBasketballLeagues() {
        leagueService.fillBasketballLeagues();
    }

    @GetMapping("/getLeagues")
    public List<League> getLeagues() {
        return leagueService.getLeagues();
    }
}
