package pt.sapiens.sapiensAPI.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.sapiens.sapiensAPI.entities.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {
}
