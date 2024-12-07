package pt.sapiens.sapiensAPI.volunteers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.sapiens.sapiensAPI.volunteers.DTOs.VolunteerCreateDTO;

import java.util.Optional;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @GetMapping("/me")
    public ResponseEntity<String> me() {
        return ResponseEntity.ok("me");
    }

    @GetMapping("/{id}")
    public Optional<Volunteer> get(@PathVariable int id) {
        return volunteerService.getVolunteer(id);
    }

    @PostMapping
    public Volunteer create(@RequestBody @Valid VolunteerCreateDTO volunteerCreateDTO) {
        return volunteerService.createVolunteer(volunteerCreateDTO);
    }
}
