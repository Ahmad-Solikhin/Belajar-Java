package spring.starter.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.starter.domain.Author;
import spring.starter.dto.author.AuthorAddRequest;
import spring.starter.dto.author.AuthorResponse;
import spring.starter.dto.author.AuthorUpdateRequest;
import spring.starter.exception.BadRequestException;
import spring.starter.exception.NotFoundException;
import spring.starter.repository.AuthorRepository;
import spring.starter.service.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorResponse findById(String id) {

        Optional<Author> result = authorRepository.findBySecureId(id);

        Author author = result.orElseThrow(
                () -> new NotFoundException("Author Not Found")
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
    public void updateAuthor(String id, AuthorUpdateRequest dto) {
        Author author = authorRepository.findBySecureId(id)
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
    public void softDeleteAuthor(String id){
        Author author = authorRepository.findBySecureId(id)
                .orElseThrow(() -> new BadRequestException("Author With Id " + id + " Not Found"));

        author.setDeleted(true);

        authorRepository.save(author);
    }

    @Override
    public List<Author> findAuthors(List<String> authorIdList) {
        List<Author> authors =  authorRepository.findBySecureIdIn(authorIdList);
        if (authors.isEmpty()) throw new BadRequestException("Author not found");
        return authors;
    }

    @Override
    public List<AuthorResponse> construct(List<Author> authors) {
        return authors.stream().map(v -> {
            AuthorResponse response = new AuthorResponse();
            response.setAuthorName(v.getName());
            response.setBirthDate(v.getBirthDate());

            return response;
        }).toList();
    }


}
