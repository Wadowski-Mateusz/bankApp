package bankApp.services;

import bankApp.DTOs.LoanDTO;
import bankApp.DTOs.LoanRequestDTO;
import bankApp.entities.Loan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import bankApp.repositories.LoanRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserService userService;


    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan getLoanById(UUID id) {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        return optionalLoan.orElse(null);
    }

    public List<Loan> getLoansByUserId(UUID id) {
        return loanRepository.findByUserId(id);
    }

    public static LoanDTO convertLoanToDTO(Loan loan) {
        return new LoanDTO(
                loan.getId(),
                loan.getName(),
                loan.getInterest(),
                loan.getDateFrom(),
                loan.getDateTo(),
                loan.getAmount(),
                loan.getDue());
    }

    public Loan convertRequestToLoan(LoanRequestDTO loanRequestDTO) {
        return new Loan(
                UUID.randomUUID(),
                loanRequestDTO.name(),
                loanRequestDTO.interest(),
                loanRequestDTO.dateFrom(),
                loanRequestDTO.dateFrom().plusMonths(loanRequestDTO.months()),
                loanRequestDTO.amount(),
                loanRequestDTO.amount().multiply(loanRequestDTO.interest()),
                userService.getUserById(loanRequestDTO.userId()).orElseGet(null)
        );
    }

}
