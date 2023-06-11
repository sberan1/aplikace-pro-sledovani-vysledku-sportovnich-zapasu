package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.TeamService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping("/fillTeamsBasketball")
    public void fillTeamsBasketball(@RequestParam int leagueExternalId, @RequestParam String season) {
        teamService.fillBasketballTeamsByLeagueExternalIdAndSeason(leagueExternalId, season);
    }
}
