package bankApp.services;

import bankApp.DTOs.AnnouncementDTO;
import bankApp.entities.Announcement;
import bankApp.entities.User;
import bankApp.repositories.AnnouncementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final UserService userService;

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    public Announcement getAnnouncementById(UUID announcementId) {
        return announcementRepository.findById(announcementId).orElse(null);
    }

    public Announcement createAnnouncement(Announcement announcement) {
        announcement.setId(UUID.randomUUID());
        return announcementRepository.save(announcement);
    }

    public Announcement updateAnnouncement(Announcement announcement) {

        Announcement announcementFromDB = announcementRepository.findById(announcement.getId()).orElse(null);

        if (announcementFromDB != null) {
            announcementFromDB.setDateFrom(announcement.getDateFrom());
            announcementFromDB.setDateTo(announcement.getDateTo());
            announcementFromDB.setContent(announcement.getContent());

            return announcementRepository.save(announcementFromDB);
        } else {
            return null;
        }
    }

    public boolean deleteAnnouncement(UUID announcementId) {
        Announcement announcement = announcementRepository.findById(announcementId).orElse(null);

        if (announcement != null) {
            announcementRepository.delete(announcement);
            return true;
        } else {
            return false;
        }
    }

    public static AnnouncementDTO convertAnnouncementToDTO(Announcement a) {
        User author = a.getAuthor();
        UUID id = author != null ? author.getId() : null;

        return new AnnouncementDTO(
                a.getId(),
                a.getContent(),
                a.getDateFrom(),
                a.getDateTo(),
                id
        );
    }

    public Announcement convertDtoToAnnouncement(AnnouncementDTO announcementDTO) {

//        User author = userService.getUserById(announcementDTO.authorId());


        return new Announcement (
                UUID.randomUUID(),
                announcementDTO.dateFrom(),
                announcementDTO.dateTo(),
                announcementDTO.content(),
                LocalDateTime.MIN,
                userService.getUserById(announcementDTO.authorId()).orElseGet(null),
                null
        );
    }


    public void setDeletingUser(Announcement announcement, UUID deletingUserId) {
        announcement.setDeletedBy(userService.getUserById(deletingUserId).get());
    }

}
