package bankApp.controllers;


import bankApp.DTOs.AccountDTO;
import bankApp.services.AccountService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@CrossOrigin
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    /* *************************/
    /*  controller is set to   */
    /*     hasRole(ADMIN)      */
    /* *************************/

}
