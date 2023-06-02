package bankApp.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionViewDTO (
    UUID id,
    String fullName,
    String title,
    BigDecimal amount,
    LocalDateTime timestamp,
    String type) {
}
