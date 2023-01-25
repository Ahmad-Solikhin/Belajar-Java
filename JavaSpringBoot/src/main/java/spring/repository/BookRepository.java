package spring.repository;

import spring.domain.Book;

public interface BookRepository {

    public Book findById(Integer id);

}
