package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.SoccerScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoccerScoreRepository extends JpaRepository<SoccerScore, Long> {
}
