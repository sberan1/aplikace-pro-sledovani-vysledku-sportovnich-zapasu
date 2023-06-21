package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t FROM Team t WHERE t.externalId = ?1 AND t.sport = ?2")
    Optional<Team> findByExternalIdAndSport(int externalId, String sport);
    List<Team> findTeamsBySport(String sport);
    Team findTeamByExternalIdAndSport(int anInt, String sport);

    Team findTeamById(long id);

    Optional<Object> findByExternalIdAndSportAndName(int anInt, String sport, String name);
}
