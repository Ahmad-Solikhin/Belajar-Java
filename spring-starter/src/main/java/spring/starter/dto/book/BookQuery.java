package spring.starter.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class BookQuery implements Serializable {
    @Serial
    private static final long serialVersionUID = 6788568376506000778L;
    private Integer id;
    private String bookId;
    private String bookTitle;
    private String publisherName;
    private String description;
}
