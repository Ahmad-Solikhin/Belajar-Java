package spring.starter.dto.publisher;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherUpdateRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1342235449580411793L;

    private String publisherName;

    private String companyName;

    private String address;
}
