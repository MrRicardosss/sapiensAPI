package pt.sapiens.sapiensAPI.municipalities;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("/municipalities")
public class MunicipalityController {

    @GetMapping
    public List<String> get() {
        return new ArrayList<String>();
    }
}
