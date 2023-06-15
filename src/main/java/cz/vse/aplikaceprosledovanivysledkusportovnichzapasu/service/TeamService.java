package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Team;

import java.util.List;

public interface TeamService {
    void fillBasketballTeamsByLeagueExternalIdAndSeason(int leagueExternalId, String seasonExternalId);

    void fillHockeyTeamsByLeagueExternalIdAndSeason(int leagueExternalId, String seasonExternalId);
    List<Team> getTeamsBySport(String sport);

}
