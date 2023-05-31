package bankApp.entities;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false,
            columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_verified", nullable = false)
    private boolean isVerified;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(
                    name = "FK_User_Role",
                    foreignKeyDefinition = "FOREIGN KEY (role_id) " +
                            "REFERENCES roles(id)" +
                            " ON DELETE RESTRICT" +
                            " ON UPDATE CASCADE"))
    private Role role;

    public User(String login, String password, boolean isVerified, Role role) {
        this.login = login;
        this.password = password;
        this.isVerified = isVerified;
        this.role = role;
    }
}
