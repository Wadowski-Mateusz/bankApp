package bankApp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "date_from", nullable = false)
    private Timestamp dateFrom;

    @Column(name = "date_to", nullable = false)
    private Timestamp dateTo;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created", nullable = false)
    private Timestamp created;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = true,
            foreignKey = @ForeignKey(
                    name = "FK_Announcement_Author",
                    foreignKeyDefinition = "FOREIGN KEY (deleted_by_id) " +
                            "REFERENCES users(id)" +
                            " ON DELETE SET NULL" +
                            " ON UPDATE CASCADE"))
    private User author;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "deleted_by_id", referencedColumnName = "id", nullable = true,
            foreignKey = @ForeignKey(
                    name = "FK_Announcement_DeletedBy",
                    foreignKeyDefinition = "FOREIGN KEY (deleted_by_id)" +
                            " REFERENCES users(id)" +
                            " ON DELETE SET NULL" +
                            " ON UPDATE CASCADE"))
    private User deletedBy;

}
