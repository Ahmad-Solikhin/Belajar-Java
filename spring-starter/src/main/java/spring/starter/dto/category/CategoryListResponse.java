package spring.starter.dto.category;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CategoryListResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -1348961920411569157L;

    private String Code;

    private String name;

    private String description;
}
