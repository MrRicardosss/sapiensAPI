package pt.sapiens.sapiensAPI.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.sapiens.sapiensAPI.DTOs.OrganizationCreateDTO;
import pt.sapiens.sapiensAPI.entities.Organization;
import pt.sapiens.sapiensAPI.services.OrganizationService;

import java.util.Optional;

@RestController
@RequestMapping("/organizations")
public class OrganizationsController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/me")
    public ResponseEntity<String> me() {
        return ResponseEntity.ok("me");
    }

    @GetMapping("/{id}")
    public Optional<Organization> get(@PathVariable int id) {
        return organizationService.getOrganization(id);
    }

    @PostMapping
    public Organization create(@RequestBody @Valid OrganizationCreateDTO organizationCreateDTO) {
        return organizationService.createOrganization(organizationCreateDTO);
    }
}
