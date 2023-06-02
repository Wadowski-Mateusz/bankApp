package bankApp.controllers;

import bankApp.DTOs.TransactionAddDTO;
import bankApp.DTOs.TransactionViewDTO;
import bankApp.entities.Account;
import bankApp.entities.Transaction;
import bankApp.services.AccountService;
import bankApp.services.TransactionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    TransactionService transactionService;
    AccountService accountService;

    @GetMapping("/user/all")
    public ResponseEntity<List<TransactionViewDTO>> getAllUserTransactions(@RequestParam UUID userId) {
        Account account = accountService.getAccountByUserId(userId);
        Stream<TransactionViewDTO> incomingTransactionsStream = transactionService
                .getAllIncomingTransactionsByAccountId(account.getId())
                .stream()
                .map(transaction -> transactionService.convertTransactionToTransactionViewDTOOutgoing(transaction, Transaction.TYPE_INCOMING));
        Stream<TransactionViewDTO> outgoingTransactionsStream = transactionService
                .getAllOutgoingTransactionsByAccountId(account.getId())
                .stream()
                .map(transaction -> transactionService.convertTransactionToTransactionViewDTOOutgoing(transaction, Transaction.TYPE_OUTGOING));

        List<TransactionViewDTO> allTransactionSortedByDateDescending =
                Stream.concat(incomingTransactionsStream, outgoingTransactionsStream)
                        .sorted(Comparator.comparing(TransactionViewDTO::timestamp).reversed())
                        .toList();

        return ResponseEntity.ok(allTransactionSortedByDateDescending);
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<TransactionAddDTO> addTransaction(@RequestBody TransactionAddDTO transactionAddDTO) {
        Optional<Account> accountReceiverOptional = accountService.getAccountByNumber(transactionAddDTO.receiverAccountNumber());
        Optional<Account> accountSenderOptional = accountService.getAccountByNumber(transactionAddDTO.senderAccountNumber());
        BigDecimal amount = transactionAddDTO.amount();
        LocalDateTime dateTime = transactionAddDTO.timestamp() != null
                ? transactionAddDTO.timestamp() : LocalDateTime.now();
        String title = transactionAddDTO.title() != null
                ? transactionAddDTO.title() : "";

        if (accountReceiverOptional.isEmpty()
                || accountSenderOptional.isEmpty()
                || amount.compareTo(BigDecimal.ZERO) < 1
                || dateTime.isAfter(LocalDateTime.now())
                || title.isEmpty())
            return ResponseEntity.badRequest().build();



        //Updating balances
        Account accountSender = accountSenderOptional.get();
        accountSender.setBalance(accountSender.getBalance().subtract(amount));
        Account updateAccountSender = accountService.updateAccountBalance(accountSender);

        Account accountReceiver = accountReceiverOptional.get();
        accountReceiver.setBalance(accountReceiver.getBalance().add(amount));
        Account updateAccountReceiver = accountService.updateAccountBalance(accountReceiver);


        Transaction createdTransaction = transactionService.createTransaction(
                new Transaction(
                        UUID.randomUUID(),
                        dateTime,
                        amount,
                        title,
                        updateAccountSender,
                        updateAccountReceiver
                )
        );

        TransactionAddDTO createdTransactionAddDTO = TransactionService.convertTransactionToTransactionAddDTO(createdTransaction);
        return ResponseEntity.ok(createdTransactionAddDTO);
    }



}
