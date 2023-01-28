package spring.starter.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class BookDetailDTO {
    private Integer id;
    private String authorName;
    private String bookTitle;
    private String bookDescription;
}
