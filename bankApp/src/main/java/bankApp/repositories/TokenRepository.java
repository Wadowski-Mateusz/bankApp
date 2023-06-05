package bankApp.repositories;

import bankApp.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {
    List<Token> findAllByUserId(UUID user_id);
    List<Token> findAllByUserIdAndExpiredIsFalse(UUID userId); // this
    List<Token> findAllByUserIdAndExpiredIsTrue(UUID userId);
    Optional<Token> findByToken(String token);
}