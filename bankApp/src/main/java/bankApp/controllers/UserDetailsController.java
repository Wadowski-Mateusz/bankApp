package bankApp.controllers;

import bankApp.DTOs.UserDetailsDTO;
import bankApp.DTOs.UserOptionsDTO;
import bankApp.entities.User;
import bankApp.entities.UserOptions;
import bankApp.exceptions.UserNotFoundException;
import bankApp.services.UserDetailsService;
import bankApp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("user_details")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;
    private final UserService userService;

    public UserDetailsController(UserDetailsService userDetailsService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDetailsDTO> getUserDetails(@RequestParam UUID userId) {
        User user = userService.getUserById(userId).orElse(null);
        try {
            if (user == null) throw new UserNotFoundException("");
            return ResponseEntity.ok(userDetailsService.convertUserDetailsToDTO(user.getUserDetails()));
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
