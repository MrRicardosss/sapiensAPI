package pt.sapiens.sapiensAPI.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.sapiens.sapiensAPI.DTOs.ApiResponse;
import pt.sapiens.sapiensAPI.DTOs.VolunteerCreateDTO;
import pt.sapiens.sapiensAPI.entities.Volunteer;
import pt.sapiens.sapiensAPI.services.VolunteerService;

import java.util.Optional;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @GetMapping("/{id}")
    public ApiResponse<?> get(@PathVariable int id) {
        Optional<Volunteer> volunteer = volunteerService.getVolunteer(id);
        return new ApiResponse<>(volunteer);
    }

    @PostMapping
    public ApiResponse<?> create(@RequestBody @Valid VolunteerCreateDTO volunteerCreateDTO) {
        Volunteer volunteer = volunteerService.createVolunteer(volunteerCreateDTO);
        return new ApiResponse<>(volunteer);
    }
}
