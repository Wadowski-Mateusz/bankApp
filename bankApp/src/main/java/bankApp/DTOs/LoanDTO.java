package bankApp.DTOs;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LoanDTO(
        UUID userId,
        String loanName,
        float interest,
        LocalDate dateFrom,
        LocalDate dateTo,
        BigDecimal amount) {
}
