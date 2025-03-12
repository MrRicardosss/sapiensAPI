package pt.sapiens.sapiensAPI.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.sapiens.sapiensAPI.DTOs.ApiResponse;
import pt.sapiens.sapiensAPI.DTOs.AuthDTO;
import pt.sapiens.sapiensAPI.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ApiResponse<?> login(@RequestBody @Valid AuthDTO authDTO) {
        return new ApiResponse<>(authService.login(authDTO));
    }

    @GetMapping("/me")
    public ApiResponse<?> me() {
        return authService.me();
    }
}
