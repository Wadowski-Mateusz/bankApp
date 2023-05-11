package bankApp.DTOs;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDTO(
        UUID fromId,
        String fromName,
        UUID toId,
        String toName,
        String timestamp,
        BigDecimal amount
) {
}
