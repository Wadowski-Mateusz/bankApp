package bankApp.DTOs;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.LocalDateTime;

public record TransactionAddDTO(
        UUID id,
        String receiverAccountNumber,
        String senderAccountNumber,
        String title,
        BigDecimal amount,
        LocalDateTime timestamp
) {
}
