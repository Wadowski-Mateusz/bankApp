package bankApp.DTOs;

import java.time.LocalDate;

public record RegisterDTO(
        String firstName,
        String lastName,
        LocalDate birthday,
        String email,
//        String idURI,
//        MultipartFile idScan,

        String idNumber,
        String country,
        String sector,
        String city,
        String street,
        String number,
        String zip,
        String login,
        String password,
        boolean isVerified
        ) {
}
