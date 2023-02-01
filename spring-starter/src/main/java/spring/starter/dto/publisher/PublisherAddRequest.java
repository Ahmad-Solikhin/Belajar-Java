package spring.starter.dto.publisher;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherAddRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1957248794265205175L;
    @NotBlank
    private String publisherName;
    @NotBlank
    private String companyName;

    private String address;
}
