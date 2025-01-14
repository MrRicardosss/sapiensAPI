package pt.sapiens.sapiensAPI.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pt.sapiens.sapiensAPI.DTOs.VolunteerCreateDTO;
import pt.sapiens.sapiensAPI.entities.UserDetailsImpl;
import pt.sapiens.sapiensAPI.entities.Volunteer;
import pt.sapiens.sapiensAPI.services.VolunteerService;

import java.util.Optional;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @GetMapping("/me")
    public ResponseEntity me() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(userDetails);
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
