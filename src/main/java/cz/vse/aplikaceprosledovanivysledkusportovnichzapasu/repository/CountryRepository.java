package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findCountryByExternalIdAndSport(int externalId, String sport);
    List<Country> findBySport(String sport);
    @Query("select c from Country c where c.externalId = ?1 and c.sport = ?2")
    Optional<Country> findByExternalIdAndSport(int externalId, String sport);
    @Query("select c from Country c where c.externalId = ?1")
    Country findByExternalId(int externalId);
    Country findCountryByFlagAndSport(String flag, String sport);
    @Query("select c from Country c where c.name = :country and c.sport = :sport")
    Optional<Country> findCountryByNameAndSport(String country, String sport);
}
