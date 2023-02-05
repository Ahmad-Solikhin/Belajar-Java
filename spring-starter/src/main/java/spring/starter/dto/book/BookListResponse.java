package spring.starter.dto.book;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookListResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1584258833198373315L;
    private String id;
    private String title;
    private String description;
    private String publisherName;
    private List<String> categoryCodes;
    private List<String> authorNames;

}
