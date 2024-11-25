package pt.sapiens.sapiensAPI.offers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/offers")
public class OfferController {

    @GetMapping
    public List<String> getAll() {
        return new ArrayList<String>();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Integer> get(@PathVariable int id) {
        return ResponseEntity.ok(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create() {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete() {
        return ResponseEntity.ok().build();
    }
}
