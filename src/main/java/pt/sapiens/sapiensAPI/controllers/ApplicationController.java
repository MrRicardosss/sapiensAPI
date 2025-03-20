package pt.sapiens.sapiensAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.sapiens.sapiensAPI.DTOs.ApiResponse;
import pt.sapiens.sapiensAPI.services.ApplicationService;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/me")
    public ApiResponse<?> getAllMyApp() {
        return new ApiResponse<>(applicationService.allVolApplications());
    }

    @GetMapping("/offers/{id}")
    public ApiResponse<?> getAllOffersApp(@PathVariable int id) {
        return applicationService.get(id);
    }

    @PostMapping("/{id}")
    public ApiResponse<?> create(@PathVariable int id) {
        return applicationService.create(id);
    }

    @GetMapping("/{id}/check")
    public ResponseEntity<?> checkApplication(@PathVariable int id) {
        boolean hasApplied = applicationService.hasUserAppliedToOffer(id);

        if (hasApplied) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        applicationService.delete(id);
    }
}
