package spring.service;

import spring.dto.BookDetailDTO;

public interface BookService {

    public BookDetailDTO findDetailById(Integer id);
}
