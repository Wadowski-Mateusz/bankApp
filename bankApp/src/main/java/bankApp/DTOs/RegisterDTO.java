package bankApp.DTOs;

public record RegisterDTO(
        String login,
        String password,
        boolean isVerified,
        String name,
        String surname,
        String email,
        String birthday,
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
