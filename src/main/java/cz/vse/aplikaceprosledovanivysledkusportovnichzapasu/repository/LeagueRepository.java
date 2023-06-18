package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.ContentHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<ContentHolder, Long>{

    @Query("""
    SELECT DISTINCT l
    FROM ContentHolder l
    INNER JOIN Fixture f ON f.league = l
    WHERE f.sport = :sport
      AND f.date >= :startDate
      AND f.date < :endDate
    """)
    List<ContentHolder> findAllByDateAndSport(LocalDateTime startDate, LocalDateTime endDate, String sport);

    List<ContentHolder> findBySport(String sport);
    @Query("select l from ContentHolder l where l.externalId = ?1 and l.sport = ?2")
    Optional<ContentHolder> findByExternalIdandSport(int externalId, String sport);
    ContentHolder findLeagueByExternalIdAndSport(int anInt, String sport);

}
