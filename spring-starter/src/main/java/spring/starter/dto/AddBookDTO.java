package spring.starter.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddBookDTO {
    @NotBlank
    private String bookTitle;
    @NotBlank
    private String authorName;
    private String bookDescription;
}
