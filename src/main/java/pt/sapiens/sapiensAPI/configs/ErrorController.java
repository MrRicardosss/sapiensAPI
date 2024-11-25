package pt.sapiens.sapiensAPI.configs;

import org.hibernate.annotations.NotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @GetMapping("/error")
    public ResponseEntity<NotFound> error() {
        return ResponseEntity.notFound().build();
    }
}
