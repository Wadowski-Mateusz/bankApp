package bankApp.controllers;

import bankApp.DTOs.LoginDTO;
import bankApp.DTOs.UserDTO;
import bankApp.entities.User;
import bankApp.exceptions.UserNotFoundException;
import bankApp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            User user = userService.getUserByLogin(loginDTO.login()).orElse(null);
            if (user == null || !user.getPassword().equals(loginDTO.password()))
                throw new UserNotFoundException("");
            return ResponseEntity.ok(userService.convertUserToDTO(user));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
