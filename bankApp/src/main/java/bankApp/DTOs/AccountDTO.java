package bankApp.DTOs;

import java.math.BigDecimal;

public record AccountDTO(
    BigDecimal balance,
    String number) {
}
