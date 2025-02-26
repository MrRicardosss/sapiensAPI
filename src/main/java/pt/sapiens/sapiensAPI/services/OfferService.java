package pt.sapiens.sapiensAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.sapiens.sapiensAPI.DTOs.OfferCreateDTO;
import pt.sapiens.sapiensAPI.entities.Category;
import pt.sapiens.sapiensAPI.entities.Municipality;
import pt.sapiens.sapiensAPI.entities.Offer;
import pt.sapiens.sapiensAPI.entities.Organization;
import pt.sapiens.sapiensAPI.enums.OfferStatus;
import pt.sapiens.sapiensAPI.repositories.CategoryRepository;
import pt.sapiens.sapiensAPI.repositories.MunicipalityRepository;
import pt.sapiens.sapiensAPI.repositories.OfferRepository;
import pt.sapiens.sapiensAPI.repositories.OrganizationRepository;

import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MunicipalityRepository municipalityRepository;

    @Autowired
    private UserService userService;

    public Iterable<Offer> getAll() {
        return offerRepository.findAll();
    }

    public Optional<Offer> get(long id) {
        return offerRepository.findById(id);
    }

    public Object create(OfferCreateDTO offerCreateDTO) {
        try {
            Optional<Organization> organization = organizationRepository.findByUserId(userService.getUserByUserDetails().get().getId());
            if (organization.isEmpty()) {
                throw new RuntimeException();
            }

            Optional<Category> category = categoryRepository.findById(offerCreateDTO.getCategoryId());
            if (category.isEmpty()) {
                throw new RuntimeException();
            }

            Optional<Municipality> municipality = municipalityRepository.findById(offerCreateDTO.getMunicipalityId());
            if (municipality.isEmpty()) {
                throw new RuntimeException();
            }

            Offer offer = Offer.builder()
                    .title(offerCreateDTO.getTitle())
                    .description(offerCreateDTO.getDescription())
                    .startDate(offerCreateDTO.getStartDate())
                    .endDate(offerCreateDTO.getEndDate())
                    .address(offerCreateDTO.getAddress())
                    .offerStatus(OfferStatus.OPEN)
                    .organization(organization.get())
                    .municipality(municipality.get())
                    .category(category.get())
                    .build();

            offer = offerRepository.save(offer);

            return offer;

        } catch(Exception e) {
            return null;
        }
    }

    public void delete(long id) {
        offerRepository.deleteById(id);
    }
}
