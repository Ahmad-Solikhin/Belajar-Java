package spring.starter.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.starter.domain.Author;
import spring.starter.dto.author.AuthorAddRequest;
import spring.starter.dto.author.AuthorResponse;
import spring.starter.dto.author.AuthorUpdateRequest;
import spring.starter.exception.BadRequestException;
import spring.starter.repository.AuthorRepository;
import spring.starter.service.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorResponse findById(Integer id) {

        Optional<Author> result = authorRepository.findById(id);

        Author author = result.orElseThrow(
                () -> new BadRequestException("Author With Id " + id + " Not Found")
        );

        AuthorResponse response = new AuthorResponse();
        response.setAuthorName(author.getName());
        response.setBirthDate(author.getBirthDate());

        return response;
    }

    @Override
    public void addAuthor(List<AuthorAddRequest> dto) {
        List<Author> authors = dto.stream().map(v -> {
            Author author = new Author();
            author.setName(v.getAuthorName());
            author.setBirthDate(v.getBirthDate());
            return author;
        }).toList();


        authorRepository.saveAll(authors);
    }

    @Override
    public void updateAuthor(Integer id, AuthorUpdateRequest dto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Author With Id " + id + " Not Found"));

        author.setName(dto.getAuthorName() == null ? author.getName() : dto.getAuthorName());
        author.setBirthDate(dto.getBirthDate() == null ? author.getBirthDate() : dto.getBirthDate());

        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Integer id) {
        //Kalo udah ditambahin annotation SqlDelete maka function ini akan melakukan soft delete
        authorRepository.deleteById(id);
    }

    @Override
    public void softDeleteAuthor(Integer id){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Author With Id " + id + " Not Found"));

        author.setDeleted(true);

        authorRepository.save(author);
    }


}
