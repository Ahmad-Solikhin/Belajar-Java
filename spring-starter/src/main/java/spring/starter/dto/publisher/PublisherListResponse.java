package spring.starter.dto.publisher;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherListResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -5807242103907611640L;
    private String publisherName;

    private String publisherId;

    private String companyName;
}
