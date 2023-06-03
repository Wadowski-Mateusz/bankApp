package bankApp.controllers;

import bankApp.DTOs.RegisterDTO;
import bankApp.entities.*;
import bankApp.services.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {

    AccountService accountService;
    AddressService addressService;
    UserDetailsService userDetailsService;
    UserOptionsService userOptionsService;
    UserService userService;
    RoleService roleService;

    @Transactional
    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {

        Optional<Role> role = roleService.getRoleByRole(RoleService.CLIENT);
        if(role.isEmpty()) return ResponseEntity.internalServerError().build();
        if (validateDate(registerDTO)) return ResponseEntity.badRequest().build();

        User user = new User(
                UUID.randomUUID(),
                registerDTO.login(),
                registerDTO.password(),
                registerDTO.isVerified(),
                role.get()
        );

        UserDetails userDetails = new UserDetails(
                UUID.randomUUID(),
                registerDTO.firstName(),
                registerDTO.lastName(),
                registerDTO.email(),
                registerDTO.birthday(),
                registerDTO.idNumber(),
                registerDTO.idURI(),
                user
        );

        Address address = new Address(
                UUID.randomUUID(),
                registerDTO.country(),
                registerDTO.sector(),
                registerDTO.city(),
                registerDTO.street(),
                registerDTO.number(),
                registerDTO.zip(),
                userDetails
        );

        UserOptions userOptions = new UserOptions(UUID.randomUUID(), false, user);
        Account account = new Account(UUID.randomUUID(), BigDecimal.ZERO, randAccountNumber(), user);

        user = userService.createUser(user);
        userDetails = userDetailsService.createUserDetails(userDetails);
        address = addressService.createAddress(address);
        userOptions = userOptionsService.createUserOptions(userOptions);
        account = accountService.createAccount(account);


        return ResponseEntity.ok().build();
    }

    private String randAccountNumber() {
        final int length = 26;
        return Stream.generate(new Random()::nextInt)
                .limit(length)
                .map(i -> Math.abs(i) % 10)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private boolean validateDate(RegisterDTO registerDTO){
        return (
                registerDTO.firstName().isEmpty() ||
                        registerDTO.lastName().isEmpty() ||
                        registerDTO.email().isEmpty() ||
                        registerDTO.idURI().isEmpty() ||
                        registerDTO.idNumber().isEmpty() ||
                        registerDTO.country().isEmpty() ||
                        registerDTO.sector().isEmpty() ||
                        registerDTO.city().isEmpty() ||
                        registerDTO.street().isEmpty() ||
                        registerDTO.number().isEmpty() ||
                        registerDTO.zip().isEmpty() ||
                        registerDTO.login().isEmpty() ||
                        registerDTO.password().isEmpty() ||
                        registerDTO.birthday().isAfter(LocalDate.now())
        );
    }

}



















