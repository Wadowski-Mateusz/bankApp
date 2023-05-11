package bankApp.controllers;

import bankApp.DTOs.RegisterDTO;
import bankApp.entities.Role;
import bankApp.entities.User;
import bankApp.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/register")
public class RegisterController {

    AccountService accountService;
    AddressService addressService;
    CardService cardService;
    RoleService roleService;
    UserDetailsService userDetailsService;
    UserOptionsService userOptionsService;
    UserService userService;

    @PostMapping("/userRegister")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO){
        Role role = roleService.getRoleById(UUID.fromString("aecdbadf-5013-4471-8796-a02e6ba52162")).orElse(null);
        if(role == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        User user = new User(
                registerDTO.login(),
                registerDTO.password(),
                registerDTO.isVerified(),
                role
        );
    return (ResponseEntity<?>) ResponseEntity.ok();
    }

}
