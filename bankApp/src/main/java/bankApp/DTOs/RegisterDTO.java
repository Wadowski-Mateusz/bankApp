package bankApp.DTOs;

import java.time.LocalDate;

public record RegisterDTO(
        String login,
        String password,
        boolean isVerified,
        String name,
        String surname,
        String email,
        LocalDate birthday,
        String idNumber,
        String pesel,
        String country,
        String sector,
        String city,
        String street,
        String number,
        String zip
        ) {
}
