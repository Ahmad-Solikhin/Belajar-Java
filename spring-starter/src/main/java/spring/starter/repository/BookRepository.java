package spring.starter.repository;

import spring.starter.domain.Book;

import java.util.List;

public interface BookRepository {

    public Book findBookById(Integer id);

    public List<Book> findAll();

    public void save(Book book);

    public void update(Book book);

    public void delete(Integer id);

}
