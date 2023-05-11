package bankApp.DTOs;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.LocalDateTime;

public record TransactionDTO(
        UUID fromId,
        String fromName,
        UUID toId,
        String toName,
        LocalDateTime timestamp,
        BigDecimal amount
) {
}
