package bankApp.controllers;


import bankApp.DTOs.DeleteUserDTO;
import bankApp.DTOs.LoginDTO;
import bankApp.DTOs.UserDTO;
import bankApp.entities.User;
import bankApp.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bankApp.services.UserService;

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

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> login(@RequestBody DeleteUserDTO duDTO) {
        try {
            User user = userService.getUserById(duDTO.id()).orElse(null);
            if (user == null || !user.getPassword().equals(duDTO.password()))
                throw new UserNotFoundException("");

            userService.deleteUserById(duDTO.id());
            return ResponseEntity.ok(true);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
