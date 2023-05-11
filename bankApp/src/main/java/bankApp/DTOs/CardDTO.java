package bankApp.DTOs;


import java.time.LocalDate;

public record CardDTO(
        String number,
        LocalDate expiryDate,
        String cvv) {
}
