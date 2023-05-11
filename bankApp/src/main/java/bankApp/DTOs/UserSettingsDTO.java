package bankApp.DTOs;

import java.util.UUID;

public record UserSettingsDTO(UUID userId, boolean emailSubscription){
}
