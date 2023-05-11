package bankApp.DTOs;

public record UserDetailsDTO(
        String fullName,
        String email,
        String birthday) {
}
