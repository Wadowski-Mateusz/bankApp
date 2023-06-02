package bankApp.controllers;

import bankApp.DTOs.TransactionDTO;
import bankApp.DTOs.TransactionViewDTO;
import bankApp.entities.Account;
import bankApp.entities.Transaction;
import bankApp.services.AccountService;
import bankApp.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    TransactionService transactionService;
    AccountService accountService;

    @GetMapping("/user/all")
    public List<TransactionViewDTO> getAllUserTransactions(@RequestParam UUID userId) {
        Account account = accountService.getAccountByUserId(userId);
        Stream<TransactionViewDTO> incomingTransactionsStream = transactionService
                .getAllIncomingTransactionsByAccountId(account.getId())
                .stream()
                .map(transaction -> transactionService.convertToTransactionViewDTOOutgoing(transaction, Transaction.TYPE_INCOMING));
        Stream<TransactionViewDTO> outgoingTransactionsStream = transactionService
                .getAllOutgoingTransactionsByAccountId(account.getId())
                .stream()
                .map(transaction -> transactionService.convertToTransactionViewDTOOutgoing(transaction, Transaction.TYPE_OUTGOING));

        List<TransactionViewDTO> allTransactionSortedByDateDescending =
                Stream.concat(incomingTransactionsStream, outgoingTransactionsStream)
                        .sorted(Comparator.comparing(TransactionViewDTO::timestamp).reversed())
                        .toList();

        return allTransactionSortedByDateDescending;
    }
}
