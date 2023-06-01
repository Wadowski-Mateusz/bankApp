package bankApp.DTOs;



import java.time.LocalDate;
import java.util.UUID;

public record AnnouncementDTO(
        UUID id,
        String content,
        LocalDate dateFrom,
        LocalDate dateTo,
        UUID authorId
) {
}
