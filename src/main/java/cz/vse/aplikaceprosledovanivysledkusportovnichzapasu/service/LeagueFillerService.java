package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;


import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model.ApiSports;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.json.simple.JSONObject;

@Service
public class LeagueFillerService {

    @Autowired
    private LeagueRepository leagueRepository;
    ApiSports apiSports = ApiSports.getInstance();

    @Scheduled(fixedRate = 1000)
    public void fillLeagues() {


        System.out.println("filling leagues");
    }
}
