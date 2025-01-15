package pt.sapiens.sapiensAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pt.sapiens.sapiensAPI.entities.Organization;
import pt.sapiens.sapiensAPI.entities.User;
import pt.sapiens.sapiensAPI.repositories.OrganizationRepository;
import pt.sapiens.sapiensAPI.repositories.UserRepository;
import pt.sapiens.sapiensAPI.enums.UserType;
import pt.sapiens.sapiensAPI.DTOs.OrganizationCreateDTO;

import java.util.Optional;

@Service
public class OrganizationService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public Optional<Organization> me() {
        Optional<User> user = userService.getUserByUserDetails();

        if (user.isEmpty()) {
            return Optional.empty();
        }

        return organizationRepository.findByUserId(user.get().getId());
    }

    public Optional<Organization> getOrganization(long id) {
        return organizationRepository.findById(id);
    }

    public Organization createOrganization(OrganizationCreateDTO organizationCreateDTO) {
        User user = User.builder()
                .email(organizationCreateDTO.getEmail())
                .profilePicture(organizationCreateDTO.getImageUrl())
                .phoneNumber(organizationCreateDTO.getPhoneNumber())
                .role(UserType.ORGANIZATION)
                .build();

        user.setPassword(organizationCreateDTO.getPassword(), passwordEncoder);
        user = userRepository.save(user);

        Organization organization = Organization.builder()
                .name(organizationCreateDTO.getName())
                .website(organizationCreateDTO.getWebsite())
                .address(organizationCreateDTO.getAddress())
                .user(user)
                .build();
        organizationRepository.save(organization);

        return organization;
    }
}
