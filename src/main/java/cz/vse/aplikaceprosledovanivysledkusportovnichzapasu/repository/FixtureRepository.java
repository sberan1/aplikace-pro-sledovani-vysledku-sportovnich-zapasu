package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FixtureRepository extends JpaRepository<Fixture, Long> {
    @Query("SELECT e FROM Fixture e WHERE e.date >= :startDate AND e.date < :endDate AND e.sport = :sport")
    List<Fixture> findAllByDateAndSport(LocalDateTime startDate, LocalDateTime endDate, String sport);

    Optional<Object> findByExternalIdAndSport(int id, String sport);

    Fixture findFixtureByExternalIdAndSport(int id, String sport);
}
