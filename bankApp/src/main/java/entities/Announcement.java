package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "announcements")
public class Announcement {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "date_from", nullable = false)
    private Timestamp dateFrom;

    @Column(name = "date_to", nullable = false)
    private Timestamp dateTo;

    @Column(name = "content", nullable = false)
    private String content;
}
