package spring.starter.dto.book;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class BookAddRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -5043724075670445812L;
    /**
     * Mengubah isian pada payload yang tidak mengikuti nama variablenya
     * Bisa pake cara binding satu-satu ke variable : @JsonProperty("namaVariable")
     * kalo mau semua property yang ada diubah jadi snake case bisa pake : @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
     * */

    @NotBlank
    private String bookTitle;
    @NotEmpty
    private List<String> authorIdList;
    private List<String> categoryList;
    @NotBlank
    private String publisherId;
    private String bookDescription;
}
