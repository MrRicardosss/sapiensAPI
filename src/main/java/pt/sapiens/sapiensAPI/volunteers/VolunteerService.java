package pt.sapiens.sapiensAPI.volunteers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.sapiens.sapiensAPI.auth.User;
import pt.sapiens.sapiensAPI.auth.UserRepository;
import pt.sapiens.sapiensAPI.volunteers.DTOs.VolunteerCreateDTO;

import java.util.Optional;

@Service
public class VolunteerService {

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

        userRepository.save(user);

        Volunteer volunteer = new Volunteer();

        volunteer.setUser(user);
        volunteer.setFirstName(volunteerCreateDTO.getFirstName());
        volunteer.setLastName(volunteerCreateDTO.getLastName());
        volunteer.setBirthday(volunteerCreateDTO.getBirthday());
        volunteer.setPhoneNumber(volunteerCreateDTO.getPhoneNumber());
        volunteer.setCivilId(volunteerCreateDTO.getCivilId());

        volunteerRepository.save(volunteer);

        return volunteer;
    }
}
