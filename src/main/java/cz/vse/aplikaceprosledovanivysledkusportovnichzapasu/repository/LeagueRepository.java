package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Dependency Injection Interface BasketballScoreRepository - rozširuje JpaRepository a slúži k manipulácií s databázou pre entitu League.
 *
 * @author Štěpán Beran, Zuzana Hadzimová, Sabína Hrabáriková, Julie Sanetrníková, Adam Škarvada
 * @version LS 2022/2023
 */

@Repository
public interface LeagueRepository extends JpaRepository<League, Long>{
    @Query("select l from League l where l.name like concat('%', ?1, '%')")
    List<League> findByNameContains(String name);

    @Query("""
    SELECT DISTINCT l
    FROM League l
    INNER JOIN Fixture f ON f.league = l
    WHERE f.sport = :sport
      AND f.date >= :startDate
      AND f.date < :endDate
    """)
    List<League> findAllByDateAndSport(LocalDateTime startDate, LocalDateTime endDate, String sport);

    List<League> findBySport(String sport);
    @Query("select l from League l where l.externalId = ?1 and l.sport = ?2")
    Optional<League> findByExternalIdandSport(int externalId, String sport);
    League findLeagueByExternalIdAndSport(int anInt, String sport);

}
