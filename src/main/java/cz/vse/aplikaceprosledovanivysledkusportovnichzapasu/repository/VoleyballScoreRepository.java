package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.VoleyballScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoleyballScoreRepository extends JpaRepository<VoleyballScore, Long> {
}
