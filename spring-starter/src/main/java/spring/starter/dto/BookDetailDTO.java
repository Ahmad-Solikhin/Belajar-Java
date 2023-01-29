package spring.starter.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@ToString
public class BookDetailDTO {
    private Integer id;
    private String authorName;
    private String bookTitle;
    private String bookDescription;
}
