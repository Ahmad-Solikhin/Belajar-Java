package spring.starter.dto.publisher;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class PublisherResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -7843696514500486910L;

    private String publisherId;
    private String publisherName;
}
