package bankApp.controllers;

import bankApp.DTOs.UserOptionsDTO;
import bankApp.entities.User;
import bankApp.entities.UserOptions;
import bankApp.exceptions.UserNotFoundException;
import bankApp.services.UserOptionsService;
import bankApp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/user_options")
public class UserOptionsController {

    private final UserOptionsService userOptionsService;
    private final UserService userService;

    public UserOptionsController(UserOptionsService userOptionsService, UserService userService) {
        this.userOptionsService = userOptionsService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserOptionsDTO> getUserSettings(@RequestParam UUID userId) {
        User u = userService.getUserById(userId).orElse(null);
        try {
            if (u == null)
                throw new UserNotFoundException("");
            return ResponseEntity.ok(userOptionsService.convertUserOptionsToDTO(u.getUserOptions()));
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping//("/update")
    public ResponseEntity<?> setUserSettings(@RequestBody UserOptionsDTO uoDTO, @RequestParam UUID userId) {
        User user = userService.getUserById(userId).orElse(null);
        try {
            if (user == null)
                throw new UserNotFoundException("");
            UserOptions uo = user.getUserOptions();
            uo.setEmailSubscription(uoDTO.emailSubscription());
            userOptionsService.updateUserOptions(uo);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
