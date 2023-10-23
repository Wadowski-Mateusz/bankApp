package bankApp.services;

import bankApp.DTOs.TransactionAddDTO;
import bankApp.DTOs.TransactionViewDTO;
import bankApp.entities.Transaction;
import bankApp.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;


    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getTransactionById(UUID id) {
        return transactionRepository.findById(id);
    }

    public void deleteTransactionById(UUID id) {
        transactionRepository.deleteById(id);
    }

    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllIncomingTransactionsByAccountId(UUID accountId) {
         return transactionRepository.findAllByToAccountId(accountId);
    }

    public List<Transaction> getAllOutgoingTransactionsByAccountId(UUID accountId) {
         return transactionRepository.findAllByFromAccountId(accountId);
    }

    public TransactionViewDTO convertTransactionToTransactionViewDTOOutgoing(Transaction transaction, String transactionType) {

        String fullName;
        BigDecimal amountToVieW = null;

        try {
            switch (transactionType) {
                case Transaction.TYPE_OUTGOING -> {
                    amountToVieW = transaction.getAmount().negate();
                    fullName = accountService.getFullNameByAccountId(transaction.getToAccount().getId());
                }
                case Transaction.TYPE_INCOMING -> {
                    amountToVieW = transaction.getAmount();
                    fullName = accountService.getFullNameByAccountId(transaction.getFromAccount().getId());
                }
                default -> {
                    amountToVieW = transaction.getAmount();
                    fullName = "Cannot determine";
                }
            }
        } catch (NullPointerException e) { // if second account has been deleted
            // `amountToView` is set before exception has been thrown
            fullName = "Account deleted";
        }

        return new TransactionViewDTO(
                transaction.getId(),
                fullName,
                transaction.getTitle(),
                amountToVieW,
                transaction.getDate(),
                transactionType
        );
    }


    public static TransactionAddDTO convertTransactionToTransactionAddDTO(Transaction transaction) {
        return new TransactionAddDTO(
                transaction.getId(),
                transaction.getToAccount().getNumber(),
                transaction.getFromAccount().getNumber(),
                transaction.getTitle(),
                transaction.getAmount(),
                transaction.getDate()
        );
    }


}
