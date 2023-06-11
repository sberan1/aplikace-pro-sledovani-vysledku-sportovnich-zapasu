package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Team;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model.ApiSports;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.CountryRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.LeagueRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.TeamRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    LeagueRepository leagueRepository;
    ApiSports apiSports = ApiSports.getInstance();

    @Override
    public void fillBasketballTeamsByLeagueExternalIdAndSeason(int leagueExternalId, String seasonExternalId) {
        JSONObject resp = apiSports.basketbalTymy(leagueExternalId, seasonExternalId);
        JSONArray teams = resp.getJSONArray("response");
        teams.forEach(o -> pridatTymy((JSONObject) o, "Basketball", resp));
    }

    private void pridatTymy(JSONObject o, String sport, JSONObject resp) {
            Team tymEnt = new Team();
            tymEnt.setExternalId(o.getInt("id"));
            tymEnt.setName(o.getString("name"));
            tymEnt.setLogo(o.getString("logo"));
            tymEnt.setSport(sport);
            tymEnt.getLeagues().add(leagueRepository.findLeagueByExternalIdAndSport(resp.getJSONObject("parameters").getInt("league"), sport));
            if (o.getJSONObject("country").get("id") != JSONObject.NULL){
                tymEnt.setCountry(countryRepository.findCountryByExternalIdAndSport(o.getJSONObject("country").getInt("id"), sport));
            }
            else {
                tymEnt.setCountry(countryRepository.findCountryByFlagAndSport(o.getJSONObject("country").getString("flag"), sport));
            }

            teamRepository.save(tymEnt);
    }
}
