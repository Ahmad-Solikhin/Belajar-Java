package spring.starter.service;

import spring.starter.dto.AddBookDTO;
import spring.starter.dto.BookDetailDTO;

import java.util.List;

public interface BookService {

    public BookDetailDTO findDetailById(Integer id);

    public List<BookDetailDTO> findBookListDetail();

    public void addNewBook(AddBookDTO dto);
}
