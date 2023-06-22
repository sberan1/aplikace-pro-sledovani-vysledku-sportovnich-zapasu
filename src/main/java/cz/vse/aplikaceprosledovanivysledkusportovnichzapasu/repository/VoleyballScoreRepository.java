package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.VoleyballScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dependency Injection Interface BasketballScoreRepository - rozširuje JpaRepository a slúži k manipulácií s databázou pre entitu VolleyballScore.
 *
 * @author Štepán Beran
 * @version LS 2022/2023
 */

@Repository
public interface VoleyballScoreRepository extends JpaRepository<VoleyballScore, Long> {
}
