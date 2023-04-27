package entities;

import java.math.BigDecimal;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "saving_accounts")
public class SavingAccount {
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @Column(name = "interest", nullable = false)
    private BigDecimal interest;
}
