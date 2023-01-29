package spring.starter.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.starter.domain.Author;
import spring.starter.domain.Book;
import spring.starter.dto.AddBookDTO;
import spring.starter.dto.BookDetailDTO;
import spring.starter.dto.BookUpdateRequest;
import spring.starter.repository.BookRepository;
import spring.starter.service.BookService;

import java.util.List;

//Annotation based config dengan menambahkan @Service karena terletak di service
@Service("bookService")
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    //Menambahkan autowired untuk mencari dependency dari spring container, dan melakukan dependency injection untuk memasukkan class author
    @Autowired
    private final BookRepository bookRepository;

    @Override
    public BookDetailDTO findDetailById(Integer id) {
        Book book = bookRepository.findBookById(id);
        BookDetailDTO dto = new BookDetailDTO();
        dto.setBookTitle(book.getTitle());
        dto.setBookDescription(book.getDescription());
        dto.setId(book.getId());
        dto.setAuthorName(book.getAuthor().getName());
        return dto;
    }

    @Override
    public List<BookDetailDTO> findBookListDetail() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(v -> {
            BookDetailDTO dto= new  BookDetailDTO();
            dto.setAuthorName(v.getAuthor().getName());
            dto.setBookTitle(v.getTitle());
            dto.setBookDescription(v.getDescription());
            dto.setId(v.getId());
            return dto;
        }).toList();
    }

    @Override
    public void addNewBook(AddBookDTO dto) {
        Author author = new Author();
        author.setName(dto.getAuthorName());

        Book book = new Book(author);
        book.setDescription(dto.getBookDescription());
        book.setTitle(dto.getBookTitle());

        bookRepository.save(book);
    }

    @Override
    public void updateBook(Integer bookId, BookUpdateRequest dto) {
        Book book = bookRepository.findBookById(bookId);
        book.setTitle(dto.getBookTitle());
        book.setDescription(dto.getBookDescription());

        bookRepository.update(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.delete(id);
    }


}
