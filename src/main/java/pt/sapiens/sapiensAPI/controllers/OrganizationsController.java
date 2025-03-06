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
public class OrganizationsController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/{id}")
    public ApiResponse<?> get(@PathVariable int id) {
        return new ApiResponse<>(organizationService.getOrganization(id));
    }

    @PostMapping
    public ApiResponse<?> create(@RequestBody @Valid OrganizationCreateDTO organizationCreateDTO) {
        return new ApiResponse<>(organizationService.createOrganization(organizationCreateDTO));
    }
}
