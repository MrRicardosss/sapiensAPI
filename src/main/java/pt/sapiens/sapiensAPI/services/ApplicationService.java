package pt.sapiens.sapiensAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.sapiens.sapiensAPI.DTOs.ApiResponse;
import pt.sapiens.sapiensAPI.entities.Application;
import pt.sapiens.sapiensAPI.entities.Offer;
import pt.sapiens.sapiensAPI.entities.Volunteer;
import pt.sapiens.sapiensAPI.repositories.ApplicationRepository;
import pt.sapiens.sapiensAPI.repositories.OfferRepository;
import pt.sapiens.sapiensAPI.repositories.VolunteerRepository;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private OfferRepository offerRepository;

    public ApiResponse<?> create(long id) {
        try {
            Volunteer volunteer = volunteerRepository.findByUserId(authService.getUserByUserDetails().get().getId()).orElseThrow(RuntimeException::new);
            Offer offer = offerRepository.findById(id).orElseThrow(RuntimeException::new);

            Application application = Application.builder()
                    .volunteer(volunteer)
                    .offer(offer)
                    .build();
            application = applicationRepository.save(application);

            return new ApiResponse<>(application);

        } catch (RuntimeException e) {
            return new ApiResponse<>(null);
        }
    }
}
