package bankApp.entities;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false,
            columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;

    @Column(name = "role", nullable = false, unique = true)
    private String role;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + this.role));
//                new SimpleGrantedAuthority(this.role));
    }
}
