package spring.starter.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
//field username akan sering di akses untuk find by, oleh karena itu harus dilakukan indexing
@Table(name = "app_user", indexes = {
        @Index(name = "uk_username", columnList = "username")
})
//Sebagai table yang menyimpan data user untuk login harus menimplements interface security UserDetails
public class AppUser extends AbstractBaseEntity implements UserDetails {
    @Serial
    private static final long serialVersionUID = -3612314964690737252L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")
    })
    private List<Role> roles;

    /**
     * Ini merupakan method yang melakukan return role
     * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    /**
     * Mengidentifikasikan apakah akses dari user expired atau tidak. Defaultnya adalah true yang nantinya dapat diakses
     * */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Ini memungkinakn apakah akun tersebut di kuci atau tidak
     * */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /***/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /***/
    @Override
    public boolean isEnabled() {
        return true;
    }
}
