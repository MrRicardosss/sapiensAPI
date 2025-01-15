package pt.sapiens.sapiensAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.sapiens.sapiensAPI.DTOs.ApiResponse;
import pt.sapiens.sapiensAPI.repositories.MunicipalityRepository;

@RestController
@RequestMapping("/municipalities")
public class MunicipalityController {
    @Autowired
    private MunicipalityRepository municipalityRepository;

    @GetMapping
    public ApiResponse<?> get() {
        return new ApiResponse<>(municipalityRepository.findAll());
    }
}
