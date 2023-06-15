package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;


import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.LeagueRespDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Fixture;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.League;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model.ApiSports;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.CountryRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.FixtureRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.LeagueRepository;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service @Primary
public class LeagueServiceImpl implements LeagueService{

    @Autowired
    private LeagueRepository leagueRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private FixtureRepository fixtureRepository;

    private ApiSports apiSports = ApiSports.getInstance();


    public void fillBasketballLeagues() {
        JSONObject parentFile = apiSports.basketbalLigy();
        JSONArray ligy = parentFile.getJSONArray("response");
        ligy.forEach(o -> pridatLigyBasketbal((JSONObject) o));
    }

    public void fillHockeyLeagues() {
        JSONArray ligy = apiSports.hokejLigy().getJSONArray("response");
        ligy.forEach(o -> {
            League ligaEnt = new League();
            JSONObject liga = (JSONObject) o;
            if (leagueRepository.findByExternalIdandSport(liga.getInt("id"), "Hockey").isEmpty()) {
                ligaEnt.setName(liga.getString("name"));
                ligaEnt.setType(liga.getString("type"));
                ligaEnt.setSport("Hockey");
                ligaEnt.setExternalId(liga.getInt("id"));
                ligaEnt.setLogo(liga.getString("logo"));
                ligaEnt.setCountry(countryRepository.findCountryByExternalIdAndSport(liga.getJSONObject("country").getInt("id"), ligaEnt.getSport()));
                leagueRepository.save(ligaEnt);
            }
        });

    }

    private void pridatLigyBasketbal(JSONObject o) {
        League ligaEnt = new League();
        ligaEnt.setType(o.getString("type"));
        ligaEnt.setExternalId(o.getInt("id"));
        ligaEnt.setName(o.getString("name"));
        ligaEnt.setSport("Basketball");
        ligaEnt.setLogo(o.getString("logo"));
        ligaEnt.setCountry(countryRepository.findByExternalId(o.getJSONObject("country").getInt("id")));

        if(leagueRepository.findByExternalIdandSport(o.getInt("id"), "Basketball").isEmpty()){
            leagueRepository.save(ligaEnt);
        }

    }

    public List<League> getLeagues() {
        return leagueRepository.findAll();
    }

    @Override
    public List<League> getLeaguesBySport(String sport) {
        return leagueRepository.findBySport(sport);
    }

    public List<LeagueRespDto> getLeagueMatchesByDateAndSport(String date, String sport) {
        int [] datumString = Arrays.stream(date.split("-")).mapToInt(Integer::parseInt).toArray();
        Set<League> ligy = new HashSet<>(leagueRepository.findAllByDateAndSport(LocalDateTime.of(datumString[0], datumString[1], datumString[2], 0, 0), LocalDateTime.of(datumString[0], datumString[1], datumString[2], 0, 0).plusDays(1), sport));
        Set<LeagueRespDto> ligyDto = new HashSet<>();
        for (League liga : ligy){
            LeagueRespDto ligaDto = new LeagueRespDto();
            ligaDto.builder()
                    .id(liga.getId())
                    .name(liga.getName())
                    .flag(liga.getCountry().getFlag())
                    .build();
            ligyDto.add(ligaDto);
        }
        return ligyDto.stream().toList();
    }


}

