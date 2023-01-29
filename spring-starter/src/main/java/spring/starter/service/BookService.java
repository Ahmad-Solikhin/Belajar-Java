package spring.starter.service;

import spring.starter.dto.AddBookDTO;
import spring.starter.dto.BookDetailDTO;
import spring.starter.dto.BookUpdateRequest;

import java.util.List;

public interface BookService {

    public BookDetailDTO findDetailById(Integer id);

    public List<BookDetailDTO> findBookListDetail();

    public void addNewBook(AddBookDTO dto);

    public void updateBook(Integer bookId, BookUpdateRequest dto);

    public void deleteBook(Integer id);
}
