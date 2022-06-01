package by.school.diary.entity;

import by.school.diary.domain.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
@Table()
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
public class UserEntity extends BaseEntity implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "LastName is mandatory")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    @Column(nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "LastName is mandatory")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    @Column(nullable = false, length = 50)
    private String lastName;

    @NotBlank(message = "LastName is mandatory")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Column(nullable = false)
    private String password;

    @Column(length = 70, unique = true, nullable = true)
    @Email
    private String email;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean verified = true;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean locked = true;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean credentialsExpired = true;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean accountExpired = true;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean enabled = true;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role_id")
    private Set<Role> roles = new HashSet<>();

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public UserEntity(Long id, String password, String username, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.authorities = authorities;
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
