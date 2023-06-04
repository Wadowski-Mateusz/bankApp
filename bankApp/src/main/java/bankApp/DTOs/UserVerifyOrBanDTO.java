package bankApp.DTOs;

import java.util.UUID;

public record UserVerifyOrBanDTO(
        String userId,
        boolean verified
) {
}
