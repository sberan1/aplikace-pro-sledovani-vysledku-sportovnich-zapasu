package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.SearchBarDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.TeamRespDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Team;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;

import java.util.List;

public interface TeamService {
    void fillBasketballTeamsByLeagueExternalIdAndSeason(int leagueExternalId, String seasonExternalId);

    void fillHockeyTeamsByLeagueExternalIdAndSeason(int leagueExternalId, String seasonExternalId);

    void fillFootballTeamsByLeagueExternalIdAndSeason (int leagueExternalId, String seasonExternalId);

    void fillVolleyballTeamsByLeagueExternalIdAndSeason (int leagueExternalId, String seasonExternalId);

    List<Team> getTeamsBySport(String sport);

    TeamRespDto getTeamInfoById(long id, User user);

    List<SearchBarDto> searchBar(String name);

}
