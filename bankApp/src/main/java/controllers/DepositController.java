package controllers;

import models.Deposit;
import org.atmosphere.config.service.Post;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class DepositController {

    // id - user id
    @GetMapping("/{id}")
    public Deposit[] getAllDeposits(@PathVariable int id) {
        // TODO
        throw new UnsupportedOperationException("");
    }

    // id - id deposit
    @GetMapping("/{id}")
    public Deposit getDeposit(@PathVariable int id) {
        // TODO
        throw new UnsupportedOperationException("");
    }

    // id - id deposit
    @DeleteMapping("/{id}")
    public Deposit deleteDeposit(@PathVariable int id) {
        // TODO
        throw new UnsupportedOperationException("");
    }

    // id - user id
    @PostMapping("/{id}")
    public Deposit postDeposit(@PathVariable int id, @RequestBody Deposit deposit) {
        // TODO
        throw new UnsupportedOperationException("");
    }

}

