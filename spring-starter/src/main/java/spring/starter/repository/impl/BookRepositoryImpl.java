package spring.starter.repository.impl;

import lombok.Data;
import spring.starter.domain.Book;
import spring.starter.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class BookRepositoryImpl implements BookRepository {
    private Map<Integer, Book> bookMap;

    @Override
    public Book findBookById(Integer id) {
        return bookMap.get(id);
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<Book>(bookMap.values());
    }

    @Override
    public void save(Book book) {
        book.setId(bookMap.size() + 1);
        bookMap.put(book.getId(), book);
    }
}
