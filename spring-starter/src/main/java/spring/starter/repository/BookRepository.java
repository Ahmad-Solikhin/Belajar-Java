package spring.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.starter.domain.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    public Optional<Book> findBySecureId(String id);
//
//    public List<Book> findAll();
//
//    public void save(Book book);
//
//    public void update(Book book);
//
//    public void delete(Integer id);

}
