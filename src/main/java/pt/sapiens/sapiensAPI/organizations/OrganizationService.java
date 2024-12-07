package pt.sapiens.sapiensAPI.organizations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.sapiens.sapiensAPI.auth.User;
import pt.sapiens.sapiensAPI.auth.UserRepository;
import pt.sapiens.sapiensAPI.organizations.DTOs.OrganizationCreateDTO;

import java.util.Optional;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<Organization> getOrganization(long id) {
        return organizationRepository.findById(id);
    }

    public Organization createOrganization(OrganizationCreateDTO organizationCreateDTO) {
        User user = new User();

        user.setEmail(organizationCreateDTO.getEmail());
        user.setPassword(organizationCreateDTO.getPassword());

        userRepository.save(user);

        Organization organization = new Organization();

        organization.setName(organizationCreateDTO.getName());
        organization.setWebsite(organizationCreateDTO.getWebsite());
        organization.setPhoneNumber(organizationCreateDTO.getPhoneNumber());
        organization.setAddress(organizationCreateDTO.getAddress());
        organization.setUser(user);

        organizationRepository.save(organization);

        return organization;
    }
}
