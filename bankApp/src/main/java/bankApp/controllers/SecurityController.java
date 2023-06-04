package bankApp.controllers;

import bankApp.DTOs.LoginDTO;
import bankApp.DTOs.RegisterDTO;
import bankApp.DTOs.UserDTO;
import bankApp.entities.*;
import bankApp.exceptions.UserNotFoundException;
import bankApp.security.SecurityConfig;
import bankApp.services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class SecurityController {
    public final static String ID_DIRECTORY = "scans";

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    private AccountService accountService;
    private AddressService addressService;
    private UserDetailsService userDetailsService;
    private UserOptionsService userOptionsService;
    private UserService userService;
    private RoleService roleService;



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {

        // TODO check if verified

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.login(),
                        loginDTO.password()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestPart("idScan") MultipartFile idScan,
            @RequestPart("registerDTO") String registerData
    ) {
        RegisterDTO registerDTO;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            registerDTO = objectMapper.readValue(registerData, RegisterDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

        Optional<Role> role = roleService.getRoleByRole(SecurityConfig.ROLE_CLIENT);
        if(role.isEmpty()) return ResponseEntity.internalServerError().build();
        if(validateDate(registerDTO)) return ResponseEntity.badRequest().build();

        User user = new User(
                UUID.randomUUID(),
                registerDTO.login(),
                passwordEncoder.encode(registerDTO.password()),
                registerDTO.isVerified(),
                role.get()
        );

        String idURI;
        try {
            idURI = saveIdScan(idScan);
        }  catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


        UserDetails userDetails = new UserDetails(
                UUID.randomUUID(),
                registerDTO.firstName(),
                registerDTO.lastName(),
                registerDTO.email(),
                registerDTO.birthday(),
                registerDTO.idNumber(),
//                registerDTO.idURI(),
                idURI,
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

        userService.createUser(user);
        userDetailsService.createUserDetails(userDetails);
        addressService.createAddress(address);
        userOptionsService.createUserOptions(userOptions);
        accountService.createAccount(account);

        return ResponseEntity.ok().build();
    }



    private String saveIdScan(MultipartFile scan) throws IOException, IndexOutOfBoundsException {

        byte[] imageData = scan.getBytes();

        if(scan.getOriginalFilename() == null)
            throw new IOException();
        String[] originalFileNameSplit = scan.getOriginalFilename().split("\\.");
        final String filename = UUID.randomUUID() + "." + originalFileNameSplit[originalFileNameSplit.length - 1];

        // Create a file object with the directory and filename
        File imageFile = new File(ID_DIRECTORY, filename);

        // Write the image data to the file
        FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
        fileOutputStream.write(imageData);
        fileOutputStream.close();
        return filename;
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
//                        registerDTO.idURI().isEmpty() ||
//                        registerDTO.idScan().isEmpty() ||
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
