package controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import models.Transaction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable UUID transactionId) {
        // TODO
        throw new UnsupportedOperationException("");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionFromPeriodOfTime(
            @PathVariable UUID userId,
            @RequestParam(name = "startDate", required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "endDate", required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate   ) {

        // TODO
        throw new UnsupportedOperationException("");
    }

}
