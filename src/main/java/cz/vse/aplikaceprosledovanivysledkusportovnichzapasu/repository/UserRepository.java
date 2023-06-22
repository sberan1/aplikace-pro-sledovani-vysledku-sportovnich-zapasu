package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository;


import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Dependency Injection Interface BasketballScoreRepository - rozširuje JpaRepository a slúži k manipulácií s databázou pre entitu User.
 *
 * @author Štepán Beran
 * @version LS 2022/2023
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByEmailAndPassword(String email, String password);


}
