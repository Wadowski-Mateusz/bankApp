package bankApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false,
            columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "sector")
    private String sector;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "zip")
    private String zip;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_details_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(
                    name = "FK_Address_UserDetails",
                    foreignKeyDefinition = "FOREIGN KEY (user_details_id)" +
                            " REFERENCES user_details(id)" +
                            " ON DELETE CASCADE" +
                            " ON UPDATE CASCADE"))
    private UserDetails userDetails;

}
