package spring.starter.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResultPaginationResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 7584004017713209311L;
    private List<T> result;
    
    private Long page;
    
    private Long elements;
}
