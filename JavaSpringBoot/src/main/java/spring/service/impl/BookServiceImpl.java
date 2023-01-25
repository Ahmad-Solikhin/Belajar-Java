package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.domain.Author;
import spring.domain.Book;
import spring.dto.BookDetailDTO;
import spring.repository.BookRepository;
import spring.repository.impl.BookRepositoryImpl;
import spring.service.BookService;

//Annotation based config dengan menambahkan @Service karena terletak di service
@Service("bookService")
public class BookServiceImpl implements BookService {

    private Book book;

    //Menambahkan autowired untuk mencari dependency dari spring container
    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        Author author = new Author();
        book = new Book(author);
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDetailDTO findDetailById(Integer id) {
        Book book = bookRepository.findById(id);
        BookDetailDTO dto = new BookDetailDTO();
        dto.setBookTitle(book.getTitle());
        dto.setBookDescription(book.getDescription());
        dto.setId(book.getId());
        dto.setAuthorName(book.getAuthor().getName());
        return dto;
    }
}
