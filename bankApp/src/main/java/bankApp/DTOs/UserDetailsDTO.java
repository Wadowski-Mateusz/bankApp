package bankApp.DTOs;

import java.time.LocalDate;

public record UserDetailsDTO(
        String fullName,
        String email,
        LocalDate birthday) {
}
