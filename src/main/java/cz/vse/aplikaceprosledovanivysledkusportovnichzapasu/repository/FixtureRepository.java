package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FixtureRepository extends JpaRepository<Fixture, Long> {

    Optional<Object> findByExternalIdAndSport(int id, String sport);

    Fixture findFixtureByExternalIdAndSport(int id, String sport);
}
