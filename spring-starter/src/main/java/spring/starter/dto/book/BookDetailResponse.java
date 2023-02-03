package spring.starter.dto.book;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.ToString;
import spring.starter.domain.Category;
import spring.starter.dto.author.AuthorResponse;
import spring.starter.dto.category.CategoryListResponse;
import spring.starter.dto.publisher.PublisherResponse;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@ToString
public class BookDetailResponse {
    private String id;
    private List<AuthorResponse> authors;
    private List<CategoryListResponse> categories;
    private PublisherResponse publisher;
    private String bookTitle;
    private String bookDescription;
}
