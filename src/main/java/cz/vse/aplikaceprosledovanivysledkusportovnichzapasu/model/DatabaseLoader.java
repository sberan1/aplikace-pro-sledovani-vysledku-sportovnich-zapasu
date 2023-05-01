package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.*;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.MatchRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.TeamRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader {

        private final UserRepository users;
        private final MatchRepository matches;
        private final TeamRepository teams;

        public DatabaseLoader(UserRepository users, MatchRepository matches, TeamRepository teams) {
            this.users = users;
            this.matches = matches;
            this.teams = teams;
        }

        public void init() {
            User user = new User();
            user.setUsername("user");
            user.setPassword("password");
            users.save(user);

            Team team = new Team();
            team.setName("team");
            teams.save(team);

            Match match = new Match();
            match.setHomeTeam(team);
            match.setAwayTeam(team);
            match.setHomeTeamScore(1);
            match.setAwayTeamScore(2);
            matches.save(match);
        }
}
