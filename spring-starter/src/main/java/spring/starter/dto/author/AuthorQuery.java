package spring.starter.dto.author;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class AuthorQuery implements Serializable {
    @Serial
    private static final long serialVersionUID = 6913260836821880632L;
    private Integer bookId;

    private String authorName;
}
