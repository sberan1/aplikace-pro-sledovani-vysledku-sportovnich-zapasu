package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository;


import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Dependency Injection Interface BasketballScoreRepository - rozširuje JpaRepository a slúži k manipulácií s databázou pre entitu Fixture.
 *
 * @author Štepán Beran, Sabína Hrabáriková
 * @version LS 2022/2023
 */


@Repository
public interface FixtureRepository extends JpaRepository<Fixture, Long> {
    List<Fixture> findByDateBetweenAndSport(LocalDateTime dateStart, LocalDateTime dateEnd, String sport);
    @Query("SELECT e FROM Fixture e WHERE e.date >= :startDate AND e.date < :endDate AND e.sport = :sport AND e.league.id = :leagueId AND e.league.sport = :sport")
    List<Fixture> findAllByDateAndSport(LocalDateTime startDate, LocalDateTime endDate, String sport, long leagueId);

    Optional<Object> findByExternalIdAndSport(int id, String sport);

    Fixture findFixtureByExternalIdAndSport(int id, String sport);

    @Override
    Optional<Fixture> findById(Long id);


    @Query("SELECT e FROM Fixture e WHERE (e.awayTeam.id = :teamId OR e.homeTeam.id = :teamId) AND e.date < CURRENT_DATE ORDER BY e.date DESC")
    List<Fixture> findFixturesByTeamIdAndDateBeforeToday(long teamId);

    @Query("SELECT e FROM Fixture e WHERE (e.awayTeam.id = :teamId OR e.homeTeam.id = :teamId) AND e.date >= CURRENT_DATE ORDER BY e.date ASC")
    List<Fixture> findFixturesByTeamIdAndDateFromToday(long teamId);

    Fixture findFixtureById(long id);
}

