package spring.starter.service;

import spring.starter.dto.author.AuthorAddRequest;
import spring.starter.dto.author.AuthorResponse;
import spring.starter.dto.author.AuthorUpdateRequest;

import java.util.List;

public interface AuthorService {

    public AuthorResponse findById(String id);

    public void addAuthor(List<AuthorAddRequest> dto);

    public void updateAuthor(String id, AuthorUpdateRequest dto);

    public void deleteAuthor(Integer id);

    public void softDeleteAuthor(String id);

}
