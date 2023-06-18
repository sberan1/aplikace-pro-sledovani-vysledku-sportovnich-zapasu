package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value="/fillTeamsBasketball")
    public void fillTeamsBasketball(@RequestParam int leagueExternalId, @RequestParam String season) {
        teamService.fillBasketballTeamsByLeagueExternalIdAndSeason(leagueExternalId, season);
    }
    @PostMapping(value= "/fillTeamsHockey")
    public void fillTeamsHockey(@RequestParam int leagueExternalId, @RequestParam String season) {
         teamService.fillHockeyTeamsByLeagueExternalIdAndSeason(leagueExternalId, season);
    }
    @GetMapping(value="/getTeamsBySport")
    public ResponseEntity<List<Team>> getTeamsBySport(@RequestParam String sport) {
        return ResponseEntity.ok(teamService.getTeamsBySport(sport));
    }
}
