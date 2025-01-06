package pt.sapiens.sapiensAPI.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.sapiens.sapiensAPI.entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
