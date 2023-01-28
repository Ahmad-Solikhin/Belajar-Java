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
import spring.starter.dto.AddBookDTO;
import spring.starter.dto.BookDetailDTO;
import spring.starter.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String showAllBook(Model model) {

        model.addAttribute("listBooks", bookService.findBookListDetail());
        return "book/list-books";
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        AddBookDTO addBookDTO = new AddBookDTO();
        model.addAttribute("addBookDTO", addBookDTO);
        return "book/add-book";
    }

    @PostMapping("/add")
    public String addNewBook(@ModelAttribute("AddBookDTO") @Valid AddBookDTO dto,
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
