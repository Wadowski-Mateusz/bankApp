package controllers;

import models.Deposit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/deposits")
public class DepositController {

    // id - user id
    @GetMapping("/{id}")
    public ResponseEntity<Deposit[]> getAllDeposits(@PathVariable UUID id) {
        // TODO
        throw new UnsupportedOperationException("");
    }

    // id - id deposit
    @GetMapping("/{id}")
    public ResponseEntity<Deposit> getDeposit(@PathVariable UUID id) {
        // TODO
        throw new UnsupportedOperationException("");
    }

    // id - id deposit
    @DeleteMapping("/{id}")
    public ResponseEntity<Deposit> deleteDeposit(@PathVariable UUID id) {
        // TODO
        throw new UnsupportedOperationException("");
    }

    // id - user id
    @PostMapping("/{id}")
    public ResponseEntity<Deposit> postDeposit(@PathVariable UUID id, @RequestBody Deposit deposit) {
        // TODO
        throw new UnsupportedOperationException("");
    }

}

