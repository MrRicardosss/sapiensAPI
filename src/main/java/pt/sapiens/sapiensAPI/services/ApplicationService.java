package pt.sapiens.sapiensAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.sapiens.sapiensAPI.DTOs.ApiResponse;
import pt.sapiens.sapiensAPI.entities.Application;
import pt.sapiens.sapiensAPI.entities.Offer;
import pt.sapiens.sapiensAPI.entities.Volunteer;
import pt.sapiens.sapiensAPI.repositories.ApplicationRepository;
import pt.sapiens.sapiensAPI.repositories.OfferRepository;
import pt.sapiens.sapiensAPI.repositories.VolunteerRepository;

import java.util.List;
import java.util.Optional;

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

    public ApiResponse<?> get(long id) {
        List<Application> applications = applicationRepository.findByOfferId(id);

        if (applications.isEmpty()) {
            return new ApiResponse<>(null);
        }

        return new ApiResponse<>(applications);
    }

    /*
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
     */

    public ApiResponse<?> create(long id) {
        try {
            // Get the current authenticated user
            Volunteer volunteer = volunteerRepository.findByUserId(authService.getUserByUserDetails().get().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Volunteer not found"));

            Offer offer = offerRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer not found"));

            // Check if the volunteer has already applied to this offer
            boolean hasApplied = hasUserAppliedToOffer(id, volunteer.getId());
            if (hasApplied) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You have already applied to this opportunity");
            }

            Application application = Application.builder()
                    .volunteer(volunteer)
                    .offer(offer)
                    .build();
            application = applicationRepository.save(application);

            return new ApiResponse<>(application);

        } catch (ResponseStatusException e) {
            // Rethrow ResponseStatusException to maintain the HTTP status code
            throw e;
        } catch (RuntimeException e) {
            return new ApiResponse<>(null);
        }
    }

    public boolean hasUserAppliedToOffer(long offerId) {
        try {
            // Get the current authenticated user
            Optional<Volunteer> volunteerOpt = volunteerRepository.findByUserId(authService.getUserByUserDetails().get().getId());
            if (volunteerOpt.isEmpty()) {
                return false;
            }

            Volunteer volunteer = volunteerOpt.get();
            return hasUserAppliedToOffer(offerId, volunteer.getId());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasUserAppliedToOffer(long offerId, long volunteerId) {
        // Check if an application already exists for this volunteer and offer
        return applicationRepository.existsByVolunteerIdAndOfferId(volunteerId, offerId);
    }
}
