package bankApp.controllers;

import bankApp.DTOs.LoanDTO;
import bankApp.DTOs.LoanRequestDTO;
import bankApp.entities.Loan;
import bankApp.services.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/loans")
public class LoansController {

    private final LoanService loanService;

    @GetMapping("/user")
    public ResponseEntity<List<LoanDTO>> getLoansOfUser(@RequestParam UUID userId) {
        List<LoanDTO> loansDTOs = loanService.getLoansByUserId(userId)
                .stream()
                .map(LoanService::convertLoanToDTO)
                .toList();
        return ResponseEntity.ok(loansDTOs);
    }

    @PostMapping("/loanRequest")
    public ResponseEntity<LoanDTO> addLoan(@RequestBody LoanRequestDTO loanRequestDTO) {
        Loan loan = loanService.convertRequestToLoan(loanRequestDTO);
        Loan createdLoan = loanService.createLoan(loan);
        return ResponseEntity.ok(LoanService.convertLoanToDTO(createdLoan));
    }

}
