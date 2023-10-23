package bankApp.DTOs;

public record UserVerificationDTO(
        String userId,
        String fullName,
        String email,
        String birthday,
        AddressDTO addressDTO,
        String idNumber
        ) {
}
