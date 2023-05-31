package bankApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false,
            columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;

    @Column(name = "balance", nullable = false, precision = 20, scale = 2)
    private BigDecimal balance;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(
                    name = "FK_Accounts_Users",
                    foreignKeyDefinition = "FOREIGN KEY (user_id)" +
                            " REFERENCES users(id)" +
                            " ON DELETE CASCADE" +
                            " ON UPDATE CASCADE"))
    private User user;

}
