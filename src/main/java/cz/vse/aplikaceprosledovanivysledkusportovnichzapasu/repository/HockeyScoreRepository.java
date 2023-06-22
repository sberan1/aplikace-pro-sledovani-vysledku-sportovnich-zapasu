package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.HockeyScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dependency Injection Interface BasketballScoreRepository - rozširuje JpaRepository a slúži k manipulácií s databázou pre entitu HockeyScore.
 *
 * @author Štepán Beran
 * @version LS 2022/2023
 */

@Repository
public interface HockeyScoreRepository extends JpaRepository<HockeyScore, Long> {
}
