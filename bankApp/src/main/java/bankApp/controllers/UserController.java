package bankApp.controllers;


import bankApp.Consts;
import bankApp.DTOs.*;
import bankApp.entities.User;
import bankApp.exceptions.UserNotFoundException;
import bankApp.services.AccountService;
import bankApp.services.TokenService;
import bankApp.services.UserDetailsService;
import jakarta.transaction.Transactional;
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
    private final AccountService accountService;
    private final TokenService tokenService;


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
            String path = Consts.ID_DIRECTORY + "/" + userDetailsService.getIdURIByUserId(userId);
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
    @Transactional
    public ResponseEntity<Boolean> deleteUser(@RequestBody DeleteUserDTO duDTO) {
        try {
            User user = userService.getUserById(duDTO.id())
                    .orElseThrow(() -> new UserNotFoundException(""));

            accountService.deleteAccount(user.getId());
            tokenService.expireAllUserTokens(user.getId());
            userService.deleteUserById(user.getId());

            return ResponseEntity.ok(true);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
