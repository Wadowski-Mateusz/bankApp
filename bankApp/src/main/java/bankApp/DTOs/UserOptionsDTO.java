package bankApp.DTOs;

import java.util.UUID;

public record UserOptionsDTO(UUID id, boolean emailSubscription, UUID userId) {
}
