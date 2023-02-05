package spring.starter.dto.address;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressAddRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -8190370124814295935L;
    private String streetName;

    private String cityName;

    private String zipCode;

}
