package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller.LeagueController;
import org.junit.jupiter.api.Test;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LeagueTest {


    @Autowired
    private LeagueService leagueFillerService;

    @Test
    public void fillBasketballLeaguesTest() {
        leagueFillerService.fillBasketballLeagues();
    }
}
