package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long>{
    List<League> findBySport(String sport);
    @Query("select l from League l where l.externalId = ?1 and l.sport = ?2")
    Optional<League> findByExternalIdandSport(int externalId, String sport);
    League findLeagueByExternalIdAndSport(int anInt, String sport);
}
