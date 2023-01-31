package spring.starter.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = 538233526722690345L;
    @Id
    private String code;
    @Column(nullable = false)
    private String name;

    private String description;

    //Jika ingin melakukan many to many dua arah bisa seperti berikut
    @ManyToMany(mappedBy = "categories")
    private List<Book> books;

}
