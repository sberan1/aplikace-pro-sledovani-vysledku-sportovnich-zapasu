package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository;


import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);

}
