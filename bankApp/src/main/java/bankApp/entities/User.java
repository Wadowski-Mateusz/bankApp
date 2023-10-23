package bankApp.entities;

import java.util.Collection;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
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



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
//        return List.of(new SimpleGrantedAuthority(role.getRole()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
