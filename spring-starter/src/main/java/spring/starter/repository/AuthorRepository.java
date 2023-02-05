package spring.starter.repository;

//Ini merupakan library untuk menggunakan orm
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.starter.domain.Author;
import spring.starter.dto.author.AuthorQuery;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    public Optional<Author> findById(Integer id);
    public Optional<Author> findBySecureId(String id);
    public Optional<Author> findByIdAndDeletedFalse(Integer id);
    public List<Author> findByNameLike(String name);
    public List<Author> findBySecureIdIn(List<String> authorIdList);

    @Query("SELECT new spring.starter.dto.author.AuthorQuery(b.id, ba.name) " +
            "FROM Book  AS b " +
            "JOIN b.authors AS ba " +
            "WHERE b.id IN :bookIdList")
    public List<AuthorQuery> findAuthorByBookListId(List<Integer> bookIdList);

}
