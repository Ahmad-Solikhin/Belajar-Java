package spring.starter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.starter.domain.Book;
import spring.starter.dto.book.BookQuery;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    public Optional<Book> findBySecureId(String id);

//    @Query("SELECT DISTINCT b FROM Book AS b INNER JOIN Publisher AS p ON p.id = b.publisher.id " +
//            "JOIN b.authors AS ba " +
//            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%',:publisherName,'%')) " +
//            "AND LOWER(b.title) LIKE LOWER(CONCAT('%',:bookTitle,'%'))" +
//            "AND LOWER(ba.name) LIKE LOWER(CONCAT('%',:authorName, '%')) ") //Ini merupakan penanda untuk menggunakan JPQL
@Query("SELECT DISTINCT new spring.starter.dto.book.BookQuery(b.id, b.secureId, b.title, b.publisher.name, b.description) " +
        "FROM Book AS b " +
        "INNER JOIN Publisher AS p ON p.id = b.publisher.id " +
        "JOIN b.authors AS ba " +
        "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%',:publisherName,'%')) " +
        "AND LOWER(b.title) LIKE LOWER(CONCAT('%',:bookTitle,'%'))" +
        "AND LOWER(ba.name) LIKE LOWER(CONCAT('%',:authorName, '%')) ") //Ini merupakan penanda untuk menggunakan JPQL
    public Page<BookQuery> findBookList(String bookTitle, String publisherName, String authorName, Pageable pageable);
//
//    public List<Book> findAll();
//
//    public void save(Book book);
//
//    public void update(Book book);
//
//    public void delete(Integer id);

}
