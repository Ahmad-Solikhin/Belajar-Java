package spring.starter.dto.author;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import spring.starter.dto.address.AddressUpdateRequest;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthorUpdateRequest {

    private String authorName;
    private LocalDate birthDate;
    private List<AddressUpdateRequest> addresses;

}
