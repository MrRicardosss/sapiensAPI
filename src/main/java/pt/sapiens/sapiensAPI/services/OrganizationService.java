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

    public Optional<Organization> getOrganization(long id) {
        return organizationRepository.findById(id);
    }

    public Organization createOrganization(OrganizationCreateDTO organizationCreateDTO) {
        User user = new User();
        user.setProfilePicture(organizationCreateDTO.getImageUrl());
        user.setEmail(organizationCreateDTO.getEmail());
        user.setPassword(organizationCreateDTO.getPassword(), passwordEncoder);
        user.setPhoneNumber(organizationCreateDTO.getPhoneNumber());
        user.setRole(UserType.ORGANIZATION);

        userRepository.save(user);

        Organization organization = new Organization();
        organization.setName(organizationCreateDTO.getName());
        organization.setWebsite(organizationCreateDTO.getWebsite());
        organization.setAddress(organizationCreateDTO.getAddress());
        organization.setUser(user);

        organizationRepository.save(organization);

        return organization;
    }
}
