package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.HockeyScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HockeyScoreRepository extends JpaRepository<HockeyScore, Long> {
}
