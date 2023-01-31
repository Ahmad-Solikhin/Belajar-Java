package spring.starter.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table digunakan sebagai nama table nantinya
@Table(name = "author")
//Kalo kolom nya banyak boleh diaktifkan
//@DynamicUpdate

//Berikut adalah annotation yang nantinya akan diperlakukan untuk semua query JPA
@Where(clause = "deleted = false")

//Annotation untuk menandai jika memamnggil delete akan menjadi soft delete
@SQLDelete(sql = "UPDATE author SET deleted = true WHERE id = ?")
public class Author {

    @Id //Merepresentasikan field id menjadi kolom id di table
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator") //Ini autoincrement
    @SequenceGenerator(name = "author_generator", sequenceName = "author_id_seq")
    private Integer id;
    @Column(name = "name", nullable = false, length = 500)
    private String name;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean deleted = false;
}
