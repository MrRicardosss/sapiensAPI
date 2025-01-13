package pt.sapiens.sapiensAPI.services;

import org.modelmapper.ModelMapper;
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
    private ImageService imageService;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<Volunteer> getVolunteer(long id) {
        return volunteerRepository.findById(id);
    }

    public Volunteer createVolunteer(VolunteerCreateDTO volunteerCreateDTO) {

        User user = new User();
        user.setEmail(volunteerCreateDTO.getEmail());
        user.setPassword(volunteerCreateDTO.getPassword(), passwordEncoder);
        user.setProfilePicture(volunteerCreateDTO.getImageUrl());
        user.setPhoneNumber(volunteerCreateDTO.getPhoneNumber());
        user.setRole(UserType.VOLUNTEER);

        userRepository.save(user);

        Volunteer volunteer = new Volunteer();
        volunteer.setUser(user);
        volunteer.setFirstName(volunteerCreateDTO.getFirstName());
        volunteer.setLastName(volunteerCreateDTO.getLastName());
        volunteer.setBirthday(volunteerCreateDTO.getBirthday());
        volunteer.setCivilId(volunteerCreateDTO.getCivilId());

        return volunteer;
    }
}
