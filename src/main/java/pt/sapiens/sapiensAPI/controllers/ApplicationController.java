package pt.sapiens.sapiensAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.sapiens.sapiensAPI.DTOs.ApiResponse;
import pt.sapiens.sapiensAPI.services.ApplicationService;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/{id}")
    public ApiResponse<?> get(@PathVariable int id) {
        return applicationService.get(id);
    }

    @PostMapping("/{id}")
    public ApiResponse<?> create(@PathVariable int id) {
        return applicationService.create(id);
    }
}
