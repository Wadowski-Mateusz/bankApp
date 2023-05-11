package bankApp.DTOs;


public record CardDTO(
        String number,
        String expiryDate,
        String cvv) {
}
