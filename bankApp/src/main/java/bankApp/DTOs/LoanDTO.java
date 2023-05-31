package bankApp.DTOs;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LoanDTO(
        UUID id,
        String name,
        BigDecimal interest,
        LocalDate dateFrom,
        LocalDate dateTo,
        BigDecimal amount,
        BigDecimal due) {
}
