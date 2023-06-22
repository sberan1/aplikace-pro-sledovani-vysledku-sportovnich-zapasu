package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.LeagueRespDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.League;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Trieda LeagueController - slúži pre manipuláciu s databázou obsahujúcou informácie o jednotlivých ligách.
 *
 * @author Štepán Beran, Zuzana Hadzimová, Sabína Hrabáriková, Adam Škarvada
 * @version LS 2022/2023
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/league")
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    /**
     * Metóda pre naplnenie databáze ligami pri športe basketbal.
     */

    @PostMapping("/fillBasketballLeagues")
    public void fillBasketballLeagues() {
        leagueService.fillBasketballLeagues();
    }

    /**
     * Metóda pre naplnenie databáze ligami pri športe hokej.
     */

    @PostMapping("/fillHockeyLeagues")
    public void fillHockeyLeagues() {
        leagueService.fillHockeyLeagues();
    }

    /**
     * Metóda pre naplnenie databáze ligami pri športe futbal.
     * @return List s informáciami o ligách.
     */

    @PostMapping("/fillFootballLeagues")
    public ResponseEntity<List<League>> fillFootballLeagues() { leagueService.fillFootballLeagues(); return ResponseEntity.ok(leagueService.getLeaguesBySport("football"));}

    /**
     * Metóda pre naplnenie databáze ligami pri športe volejbal.
     */

    @PostMapping("/fillVolleyballLeagues")
    public void fillVolleyballLeagues(){
        leagueService.fillVolleyballLeagues();
    }

    /**
     * Metóda pre získanie líg podľa športu.
     * @param sport
     * @return List s informáciami o ligách.
     */

    @GetMapping("/getLeagues")
    public List<League> getLeaguesBySport(@RequestParam String sport) {
        return leagueService.getLeaguesBySport(sport);
    }

    /**
     * Metóda pre získanie informácí o ligáach.
     * @param date
     * @param sport
     * @return List s informáciami o ligách.
     */

   @GetMapping(value = "/getLeaguesByFixturePlayedAtDateInSport")
    public List<LeagueRespDto> getLeaguesByFixturePlayedAtDateInSport(@RequestParam String date, @RequestParam String sport) {
        return leagueService.getLeagueMatchesByDateAndSport(date, sport);
    }
}
