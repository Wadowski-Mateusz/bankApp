package bankApp.DTOs;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountDTO(
    BigDecimal balance,
    String number) {
}
