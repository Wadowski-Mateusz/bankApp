package bankApp.DTOs;

public record UserVerifyOrBanDTO(
        String userId,
        boolean verified
) {
}
