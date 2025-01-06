package pt.sapiens.sapiensAPI.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.sapiens.sapiensAPI.entities.Offer;

public interface OfferRepository extends CrudRepository<Offer, Long> {
}
