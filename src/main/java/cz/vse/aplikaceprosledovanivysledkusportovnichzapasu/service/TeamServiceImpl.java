package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.SearchBarDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.TeamRespDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Team;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model.ApiSports;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.CountryRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.LeagueRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.TeamRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * Trieda TeamServiceImpl - implementuje rozhranie TeamService a poskytuje konkrétnu implementáciu metód pre prácu s týmami.
 *
 * @author Štěpán Beran, Zuzana Hadzimová, Sabína Hrabáriková, Julie Sanetrníková, Adam Škarvada
 * @version LS 2022/2023
 */

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    LeagueRepository leagueRepository;
    ApiSports apiSports = ApiSports.getInstance();

    User user;

    /**
     * Implementácia metódy z TeamService pre plnenie týmov pre basketbal na základe Id ligy a Id sezóny a  pomocou API volania.
     * @param leagueExternalId
     * @param seasonExternalId
     */
    @Override
    public void fillBasketballTeamsByLeagueExternalIdAndSeason(int leagueExternalId, String seasonExternalId) {
        JSONObject resp = apiSports.basketbalTymy(leagueExternalId, seasonExternalId);
        JSONArray teams = resp.getJSONArray("response");
        teams.forEach(o -> pridatTymy((JSONObject) o, "Basketball", resp));
    }

    /**
     * Implementácia metódy z TeamService pre plnenie týmov pre hokej na základe Id ligy a Id sezóny a  pomocou API volania.
     * @param leagueExternalId
     * @param seasonExternalId
     */

    @Override
    public void fillHockeyTeamsByLeagueExternalIdAndSeason(int leagueExternalId, String seasonExternalId) {
        JSONObject resp = apiSports.hokejTymy(leagueExternalId, seasonExternalId);
        JSONArray teams = resp.getJSONArray("response");
        teams.forEach(o -> pridatTymy((JSONObject) o, "Hockey", resp));
    }

    /**
     * Implementácia metódy z TeamService pre plnenie týmov pre futbal na základe Id ligy a Id sezóny a  pomocou API volania.
     * @param leagueExternalId
     * @param seasonExternalId
     */

    @Override
    public void fillFootballTeamsByLeagueExternalIdAndSeason (int leagueExternalId, String seasonExternalId){
        JSONObject resp = apiSports.fotbalTymy(leagueExternalId, seasonExternalId);
        JSONArray teams = resp.getJSONArray("response");
        teams.forEach(n ->{
            JSONObject o = ((JSONObject)n).getJSONObject("team");
            Team tymEnt;
            if (teamRepository.findByExternalIdAndSport(o.getInt("id"), "Football").isPresent()){
                tymEnt = teamRepository.findTeamByExternalIdAndSport(o.getInt("id"), "Football");
            }
            else {
                tymEnt = new Team();
            }
            tymEnt.setExternalId(o.getInt("id"));
            tymEnt.setSport("Football");
            tymEnt.setName(o.getString("name"));
            tymEnt.setLogo(o.getString("logo"));
            tymEnt.getLeagues().add(leagueRepository.findLeagueByExternalIdAndSport(Integer.parseInt(resp.getJSONObject("parameters").getString("league")),"Football"));
                tymEnt.setCountry(countryRepository.findCountryByNameAndSport(o.getString("country"),"Football").get());
                teamRepository.save(tymEnt);


        } );
    }

    /**
     * Implementácia metódy z TeamService pre plnenie týmov pre volejbal na základe Id ligy a Id sezóny a  pomocou API volania.
     * @param leagueExternalId
     * @param seasonExternalId
     */

    @Override
    public void fillVolleyballTeamsByLeagueExternalIdAndSeason(int leagueExternalId, String seasonExternalId) {
        JSONObject resp = apiSports.volejbalTymy(leagueExternalId, seasonExternalId);
        JSONArray teams = resp.getJSONArray("response");
        teams.forEach(o -> pridatTymy((JSONObject) o, "Volleyball", resp));
    }

    /**
     * Implementácia listu z TeamService slúžiaceho na získanie týmov daného športu.
     * @param sport
     * @return list týmov
     */
    @Override
    public List<Team> getTeamsBySport(String sport) {
        return teamRepository.findTeamsBySport(sport);
    }

    /**
     * Implementácia metódy z TeamService pre získanie informácií o týme podľa identifikačného čísla a používateľa.
     * @param id
     * @param user
     * @return informácie o týme alebo null
     */
    @Override
    public TeamRespDto getTeamInfoById(long id, User user) {
        Team team = teamRepository.findTeamById(id);
        TeamRespDto teamDto = TeamRespDto.builder()
                .id(team.getId())
                .name(team.getName())
                .sport(team.getSport())
                .teamLogo(team.getLogo())
                .country(team.getCountry().getName())
                .countryLogo(team.getCountry().getFlag())
                .build();
                if (user == null){
                    teamDto.setFavourite(false);
                }
                else {
                    for (var t : user.getFavouriteTeams()){
                        if (t.getId() == id){
                            teamDto.setFavourite(true);
                            break;
                        }
                        teamDto.setFavourite(false);
                    }

                }
        return teamDto;
    }

    /**
     * Metóda pre pridanie nového týmu do databáze.
     * @param o
     * @param sport
     * @param resp
     */
    @Override
    public List<SearchBarDto> searchBar(String name) {
        List<Team> tymy = teamRepository.findTeamsByNameContains(name);
        List<SearchBarDto> vysledek = new ArrayList<>();
        for (var t : tymy) {
            SearchBarDto tym = SearchBarDto.builder()
                    .id(t.getId())
                    .name(t.getName())
                    .logo(t.getLogo())
                    .build();
            vysledek.add(tym);
        }
        return vysledek;
    }

    private void pridatTymy(JSONObject o, String sport, JSONObject resp) {
        Team tymEnt;
        if (teamRepository.findByExternalIdAndSport(o.getInt("id"), sport).isPresent()){
            tymEnt = teamRepository.findTeamByExternalIdAndSport(o.getInt("id"), sport);
        }
        else {
            tymEnt = new Team();
        }

        tymEnt.setExternalId(o.getInt("id"));
            tymEnt.setName(o.getString("name"));
            tymEnt.setLogo(o.getString("logo"));
            tymEnt.setSport(sport);
            tymEnt.getLeagues().add(leagueRepository.findLeagueByExternalIdAndSport(resp.getJSONObject("parameters").getInt("league"), sport));
            if (o.getJSONObject("country").get("id") != JSONObject.NULL){
                tymEnt.setCountry(countryRepository.findCountryByExternalIdAndSport(o.getJSONObject("country").getInt("id"), sport));
                teamRepository.save(tymEnt);
            }
            else {
                tymEnt.setCountry(countryRepository.findCountryByFlagAndSport(o.getJSONObject("country").getString("flag"), sport));
                teamRepository.save(tymEnt);
            }


    }
}
