package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.TeamRespDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Team;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    UserService userService;

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
    @GetMapping(value="/getTeamInfoById")
    public ResponseEntity<TeamRespDto> getTeamById(@RequestParam Long id, HttpServletRequest request) {
        if(request.getHeader("Authorization") != null){
            String jwt = request.getHeader("Authorization").substring(7);
            User user = userService.getUserFromToken(jwt);
            return ResponseEntity.ok(teamService.getTeamInfoById(id, user));
        }
        return ResponseEntity.ok(teamService.getTeamInfoById(id, null));
    }
}
