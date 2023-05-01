package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.BasketballScore;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.VoleyballScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketballScoreRepository extends JpaRepository<BasketballScore, Long> {
}
