package bankApp.DTOs;

public record AddressDTO(
        String country,
        String sector,
        String city,
        String street,
        String number,
        String zip) {
}
