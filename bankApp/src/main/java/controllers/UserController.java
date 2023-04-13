package controllers;

import models.UserSettings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping()
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password, @RequestParam String code) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String pesel,
            @RequestParam("file") MultipartFile scan,
            @RequestParam UUID idQuestion,
            @RequestParam("file") String answear){
        // TODO
        throw new UnsupportedOperationException();
    }

    @GetMapping("/settings")
    public UserSettings getUserSettings(@RequestParam UUID userId) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @PostMapping("/settings")
    public UserSettings updateUserSettings(@RequestBody UserSettings updatedUserSettings) {
        //TODO
        throw new UnsupportedOperationException();
    }

}
