package spring.starter.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.starter.dto.book.BookAddRequest;
import spring.starter.service.BookService;

@Controller
@RequestMapping("")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("list")
    public String showAllBook(Model model) {

        model.addAttribute("listBooks", bookService.findBookListDetail());
        return "book/list-books";
    }

    @GetMapping
    public String addBook(Model model) {
        BookAddRequest addBookDTO = new BookAddRequest();
        model.addAttribute("addBookDTO", addBookDTO);
        return "book/add-book";
    }

    @PostMapping
    public String addNewBook(@ModelAttribute("AddBookDTO") @Valid BookAddRequest dto,
                             BindingResult bindingResult,
                             Errors errors,
                             Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("addBookDTO", dto);
            return "book/add-book";
        }
        bookService.addNewBook(dto);
        return "redirect:/book/";

    }

}
