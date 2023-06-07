package bankApp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "announcements")
public class Announcement {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false,
            columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;

    @Column(name = "date_from", nullable = false)
    private LocalDate dateFrom;

    @Column(name = "date_to", nullable = false)
    private LocalDate dateTo;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "FK_Announcement_Author",
                    foreignKeyDefinition = "FOREIGN KEY (author_id) " +
                            "REFERENCES users(id)" +
                            " ON DELETE SET NULL" +
                            " ON UPDATE CASCADE"))
    private User author;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "deleted_by_id", referencedColumnName = "id", nullable = true,
            foreignKey = @ForeignKey(
                    name = "FK_Announcement_DeletedBy",
                    foreignKeyDefinition = "FOREIGN KEY (deleted_by_id)" +
                            " REFERENCES users(id)" +
                            " ON DELETE NO ACTION" +
                            " ON UPDATE CASCADE"))
    private User deletedBy;

}
