package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository;


import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
