package pt.sapiens.sapiensAPI.services;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pt.sapiens.sapiensAPI.DTOs.ApiResponse;
import pt.sapiens.sapiensAPI.DTOs.AuthDTO;
import pt.sapiens.sapiensAPI.entities.Organization;
import pt.sapiens.sapiensAPI.entities.User;
import pt.sapiens.sapiensAPI.entities.UserDetailsImpl;
import pt.sapiens.sapiensAPI.entities.Volunteer;
import pt.sapiens.sapiensAPI.repositories.OrganizationRepository;
import pt.sapiens.sapiensAPI.repositories.UserRepository;
import pt.sapiens.sapiensAPI.repositories.VolunteerRepository;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    public String login(@RequestBody @Valid AuthDTO authDTO) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword()));
        return jwtService.generateToken((UserDetails) auth.getPrincipal());
    }

    public ApiResponse<?> me() {
        try {
            Optional<User> user = getUserByUserDetails();
            if (user.isEmpty()) {
                throw new RuntimeException();
            }

            return switch (user.get().getRole()) {
                case VOLUNTEER -> {
                    Optional<Volunteer> volunteer = volunteerRepository.findByUserId(user.get().getId());
                    yield new ApiResponse<>(volunteer.orElseThrow(RuntimeException::new));
                }
                case ORGANIZATION -> {
                    Optional<Organization> organization = organizationRepository.findByUserId(user.get().getId());
                    yield new ApiResponse<>(organization.orElseThrow(RuntimeException::new));
                }
            };

        } catch (Exception e) {
            return new ApiResponse<>(null);
        }
    }

    public Optional<User> getUserByUserDetails() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername());
    }
}
