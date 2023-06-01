package bankApp.DTOs;

import java.util.UUID;

public record AnnouncementDeleteDTO(UUID announcementId, UUID deletingUserId) {
}
