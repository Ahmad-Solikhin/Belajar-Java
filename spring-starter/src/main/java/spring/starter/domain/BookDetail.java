package spring.starter.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "book_detail")
public class BookDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = 8013201150668394480L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String settings;
    private String thumbnail;
    @OneToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
