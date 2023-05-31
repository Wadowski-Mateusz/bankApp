package bankApp.DTOs;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LoanRequestDTO(
        UUID userId,
        String name,
        LocalDate dateFrom,
        BigDecimal amount,
        int months,
        BigDecimal interest) {
}
