package spring.starter.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.starter.dto.ResultPaginationResponse;
import spring.starter.dto.book.BookAddRequest;
import spring.starter.dto.book.BookDetailResponse;
import spring.starter.dto.BookUpdateRequest;
import spring.starter.dto.book.BookListResponse;
import spring.starter.service.BookService;

import java.net.URI;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Validated
@RestController
@RequestMapping("api")
public class BookResource {

    private final BookService bookService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("v1/books/{id}/detail")
    public ResponseEntity<BookDetailResponse> findById(@PathVariable("id") String id){
        var result = bookService.findDetailById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("v1/books")
    public ResponseEntity<Void> createNewBook(@RequestBody BookAddRequest dto){
        bookService.addNewBook(dto);
        return ResponseEntity.created(URI.create("/books")).build();
    }

    @GetMapping("v1/books/list")
    public ResponseEntity<List<BookDetailResponse>> showBookList(){
        if (bookService.findBookListDetail() == null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok().body(bookService.findBookListDetail());
        }
    }

    /**
     * Get book
     * judul buku
     * nama penerbit
     * nama penulis
     * */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("v2/books/list")
    public ResponseEntity<ResultPaginationResponse<BookListResponse>> showBookList(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "5") Integer limit,
            @RequestParam(name = "sortby", defaultValue = "title") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "bookTitle", required = false, defaultValue = "") String bookTitle,
            @RequestParam(name = "publisherName", required = false, defaultValue = "") String publisherName,
            @RequestParam(name = "authorName", required = false, defaultValue = "") String authorName
    ){
        return ResponseEntity.ok().body(bookService.findBookList(page, limit, sortBy, direction, publisherName, bookTitle, authorName));
    }


    @PutMapping("v1/books/{bookId}")
    public ResponseEntity<Void> updateBook(@PathVariable("bookId") Integer id, @RequestBody BookUpdateRequest dto){
        bookService.updateBook(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("v1/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Integer id){
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

}
