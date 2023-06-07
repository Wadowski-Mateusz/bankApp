package bankApp.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    static public final String TYPE_OUTGOING = "Outgoing";
    static public final String TYPE_INCOMING = "Incoming";

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false,
            columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "title", nullable = false)
    private String title;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "from_account_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "FK_Transactions_FromAccount",
                    foreignKeyDefinition = "FOREIGN KEY (from_account_id)" +
                            " REFERENCES accounts(id)" +
                            " ON DELETE SET NULL" +
                            " ON UPDATE CASCADE"))
    private Account fromAccount;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "to_account_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "FK_Transactions_ToAccount",
                    foreignKeyDefinition = "FOREIGN KEY (to_account_id)" +
                            " REFERENCES accounts(id)" +
                            " ON DELETE SET NULL" +
                            " ON UPDATE CASCADE"))
    private Account toAccount;

}
