package bankApp.controllers;


import bankApp.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bankApp.services.UserService;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<String> testGet() {
        try {
            String s = "f63d966f-e3c0-44e6-9108-565b1b8a47a2";
            User user = userService.getUserById(UUID.fromString(s)).orElseGet(null);
            if(user == null)
                throw new RuntimeException("asd");
            return ResponseEntity.ok(user.getUserDetails().getFullName());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
