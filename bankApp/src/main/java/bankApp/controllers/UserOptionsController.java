package bankApp.controllers;

import bankApp.DTOs.UserSettingsDTO;
import bankApp.services.UserOptionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/options")
public class UserOptionsController {

    private final UserOptionsService userOptionsService;

    public UserOptionsController(UserOptionsService userOptionsService) {
        this.userOptionsService = userOptionsService;
    }


}
