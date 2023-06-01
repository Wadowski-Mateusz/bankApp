package bankApp.DTOs;



import java.time.LocalDateTime;
import java.util.UUID;

public record AnnouncementDTO(
        UUID id,
        String content,
        LocalDateTime dateFrom,
        LocalDateTime dateTo
) {
}
