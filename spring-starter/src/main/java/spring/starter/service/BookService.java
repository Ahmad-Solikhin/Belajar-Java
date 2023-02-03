package spring.starter.service;

import spring.starter.dto.book.BookAddRequest;
import spring.starter.dto.book.BookDetailResponse;
import spring.starter.dto.BookUpdateRequest;

import java.util.List;

public interface BookService {

    public BookDetailResponse findDetailById(String id);

    public List<BookDetailResponse> findBookListDetail();

    public void addNewBook(BookAddRequest dto);

    public void updateBook(Integer bookId, BookUpdateRequest dto);

    public void deleteBook(Integer id);
}
