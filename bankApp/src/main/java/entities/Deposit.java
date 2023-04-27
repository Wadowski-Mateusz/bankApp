package entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "deposits")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "name", nullable = false)
    private String name = "Lokata";

    @Column(name = "interest", nullable = false)
    private BigDecimal interest = BigDecimal.ZERO;

    @Column(name = "date_from", nullable = false)
    private Timestamp dateFrom;

    @Column(name = "date_to", nullable = false)
    private Timestamp dateTo;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;
}
