package bankApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false,
            columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "expired", nullable = false)
    private boolean expired;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(
                    name = "FK_Tokens_User",
                    foreignKeyDefinition = "FOREIGN KEY (user_id) " +
                            "REFERENCES users(id)" +
                            " ON DELETE CASCADE" +
                            " ON UPDATE CASCADE"))
    private User user;

}
