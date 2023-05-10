package services;

import entities.Announcement;
import repositories.AnnouncementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

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

    public Announcement updateAnnouncement(UUID announcementId, Announcement announcementDetails) {
        Announcement announcement = announcementRepository.findById(announcementId).orElse(null);

        if (announcement != null) {
            announcement.setDateFrom(announcementDetails.getDateFrom());
            announcement.setDateTo(announcementDetails.getDateTo());
            announcement.setContent(announcementDetails.getContent());

            return announcementRepository.save(announcement);
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
}
