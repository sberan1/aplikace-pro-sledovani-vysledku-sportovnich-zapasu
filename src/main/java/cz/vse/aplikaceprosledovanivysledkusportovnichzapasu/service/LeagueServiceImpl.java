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
import java.util.*;
import java.util.stream.Collectors;

/**
 * Trieda LeagueServiceImpl - implementuje rozhranie LeagueService a poskytuje konkrétnu implementáciu metód pre prácu s ligami.
 *
 * @author Štěpán Beran, Zuzana Hadzimová, Sabína Hrabáriková, Julie Sanetrníková, Adam Škarvada
 * @version LS 2022/2023
 */

@Service @Primary
public class LeagueServiceImpl implements LeagueService{

    @Autowired
    private LeagueRepository leagueRepository;
    @Autowired
    private CountryRepository countryRepository;

    private ApiSports apiSports = ApiSports.getInstance();

    /**
     * Implementácia metódy z LeagueService pre vyplnenie údajov o lige pre basketbal pomocou API volania.
     */

    public void fillBasketballLeagues() {
        JSONObject parentFile = apiSports.basketbalLigy();
        JSONArray ligy = parentFile.getJSONArray("response");
        ligy.forEach(o -> pridatLigyBasketbal((JSONObject) o));
    }

    /**
     * Implementácia metódy z LeagueService pre vyplnenie údajov o lige pre hokej pomocou API volania.
     */

    public void fillHockeyLeagues() {
        JSONArray ligy = apiSports.hokejLigy().getJSONArray("response");
        pridatLigy(ligy, "Hockey");

    }

    /**
     * Metóda pre pridanie novel ligy do databáze.
     * @param ligy
     * @param sport
     */

    private void pridatLigy(JSONArray ligy, String sport) {
        ligy.forEach(o -> {
            League ligaEnt = new League();
            JSONObject liga = (JSONObject) o;
            if (leagueRepository.findByExternalIdandSport(liga.getInt("id"), sport).isEmpty()) {
                ligaEnt.setName(liga.getString("name"));
                ligaEnt.setType(liga.getString("type"));
                ligaEnt.setSport(sport);
                ligaEnt.setExternalId(liga.getInt("id"));
                ligaEnt.setLogo(liga.getString("logo"));
                ligaEnt.setCountry(countryRepository.findCountryByExternalIdAndSport(liga.getJSONObject("country").getInt("id"), ligaEnt.getSport()));
                leagueRepository.save(ligaEnt);
            }
        });
    }

    /**
     * Implementácia metódy z LeagueService pre vyplnenie údajov o lige pre fotbal pomocou API volania.
     */

    public void fillFootballLeagues() {
        JSONArray ligy = apiSports.fotbalLigy().getJSONArray("response");
        ligy.forEach(o -> {
            League ligaEnt = new League();
            JSONObject liga = (JSONObject) o;
            if (leagueRepository.findByExternalIdandSport(liga.getJSONObject("league").getInt("id"), "Football").isEmpty()){
                ligaEnt.setName(liga.getJSONObject("league").getString("name"));
                ligaEnt.setType(liga.getJSONObject("league").getString("type"));
                ligaEnt.setSport("Football");
                ligaEnt.setExternalId(liga.getJSONObject("league").getInt("id"));
                ligaEnt.setLogo(liga.getJSONObject("league").getString("logo"));
                ligaEnt.setCountry(countryRepository.findCountryByNameAndSport(liga.getJSONObject("country").getString("name"), ligaEnt.getSport()).get());
                leagueRepository.save(ligaEnt);
            }
        });
    }

    /**
     * Implementácia metódy z LeagueService pre vyplnenie údajov o lige pre volejbal pomocou API volania.
     */
    public void fillVolleyballLeagues(){
        JSONArray ligy = apiSports.volejbalLigy().getJSONArray("response");
        pridatLigy(ligy, "Volleyball");
    }

    /**
     * Metóda pre pridanie novej ligy basketbalu do databáze.
     * @param o
     */

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

    /**
     * Implementácia listu z LeagueService slúžiaceho na získanie všetkých líg.
     * @return List líg
     */
    public List<League> getLeagues() {
        return leagueRepository.findAll();
    }

    /**
     * Implementácia listu z LeagueService slúžiaceho na získanie líg na základe športu.
     * @param sport
     * @return
     */

    @Override
    public List<League> getLeaguesBySport(String sport) {
        return leagueRepository.findBySport(sport);
    }

    /**
     * Implementácia metódy z LeagueService pre získanie líg na základe dátumu a športu.
     * @param date
     * @param sport
     * @return List líg
     */

    public List<LeagueRespDto> getLeagueMatchesByDateAndSport(String date, String sport) {
        int [] datumString = Arrays.stream(date.split("-")).mapToInt(Integer::parseInt).toArray();
        Set<League> ligy = new HashSet<>(leagueRepository.findAllByDateAndSport(LocalDateTime.of(datumString[0], datumString[1], datumString[2], 0, 0), LocalDateTime.of(datumString[0], datumString[1], datumString[2], 0, 0).plusDays(1), sport));
        Set<LeagueRespDto> ligyDto = new HashSet<>();
        for (League liga : ligy){
            LeagueRespDto ligaDto = LeagueRespDto.builder()
                    .id(liga.getId())
                    .name(liga.getName())
                    .flag(liga.getCountry().getFlag())
                    .build();
            ligyDto.add(ligaDto);
        }
        return ligyDto.stream().toList();
    }



}

