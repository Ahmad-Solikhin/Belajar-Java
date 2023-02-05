package spring.starter.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class CategoryQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = -3602818390338452953L;
    private Integer bookId;

    private String categoryCode;
}
