package bankApp.DTOs;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.LocalDateTime;

public record TransactionDTO(
        UUID id,
        UUID fromId,
        String fromName,
        UUID toId,
        String toName,
        String title,
        BigDecimal amount,
        LocalDateTime timestamp
) {
}
