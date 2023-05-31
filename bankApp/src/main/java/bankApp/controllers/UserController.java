package bankApp.controllers;


import bankApp.DTOs.AccountDTO;
import bankApp.DTOs.LoginDTO;
import bankApp.DTOs.UserDTO;
import bankApp.DTOs.UserOptionsDTO;
import bankApp.entities.User;
import bankApp.exceptions.UserNotFoundException;
import bankApp.services.AccountService;
import bankApp.services.UserOptionsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bankApp.services.UserService;

import java.util.UUID;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
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
