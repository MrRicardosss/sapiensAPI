package pt.sapiens.sapiensAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pt.sapiens.sapiensAPI.DTOs.ApiResponse;
import pt.sapiens.sapiensAPI.services.ImageService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/{filename}")
    public ApiResponse<?> getImage(@PathVariable String filename) {
        try {
            Path imagePath = Paths.get("images").resolve(filename);
            Resource resource = new UrlResource(imagePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("Image not found");
            }

            return new ApiResponse<>(resource);

        } catch (Exception e) {
            return new ApiResponse<>(null);
        }
    }

    @PostMapping
    public ApiResponse<?> createImage(@RequestParam("image") MultipartFile image) {
        try {
            return new ApiResponse<>(imageService.saveImage(image));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}