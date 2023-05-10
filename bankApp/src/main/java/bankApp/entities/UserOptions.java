package bankApp.entities;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_options")
public class UserOptions {
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "email_subscription", nullable = false)
    private boolean emailSubscription;

    @Column(name = "two_step_login", nullable = false)
    private boolean twoStepLogin;

}
