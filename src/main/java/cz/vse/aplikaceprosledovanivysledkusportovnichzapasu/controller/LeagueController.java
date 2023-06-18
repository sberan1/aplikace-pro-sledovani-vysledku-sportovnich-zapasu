package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.LeagueRespDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.ContentHolder;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/fillFootballLeagues")
    public ResponseEntity<List<League>> fillFootballLeagues() { leagueService.fillFootballLeagues(); return ResponseEntity.ok(leagueService.getLeaguesBySport("football"));}

    @PostMapping("/fillVolleyballLeagues")
    public void fillVolleyballLeagues(){
        leagueService.fillVolleyballLeagues();
    }

    @GetMapping("/getLeagues")
    public List<ContentHolder> getLeaguesBySport(@RequestParam String sport) {
        return leagueService.getLeaguesBySport(sport);
    }

   @GetMapping(value = "/getLeaguesByFixturePlayedAtDateInSport")
    public List<LeagueRespDto> getLeaguesByFixturePlayedAtDateInSport(@RequestParam String date, @RequestParam String sport) {
        return leagueService.getLeagueMatchesByDateAndSport(date, sport);
    }
}
