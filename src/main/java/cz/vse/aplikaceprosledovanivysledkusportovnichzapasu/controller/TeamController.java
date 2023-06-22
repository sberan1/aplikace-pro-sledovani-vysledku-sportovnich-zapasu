package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.SearchBarDto;
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

/**
 * Trieda TeamController - slúži pre manipuláciu s databázou obsahujúcou informácie o jednotlivých tímoch.
 *
 * @author Štepán Beran, Zuzana Hadzimová
 * @version LS 2022/2023
 */

@Controller
@RequestMapping(value = "/team")
@CrossOrigin
public class TeamController {
    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    /**
     * Metóda pre naplnenie databáze týmami pri športe basketbal.
     * @param leagueExternalId
     * @param season
     */


    @PostMapping(value="/fillTeamsBasketball")
    public void fillTeamsBasketball(@RequestParam int leagueExternalId, @RequestParam String season) {
        teamService.fillBasketballTeamsByLeagueExternalIdAndSeason(leagueExternalId, season);
    }

    /**
     * Metóda pre naplnenie databáze týmami pri športe hokej.
     * @param leagueExternalId
     * @param season
     */

    @PostMapping(value= "/fillTeamsHockey")
    public void fillTeamsHockey(@RequestParam int leagueExternalId, @RequestParam String season) {
         teamService.fillHockeyTeamsByLeagueExternalIdAndSeason(leagueExternalId, season);
    }

    /**
     * Metóda pre získanie týmov podľa športu.
     * @param sport
     * @return List s informáciami o týmoch.
     */

    @GetMapping(value="/getTeamsBySport")
    public ResponseEntity<List<Team>> getTeamsBySport(@RequestParam String sport) {
        return ResponseEntity.ok(teamService.getTeamsBySport(sport));
    }

    /**
     * Metóda pre získanie informácií o týme na základe identifikačného čisla a užívateľa.
     * @param id
     * @param request
     * @return informácie o týme alebo null.
     */

    @GetMapping(value="/getTeamInfoById")
    public ResponseEntity<TeamRespDto> getTeamById(@RequestParam Long id, HttpServletRequest request) {
        if(request.getHeader("Authorization") != null){
            String jwt = request.getHeader("Authorization").substring(7);
            User user = userService.getUserFromToken(jwt);
            return ResponseEntity.ok(teamService.getTeamInfoById(id, user));
        }
        return ResponseEntity.ok(teamService.getTeamInfoById(id, null));
    }

    @GetMapping(value = "/search")
    public  ResponseEntity<List<SearchBarDto>> searchBar(@RequestParam String name){
        return ResponseEntity.ok(teamService.searchBar(name));
    }
}
