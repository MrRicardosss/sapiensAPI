package pt.sapiens.sapiensAPI.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.sapiens.sapiensAPI.DTOs.ApiResponse;
import pt.sapiens.sapiensAPI.DTOs.OfferCreateDTO;
import pt.sapiens.sapiensAPI.services.OfferService;

@RestController
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @GetMapping
    public ApiResponse<?> all() {
        return new ApiResponse<>(offerService.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<?> get(@PathVariable int id) {
        return new ApiResponse<>(offerService.get(id));
    }

    @PostMapping
    public ApiResponse<?> create(@RequestBody @Valid OfferCreateDTO offerCreateDTO) {
        return new ApiResponse<>(offerService.create(offerCreateDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        offerService.delete(id);
    }
}