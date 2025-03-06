package pt.sapiens.sapiensAPI.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.sapiens.sapiensAPI.DTOs.ApiResponse;
import pt.sapiens.sapiensAPI.DTOs.OrganizationCreateDTO;
import pt.sapiens.sapiensAPI.entities.Organization;
import pt.sapiens.sapiensAPI.services.OrganizationService;

import java.util.Optional;

@RestController
@RequestMapping("/organizations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrganizationsController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/me")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ApiResponse<?> me() {
        Optional<Organization> organization = organizationService.me();

        return new ApiResponse<>(organization);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ApiResponse<?> get(@PathVariable int id) {
        return new ApiResponse<>(organizationService.getOrganization(id));
    }

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ApiResponse<?> create(@RequestBody @Valid OrganizationCreateDTO organizationCreateDTO) {
        return new ApiResponse<>(organizationService.createOrganization(organizationCreateDTO));
    }
}
