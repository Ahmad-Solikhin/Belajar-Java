package spring.starter.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "publisher")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Publisher extends AbstractBaseEntity {
    @Serial
    private static final long serialVersionUID = -3117349898775218043L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisher_generator")
    @SequenceGenerator(name = "publisher_generator", sequenceName = "publisher_id_seq")//Ini buat generate unique sequence nya
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(name = "company_name", nullable = false)
    private String companyName;
    @Column
    private String address;

    //Bersifat opsional
    //@OneToMany(mappedBy = "publisher")
    //private List<Book> books;
}
