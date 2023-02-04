package spring.starter.dto.userDetails;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDetailsResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -1644897745902601530L;

    private String username;
}
