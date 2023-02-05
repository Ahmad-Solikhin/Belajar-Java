package spring.starter.dto.author;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import spring.starter.dto.address.AddressAddRequest;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthorAddRequest {

    @NotBlank
    private String authorName;
    @NotNull
    private LocalDate birthDate;

    private List<AddressAddRequest> addresses;

}
