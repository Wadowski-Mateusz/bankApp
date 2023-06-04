package bankApp.controllers;


import bankApp.DTOs.*;
import bankApp.entities.User;
import bankApp.exceptions.UserNotFoundException;
import bankApp.services.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bankApp.services.UserService;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;

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

    @GetMapping(value = "/verify/data")
    public ResponseEntity<UserVerificationDTO> getUserToVerification() {

        List<User> users = userService.getAllUnverified();
        List<UserVerificationDTO> userVerificationDTOs = users
                .stream()
                .map(user -> {
                    try {
                        return userService.convertUserToVerificationDTO(user);
                    } catch (UserNotFoundException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .limit(1)
                .toList();
        try {
            return ResponseEntity.ok(userVerificationDTOs.get(0));
        } catch(IndexOutOfBoundsException e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
    }

    @GetMapping(value = "/verify/scan", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getPicture(@RequestParam UUID userId) {
        try {
            String path = RegisterController.ID_DIRECTORY + "/" + userDetailsService.getIdURIByUserId(userId);
            File f = new File(path);
            byte[] scan = Files.readAllBytes(f.toPath());
            return ResponseEntity.ok(scan);
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/verify/verify")
    public ResponseEntity<?> setVerification(@RequestBody UserVerifyOrBanDTO userVerifyOrBanDTO) {
            UUID userid = UUID.fromString(userVerifyOrBanDTO.userId());
            Optional<User> userOptional = userService.getUserById(userid);
            if (userOptional.isEmpty())
                return ResponseEntity.badRequest().build();
            User user = userOptional.get();
            if (user.isVerified())
                return ResponseEntity.ok().build();
            if (!userVerifyOrBanDTO.verified()) {
                // TODO delete user and send mail
                userService.deleteUserById(userid);
            } else {
                user.setVerified(true);
                userService.updateUser(user);
            }
            return ResponseEntity.ok().build();
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
