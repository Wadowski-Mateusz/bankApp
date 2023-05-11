package bankApp.DTOs;

import java.math.BigDecimal;
import java.util.UUID;

public record LoanDTO(
        UUID userId,
        String loanName,
        float interest,
        String dateFrom,
        String dateTo,
        BigDecimal amount) {
}
