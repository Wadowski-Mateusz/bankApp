package bankApp.DTOs;

import java.time.LocalDate;

public record AnnouncementToAddDTO(
        LocalDate dateFrom,
        LocalDate dateTo,
        String content
) {
}
