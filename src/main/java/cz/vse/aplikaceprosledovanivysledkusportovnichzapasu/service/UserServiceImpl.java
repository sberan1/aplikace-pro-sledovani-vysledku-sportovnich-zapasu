package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.MatchListDateDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.SearchBarDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Fixture;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto.ChangePasswordDto;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Fixture;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.League;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Team;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model.OpenAI;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.FixtureRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Trieda UserServiceImpl - implementuje rozhranie UserServiceImpl a poskytuje konkrétnu implementáciu metód pre operácie súvisiace s používateľom.
 *
 * @author Štepán Beran, Zuzana Hadzimová, Sabína Hrabáriková
 * @version LS 2022/2023
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private FixtureRepository fixtureRepository;
    /**
     * Implementácia metódy z UserService pre uloženie používateľa do systému.
     * @param user
     * @return uložený používateľ alebo null
     */
    @Autowired
    private TeamService teamService;

    @Override
    public User saveUser(User user) {
        if(user.getEmail() == null || user.getPassword() == null || user.getName() == null || user.getSurname() == null) {
            return null;
        }
        for (User u : userRepository.findAll()) {
            if (u.getEmail().equals(user.getEmail())) {
                return null;
            }
        }
        return userRepository.save(user);
    }

    /**
     * Implementácia listu z UserService slúžiaceho na získanie všetkých používateľov.
     * @return List všetkých používateľov
     */

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Implementácia metódy z UserService pre získanie používateľa podľa identifikačného čísla.
     * @param id
     * @return user alebo vyhodenie výnimky
     */

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    /**
     * Implementácia metódy z UserService pre overenie prítomnosti emailu.
     * @param email
     * @return true alebo false
     */

    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    /**
     * Implementácia metódy z UserService pre získanie textového reťazca s obľúbenými týmami.
     * @param user
     * @return zoznam obľúbených týmov
     */

    @Override
    public String getTextOfFavTeams(User user) {
        StringBuilder sb = new StringBuilder();
        for (Team f : user.getFavouriteTeams()) {
            sb.append(f.getName());
            sb.append(", ");
        }
        return sb.toString();
    }

    /**
     * Implementácia metódy z UserService pre získanie užívateľa na základe tokena.
     * @param jwt
     * @return user
     */

    @Override
    public User getUserFromToken(String jwt){
        String email = jwtService.extractEmail(jwt);
        return userRepository.findByEmail(email).get();
    }

    /**
     * Implementácia metódy z UserService pre pridanie týmu medzi oblúbené na základe teamId a tokenu.
     * @param teamId
     * @param jwt
     */

    @Override
    public void addFavouriteTeam(long teamId, String jwt) {
        User user= getUserFromToken(jwt);
        user.getFavouriteTeams().add(teamRepository.findById(teamId).get());
        userRepository.save(user);
    }

    /**
     * Implementácia metódy z UserService pre odstránenie týmu z oblúbených na základe teamId a tokenu.
     * @param teamId
     * @param jwt
     */

    @Override
    public void removeFavouriteTeam(long teamId, String jwt) {
        User user= getUserFromToken(jwt);
        user.getFavouriteTeams().remove(teamRepository.findById(teamId).get());
        userRepository.save(user);
    }

    /**
     * Implementácia metódy z UserService pre získanie obľúbených týmov.
     * @param jwt
     * @return obľúbené tímy
     */

    @Override
    public Set<SearchBarDto> getFavouriteTeams(String jwt) {
        User user= getUserFromToken(jwt);
        Set<SearchBarDto> formatovaneTymy = new HashSet<>();
        for (Team team : user.getFavouriteTeams()) {
            SearchBarDto searchBarDTO = new SearchBarDto();
            searchBarDTO.setId(team.getId());
            searchBarDTO.setName(team.getName());
            searchBarDTO.setLogo(team.getLogo());
            formatovaneTymy.add(searchBarDTO);
        }
        return formatovaneTymy;
    }

    /**
     * Implementácia metódy z UserService pre získanie obľúbených zápasov.
     * @param jwt
     * @return obľúbené zápasy
     */

    @Override
    public Set<MatchListDateDto> getFavouriteFixtures(String jwt) {
        User user= getUserFromToken(jwt);
        Set<MatchListDateDto> formatovaneZapasy = new HashSet<>();
        for (Fixture fixture : user.getFavouriteFixtures()) {
            MatchListDateDto matchListDateDTO = new MatchListDateDto();
            matchListDateDTO.setId(fixture.getId());
            matchListDateDTO.setDate(fixture.getDate().format(DateTimeFormatter.ISO_DATE));
            matchListDateDTO.setTime(fixture.getDate().format(DateTimeFormatter.ofPattern("HH:mm")));
            matchListDateDTO.setHomeTeamId(fixture.getHomeTeam().getId());
            matchListDateDTO.setHomeTeam(fixture.getHomeTeam().getName());
            matchListDateDTO.setAwayTeamId(fixture.getAwayTeam().getId());
            matchListDateDTO.setAwayTeam(fixture.getAwayTeam().getName());
            matchListDateDTO.setHomeTeamScore(fixture.getScore().getFinalHomeScore());
            matchListDateDTO.setAwayTeamScore(fixture.getScore().getFinalAwayScore());
            matchListDateDTO.setHomeTeamLogo(fixture.getHomeTeam().getLogo());
            matchListDateDTO.setAwayTeamLogo(fixture.getAwayTeam().getLogo());
            formatovaneZapasy.add(matchListDateDTO);
        }
        return formatovaneZapasy;
    }

    /**
     * Metóda pre zmazanie používateľa na základe identifikačného čísla.
     * @param id
     * @return user
     */

    @Override
    public User deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
        return user;
    }

    /**
     * Implementácia metódy z UserService pre pridanie oblúbeného zápasu.
     * @param id
     * @param jwt
     */

    @Override
    public void addFavouriteFixture(long id, String jwt) {
        User user= getUserFromToken(jwt);
        Fixture fixture = fixtureRepository.findFixtureById(id);
        user.getFavouriteFixtures().add(fixture);
        userRepository.save(user);
    }

    /**
     * Implementácia metódy z UserService pre odstránenie oblúbeného zápasu.
     * @param id
     * @param jwt
     */

    @Override
    public void removeFavouriteFixture(long id, String jwt) {
        User user= getUserFromToken(jwt);
        user.getFavouriteFixtures().remove(fixtureRepository.findFixtureById(id));
        userRepository.save(user);
    }

    @Override
    public List<SearchBarDto> callFavouriteTeamsOpenAi(String jwt) {
        String[] tymy = OpenAI.useMessages(getUserFromToken(jwt)).split(",.");
        List<SearchBarDto> value = new ArrayList<>();
        for (String tym : tymy) {
            value.addAll(teamService.searchBar(tym));
        }
    return value.stream().limit(6).collect(Collectors.toList());
    }


}
