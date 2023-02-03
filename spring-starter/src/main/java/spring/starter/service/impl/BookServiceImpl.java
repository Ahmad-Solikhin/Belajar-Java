package spring.starter.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.starter.domain.Book;
import spring.starter.dto.book.BookAddRequest;
import spring.starter.dto.book.BookDetailResponse;
import spring.starter.dto.BookUpdateRequest;
import spring.starter.exception.BadRequestException;
import spring.starter.repository.BookRepository;
import spring.starter.service.AuthorService;
import spring.starter.service.BookService;
import spring.starter.service.CategoryService;
import spring.starter.service.PublisherService;

import java.util.List;

//Annotation based config dengan menambahkan @Service karena terletak di service
@Service("bookService")
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    //Menambahkan autowired untuk mencari dependency dari spring container, dan melakukan dependency injection untuk memasukkan class author
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;

    @Override
    public BookDetailResponse findDetailById(String id) {
        Book book = bookRepository.findBySecureId(id)
                .orElseThrow(() -> new BadRequestException("Book With Id " + id + " Not Found"));
        BookDetailResponse dto = new BookDetailResponse();
        dto.setBookTitle(book.getTitle());
        dto.setBookDescription(book.getDescription());
        dto.setId(book.getSecureId());
        dto.setCategories(categoryService.construct(book.getCategories()));
        dto.setAuthors(authorService.construct(book.getAuthors()));
        dto.setPublisher(publisherService.construct(book.getPublisher()));
        //dto.setAuthorName(book.getAuthor().getName());
        return dto;
    }

    @Override
    public List<BookDetailResponse> findBookListDetail() {
        List<Book> books = bookRepository.findAll();
        if (books.size() == 0){
            return null;
        }else {
            return books.stream().map(v -> {
                BookDetailResponse dto= new BookDetailResponse();
                //dto.setAuthorName(v.getAuthor().getName());
                dto.setBookTitle(v.getTitle());
                dto.setBookDescription(v.getDescription());
                dto.setId(v.getSecureId());
                return dto;
            }).toList();
        }
    }

    @Override
    public void addNewBook(BookAddRequest dto) {
        Book book = new Book();
        book.setAuthors(authorService.findAuthors(dto.getAuthorIdList()));
        book.setCategories(categoryService.findCategories(dto.getCategoryList()));
        book.setPublisher(publisherService.findPublisher(dto.getPublisherId()));
        book.setDescription(dto.getBookDescription());
        book.setTitle(dto.getBookTitle());

        bookRepository.save(book);
    }

    @Override
    public void updateBook(Integer bookId, BookUpdateRequest dto) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BadRequestException("Author With Id " + bookId + " Not Found"));
        book.setTitle(dto.getBookTitle());
        book.setDescription(dto.getBookDescription());

        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }


}
