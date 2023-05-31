package bankApp.entities;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_options")
public class UserOptions {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false,
            columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;

    @Column(name = "email_subscription", nullable = false)
    private boolean emailSubscription;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(
                    name = "FK_UserOptions_User",
                    foreignKeyDefinition = "FOREIGN KEY (user_id)" +
                            " REFERENCES users(id)" +
                            " ON DELETE CASCADE" +
                            " ON UPDATE CASCADE"))
    private User user;

}
