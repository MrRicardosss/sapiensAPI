package pt.sapiens.sapiensAPI.categories;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("/categories")
public class CategoryController {

    @GetMapping
    public List<String> get() {
        return new ArrayList<String>();
    }
}
