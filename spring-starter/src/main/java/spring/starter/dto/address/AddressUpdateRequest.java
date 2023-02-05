package spring.starter.dto.address;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressUpdateRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 4971470038829909260L;

    private Integer addressId;

    private String streetName;

    private String cityName;

    private String zipCode;

}
