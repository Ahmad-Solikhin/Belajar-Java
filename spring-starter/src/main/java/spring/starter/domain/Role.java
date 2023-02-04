package spring.starter.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;

@Data
@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "role")
//Untuk table atau entity yang menyimpan role harus implements interface GrantedAuthority
public class Role implements GrantedAuthority {
    @Serial
    private static final long serialVersionUID = 2179820818301706165L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;

    @Override
    public String getAuthority() {
        return "ROLE_" + name;
    }
}
