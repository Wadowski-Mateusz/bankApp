package bankApp.controllers;

import bankApp.services.UserDetailsService;
import bankApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user_details")
@RequiredArgsConstructor
public class UserDetailsController {

    private final UserDetailsService userDetailsService;
    private final UserService userService;


    /* *************************/
    /*  controller is set to   */
    /*     hasRole(ADMIN)      */
    /* *************************/

//    @GetMapping
//    public ResponseEntity<UserDetailsDTO> getUserDetails(@RequestParam UUID userId) {
//        User user = userService.getUserById(userId).orElse(null);
//        try {
//            if (user == null) throw new UserNotFoundException("");
//            return ResponseEntity.ok(userDetailsService.convertUserDetailsToDTO(user.getUserDetails()));
//        } catch (UserNotFoundException e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }

}
