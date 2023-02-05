package spring.starter.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.starter.domain.Author;
import spring.starter.domain.Book;
import spring.starter.domain.Category;
import spring.starter.dto.ResultPaginationResponse;
import spring.starter.dto.book.BookAddRequest;
import spring.starter.dto.book.BookDetailResponse;
import spring.starter.dto.BookUpdateRequest;
import spring.starter.dto.book.BookListResponse;
import spring.starter.dto.book.BookQuery;
import spring.starter.exception.BadRequestException;
import spring.starter.repository.BookRepository;
import spring.starter.service.AuthorService;
import spring.starter.service.BookService;
import spring.starter.service.CategoryService;
import spring.starter.service.PublisherService;
import spring.starter.util.PaginationUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public ResultPaginationResponse<BookListResponse> findBookList(Integer page, Integer limit, String sortBy, String direction, String publisherName, String bookTitle, String authorName) {
        var sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
        var pageable = PageRequest.of(page, limit, sort);
        Page<BookQuery> pageResult = bookRepository.findBookList(bookTitle, publisherName, authorName, pageable);

        //Masih kurang paham, tapi kayaknya ini buat naro semua id buku yang dipunya semua kedalam list dulu
        List<Integer> idList= pageResult.stream().map(BookQuery::getId).toList();

        Map<Integer, List<String>> categoriesCodeMap = categoryService.findCategoriesMap(idList);
        Map<Integer, List<String>> authorsMap = authorService.findAuthorsMap(idList);


        List<BookListResponse> result = pageResult.stream().map(v -> {
            BookListResponse response = new BookListResponse();
            response.setAuthorNames(authorsMap.get(v.getId()));
            response.setCategoryCodes(categoriesCodeMap.get(v.getId()));
            response.setTitle(v.getBookTitle());
            response.setPublisherName(v.getPublisherName());
            response.setDescription(v.getDescription());
            response.setId(v.getBookId());

            return response;
        }).toList();

        return PaginationUtil.createResultPageResponse(result, pageResult.getTotalElements(), (long) pageResult.getTotalPages());
    }
}
