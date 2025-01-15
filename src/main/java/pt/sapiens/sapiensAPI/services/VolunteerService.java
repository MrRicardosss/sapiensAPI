package pt.sapiens.sapiensAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pt.sapiens.sapiensAPI.entities.User;
import pt.sapiens.sapiensAPI.entities.Volunteer;
import pt.sapiens.sapiensAPI.repositories.UserRepository;
import pt.sapiens.sapiensAPI.repositories.VolunteerRepository;
import pt.sapiens.sapiensAPI.enums.UserType;
import pt.sapiens.sapiensAPI.DTOs.VolunteerCreateDTO;

import java.util.Optional;

@Service
public class VolunteerService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public Optional<Volunteer> me() {
        Optional<User> user = userService.getUserByUserDetails();

        if (user.isEmpty()) {
            return Optional.empty();
        }

        return volunteerRepository.findByUserId(user.get().getId());
    }

    public Optional<Volunteer> getVolunteer(long id) {
        return volunteerRepository.findById(id);
    }

    public Volunteer createVolunteer(VolunteerCreateDTO volunteerCreateDTO) {

        User user = User.builder()
                .email(volunteerCreateDTO.getEmail())
                .profilePicture(volunteerCreateDTO.getImageUrl())
                .phoneNumber(volunteerCreateDTO.getPhoneNumber())
                .role(UserType.VOLUNTEER)
                .build();

        user.setPassword(volunteerCreateDTO.getPassword(), passwordEncoder);
        user = userRepository.save(user);

        Volunteer volunteer = Volunteer.builder()
                .user(user)
                .firstName(volunteerCreateDTO.getFirstName())
                .lastName(volunteerCreateDTO.getLastName())
                .birthday(volunteerCreateDTO.getBirthday())
                .civilId(volunteerCreateDTO.getCivilId())
                .build();

        volunteerRepository.save(volunteer);

        return volunteer;
    }
}
