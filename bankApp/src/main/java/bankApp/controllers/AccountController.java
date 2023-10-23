package bankApp.controllers;


import bankApp.DTOs.AccountDTO;
import bankApp.entities.Account;
import bankApp.services.AccountService;
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

    @GetMapping("/byUserId")
    public ResponseEntity<AccountDTO> getAccountByUserId(String userId) {
        try {
            UUID userUuid = UUID.fromString(userId);
            Account account = accountService.findAccountByUserId(userUuid);
            if (null == account)
                return ResponseEntity.badRequest().build();

            AccountDTO accountDTO = AccountDTO.builder()
                    .balance(account.getBalance())
                    .number(account.getNumber())
                    .build();
            return ResponseEntity.ok(accountDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
