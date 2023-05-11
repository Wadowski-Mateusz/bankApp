package bankApp.DTOs;

public record AnnouncementToAddDTO(
        String dateFrom,
        String dateTo,
        String content
) {
}
