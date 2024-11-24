package pt.sapiens.sapiensAPI.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/org")
public class OrganizationController {

    @GetMapping("{id}")
    public int organization(@PathVariable int id) {
        return id;
    }

    @GetMapping("/me")
    public ResponseEntity<String> me() {
        return ResponseEntity.ok("me");
    }

    @PostMapping
    public ResponseEntity create() {
        return ResponseEntity.ok().build();
    }
}