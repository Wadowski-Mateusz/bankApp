package bankApp.repositories;

import bankApp.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID> {
}