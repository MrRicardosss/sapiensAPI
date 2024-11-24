package pt.sapiens.sapiensAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UtilsController {

    @GetMapping("/municipalities")
    public List<String> municipalities() {
        return new ArrayList<String>();
    }

    @GetMapping("/categories")
    public List<String> Categories() {
        return new ArrayList<String>();
    }
}
