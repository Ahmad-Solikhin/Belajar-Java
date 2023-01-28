package spring.starter.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Book {

    private Integer id;
    private String title;
    private String description;
    @NonNull
    private Author author;
}
