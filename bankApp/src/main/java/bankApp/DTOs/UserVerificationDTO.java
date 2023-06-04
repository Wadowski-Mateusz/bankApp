package bankApp.DTOs;

import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

public record UserVerificationDTO(
        String userId,
        String fullName,
        String email,
        String birthday,
        AddressDTO addressDTO,
        String idNumber
        ) {
}
