package spring.starter.dto.category;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CategoryAddAndUpdateRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 7659361485515665291L;
    @NotBlank
    private String code;
    @NotBlank
    private String name;
    private String description;
}
