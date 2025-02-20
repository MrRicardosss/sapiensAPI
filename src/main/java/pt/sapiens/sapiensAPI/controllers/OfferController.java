package pt.sapiens.sapiensAPI.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pt.sapiens.sapiensAPI.DTOs.ApiResponse;
import pt.sapiens.sapiensAPI.DTOs.OfferCreateDTO;

@RestController
@RequestMapping("/offers")
public class OfferController {
    @GetMapping
    public ApiResponse<?> me() {
        return new ApiResponse<>("list");
    }

    @GetMapping("/{id}")
    public ApiResponse<?> get(@PathVariable int id) {
        return new ApiResponse<>(id);
    }

    @PostMapping
    public ApiResponse<?> create(@RequestBody @Valid OfferCreateDTO offerCreateDTO) {
        return new ApiResponse<>(offerCreateDTO);
    }
}
