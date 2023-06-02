package bankApp.repositories;

import bankApp.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findAllByFromAccountId(UUID userId);
    List<Transaction> findAllByToAccountId(UUID userId);
}