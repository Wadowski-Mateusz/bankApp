package bankApp.controllers;

import bankApp.services.UserDetailsService;
import bankApp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("user_details")
@AllArgsConstructor
public class UserDetailsController {

    private final UserDetailsService userDetailsService;
    private final UserService userService;

}
