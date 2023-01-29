package spring.starter.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class AddBookDTO {
    /**
     * Mengubah isian pada payload yang tidak mengikuti nama variablenya
     * Bisa pake cara binding satu-satu ke variable : @JsonProperty("namaVariable")
     * kalo mau semua property yang ada diubah jadi snake case bisa pake : @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
     * */

    @NotBlank
    private String bookTitle;
    @NotBlank
    private String authorName;
    private String bookDescription;
}
