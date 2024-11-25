package pt.sapiens.sapiensAPI.volunteers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {

    @GetMapping("/me")
    public ResponseEntity<String> me() {
        return ResponseEntity.ok("me");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Integer> get(@PathVariable int id) {
        return ResponseEntity.ok(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create() {
        return ResponseEntity.ok().build();
    }
}
