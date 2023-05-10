package services;

import entities.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.LoanRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan getLoanById(UUID id) {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        return optionalLoan.orElse(null);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan updateLoan(UUID id, Loan loan) {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        if (optionalLoan.isPresent()) {
            Loan existingLoan = optionalLoan.get();
            existingLoan.setName(loan.getName());
            existingLoan.setInterest(loan.getInterest());
            existingLoan.setDateFrom(loan.getDateFrom());
            existingLoan.setDateTo(loan.getDateTo());
            existingLoan.setAmount(loan.getAmount());
            return loanRepository.save(existingLoan);
        }
        return null;
    }

    public void deleteLoan(UUID id) {
        loanRepository.deleteById(id);
    }

    public BigDecimal calculateInterest(Loan loan) {
        BigDecimal interest = loan.getInterest();
        BigDecimal amount = loan.getAmount();
        return amount.multiply(interest).setScale(2, RoundingMode.HALF_UP);
    }

}
