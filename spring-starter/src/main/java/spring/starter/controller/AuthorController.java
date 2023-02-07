package spring.starter.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.starter.dto.author.AuthorAddRequest;
import spring.starter.dto.author.AuthorResponse;
import spring.starter.dto.author.AuthorUpdateRequest;
import spring.starter.service.AuthorService;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("{authorId}/detail")
    public ResponseEntity<AuthorResponse> findAuthorById(@PathVariable("authorId") String id){
        return ResponseEntity.ok().body(authorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> addAuthor(@RequestBody @Valid List<AuthorAddRequest> dto){
        authorService.addAuthor(dto);
        return ResponseEntity.created(URI.create("/author")).build();
    }

    @PutMapping("{authorId}")
    public ResponseEntity<Void> updateAuthor(@PathVariable("authorId") String id, @RequestBody AuthorUpdateRequest dto){
        authorService.updateAuthor(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("authorId") Integer id){
        authorService.deleteAuthor(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("soft/{authorId}")
    public ResponseEntity<Void> softDeleteAuthor(@PathVariable("authorId") String id){
        authorService.softDeleteAuthor(id);
        return ResponseEntity.ok().build();
    }

}
