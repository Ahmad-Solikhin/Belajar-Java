package spring.starter.repository;

//Ini merupakan library untuk menggunakan orm
import org.springframework.data.jpa.repository.JpaRepository;
import spring.starter.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    public Optional<Author> findById(Integer id);
    public Optional<Author> findBySecureId(String id);
    public Optional<Author> findByIdAndDeletedFalse(Integer id);
    public List<Author> findByNameLike(String name);

}
