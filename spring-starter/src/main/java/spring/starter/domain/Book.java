package spring.starter.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "book")
public class Book extends AbstractBaseEntity {

    @Serial
    private static final long serialVersionUID = 3457324281849832877L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    /**
     * Ini Buat Relasi Many to Many
     * jadi tinggal pilih salah satu aja dari entity yang mau ngimplementasi
     * Tambah annotation @ManyToMany, @JoinTable
     *
     * */
    @ManyToMany
    @JoinTable(name = "book_author", joinColumns = {
            @JoinColumn(name = "book_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "author_id", referencedColumnName = "id")
    })
    private List<Author> authors;

    @ManyToMany
    @JoinTable(name = "book_category", joinColumns = {
            @JoinColumn(name = "book_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "category_code", referencedColumnName = "code")
    })
    private List<Category> categories;
}
