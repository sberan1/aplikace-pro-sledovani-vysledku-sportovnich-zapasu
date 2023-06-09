package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;


import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Country;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.League;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model.ApiSports;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.CountryRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.LeagueRepository;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService{

    @Autowired
    private LeagueRepository leagueRepository;
    @Autowired
    private CountryRepository countryRepository;
    private ApiSports apiSports = ApiSports.getInstance();


    public void fillBasketballLeagues() {
        JSONObject parentFile = apiSports.basketbalLigy();
        JSONArray ligy = parentFile.getJSONArray("response");
        ligy.forEach(o -> {
            pridatLigy((JSONObject) o);
        });
        }

    private void pridatLigy(JSONObject o) {
        League ligaEnt = new League();
        ligaEnt.setType(o.getString("type"));
        ligaEnt.setExternalId(o.getInt("id"));
        ligaEnt.setName(o.getString("name"));
        ligaEnt.setSport("Basketball");
        ligaEnt.setLogo(o.getString("logo"));
        ligaEnt.setCountry(countryRepository.findByExternalId(o.getJSONObject("country").getInt("id")));

        if(!leagueRepository.findByExternalIdandSport(o.getInt("id"), "Basketball").isPresent()){
            leagueRepository.save(ligaEnt);
        }

    }

    public List<League> getLeagues() {
        return leagueRepository.findAll();
    }


}

