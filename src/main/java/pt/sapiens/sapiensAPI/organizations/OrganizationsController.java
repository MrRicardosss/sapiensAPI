package pt.sapiens.sapiensAPI.organizations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/organizations")
public class OrganizationsController {

    @GetMapping("/{id}")
    public ResponseEntity<Integer> get(@PathVariable int id) {
        return ResponseEntity.ok(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create() {
        return ResponseEntity.ok().build();
    }
}
