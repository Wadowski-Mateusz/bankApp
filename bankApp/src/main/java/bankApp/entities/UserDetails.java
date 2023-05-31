package bankApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false,
            columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "id_number")
    private String idNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(
                    name = "FK_UserDetails_User",
                    foreignKeyDefinition = "FOREIGN KEY (user_id)" +
                            " REFERENCES users(id)" +
                            " ON DELETE CASCADE" +
                            " ON UPDATE CASCADE"))
    private User user;

    public String getFullName() {
        return name + " " + surname;
    }

}
