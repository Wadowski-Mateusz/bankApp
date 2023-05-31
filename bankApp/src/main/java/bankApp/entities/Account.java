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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id;

    @Column(name = "balance", nullable = false, precision = 20, scale = 2)
    private BigDecimal balance;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "fromAccount")
    private List<Transaction> incomingTransactions;

    @OneToMany(mappedBy = "toAccount")
    private List<Transaction> outgoingTransactions;

}
