package pt.sapiens.sapiensAPI.volunteers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pt.sapiens.sapiensAPI.users.User;
import pt.sapiens.sapiensAPI.users.UserRepository;
import pt.sapiens.sapiensAPI.users.UserType;
import pt.sapiens.sapiensAPI.utils.ImageService;
import pt.sapiens.sapiensAPI.volunteers.DTOs.VolunteerCreateDTO;

import java.io.IOException;
import java.util.Optional;

@Service
public class VolunteerService {

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
        user.setPassword(volunteerCreateDTO.getPassword());
        user.setProfilePicture(volunteerCreateDTO.getImageUrl());
        user.setPhoneNumber(volunteerCreateDTO.getPhoneNumber());
        user.setUserType(UserType.VOLUNTEER);

        userRepository.save(user);

        Volunteer volunteer = new Volunteer();

        volunteer.setUser(user);
        volunteer.setFirstName(volunteerCreateDTO.getFirstName());
        volunteer.setLastName(volunteerCreateDTO.getLastName());
        volunteer.setBirthday(volunteerCreateDTO.getBirthday());
        volunteer.setCivilId(volunteerCreateDTO.getCivilId());

        volunteerRepository.save(volunteer);

        return volunteer;
    }
}
