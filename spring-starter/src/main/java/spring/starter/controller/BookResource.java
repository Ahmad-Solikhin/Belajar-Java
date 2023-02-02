package spring.starter.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import spring.starter.dto.book.BookAddRequest;
import spring.starter.dto.BookDetailResponse;
import spring.starter.dto.BookUpdateRequest;
import spring.starter.service.BookService;

import java.net.URI;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/books")
public class BookResource {

    private final BookService bookService;

    @GetMapping("/{id}/detail")
    public ResponseEntity<BookDetailResponse> findById(@PathVariable("id") String id){
        //Nambahin waktu di log
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        var result = bookService.findDetailById(id);
        log.info("Finish in time " + stopWatch.getTotalTimeMillis());
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Void> createNewBook(@RequestBody BookAddRequest dto){
        bookService.addNewBook(dto);
        return ResponseEntity.created(URI.create("/books")).build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<BookDetailResponse>> showBookList(){
        if (bookService.findBookListDetail() == null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok().body(bookService.findBookListDetail());
        }
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Void> updateBook(@PathVariable("bookId") Integer id, @RequestBody BookUpdateRequest dto){
        bookService.updateBook(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Integer id){
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

}
