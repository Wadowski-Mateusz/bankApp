package bankApp.controllers;

import bankApp.DTOs.LoanDTO;
import bankApp.DTOs.LoanRequestDTO;
import bankApp.entities.Account;
import bankApp.entities.Loan;
import bankApp.services.AccountService;
import bankApp.services.LoanService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/loans")
public class LoansController {

    private final LoanService loanService;
    private final AccountService accountService;

    @GetMapping("/user")
    public ResponseEntity<List<LoanDTO>> getLoansOfUser(@RequestParam UUID userId) {
        List<LoanDTO> loansDTOs = loanService.getLoansByUserId(userId)
                .stream()
                .map(LoanService::convertLoanToDTO)
                .toList();
        return ResponseEntity.ok(loansDTOs);
    }

    @PostMapping("/loanRequest")
    @Transactional
    public ResponseEntity<LoanDTO> addLoan(@RequestBody LoanRequestDTO loanRequestDTO) {
        Loan loan = loanService.convertRequestToLoan(loanRequestDTO);
        Loan createdLoan = loanService.createLoan(loan);
        Account account = accountService.findAccountByUserId(loanRequestDTO.userId());
        BigDecimal newBalance = account.getBalance().add(loanRequestDTO.amount());
        account.setBalance(newBalance);
        accountService.updateAccountBalance(account);
        return ResponseEntity.ok(LoanService.convertLoanToDTO(createdLoan));
    }

}
