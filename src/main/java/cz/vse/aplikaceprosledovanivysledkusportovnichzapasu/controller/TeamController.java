package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.TeamService;

import java.util.List;

@Controller
@RequestMapping(value = "/team")
@CrossOrigin
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping("/fillTeamsBasketball")
    public void fillTeamsBasketball(@RequestParam int leagueExternalId, @RequestParam String season) {
        teamService.fillBasketballTeamsByLeagueExternalIdAndSeason(leagueExternalId, season);
    }

    @GetMapping("/getTeamsBySport")
    public List<Team> getTeamsBySport(@RequestParam String sport) {
        return teamService.getTeamsBySport(sport);
    }
}
