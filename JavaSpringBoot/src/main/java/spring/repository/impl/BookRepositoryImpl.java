package spring.repository.impl;

import spring.domain.Author;
import spring.domain.Book;
import spring.repository.BookRepository;

import java.util.HashMap;
import java.util.Map;

public class BookRepositoryImpl implements BookRepository {

    private Map<Integer, Book> bookMap;

//    public BookRepositoryImpl() {
//        bookMap = new HashMap<>();
//
//        Author author = new Author();
//        author.setId(1);
//        author.setName("Gayuh");
//        author.setBirthDate(1002434450L);
//
//        Book book1 = new Book(author);
//        book1.setId(1);
//        book1.setTitle("Gatau Juga Sih");
//        book1.setDescription("Ya Namanya Gatau Gimana Ya");
//
//        bookMap.put(book1.getId(), book1);
//    }

    @Override
    public Book findById(Integer id) {
        return bookMap.get(id);
    }

    public Map<Integer, Book> getBookMap() {
        return bookMap;
    }

    public void setBookMap(Map<Integer, Book> bookMap) {
        this.bookMap = bookMap;
    }
}
