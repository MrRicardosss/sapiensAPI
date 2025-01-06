package pt.sapiens.sapiensAPI.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.sapiens.sapiensAPI.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
