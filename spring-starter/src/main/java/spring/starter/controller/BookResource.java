package spring.starter.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spring.starter.dto.BookDetailDTO;
import spring.starter.service.BookService;

@Slf4j
@AllArgsConstructor
@RestController
public class BookResource {

    private final BookService bookService;

    @GetMapping("/book/{id}")
    public BookDetailDTO findById(@PathVariable("id") Integer id){
        //Nambahin waktu di log
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        var result = bookService.findDetailById(id);
        log.info("Finish in time " + stopWatch.getTotalTimeMillis());
        return result;
    }

}
