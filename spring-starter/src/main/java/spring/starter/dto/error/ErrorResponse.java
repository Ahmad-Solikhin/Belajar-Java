package spring.starter.dto.error;

import lombok.Data;
import org.springframework.http.HttpStatus;
import spring.starter.enums.ErrorCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -5074679466127073978L;
    private LocalDateTime timestamp;
    private String message;
    private ErrorCode errorCode;
    private List<String> details;
    private HttpStatus status;

    public static ErrorResponse of(final String message, List<String> details, final ErrorCode errorCode, HttpStatus status){
        return new ErrorResponse(message, errorCode, details, status);
    }

    public ErrorResponse(String message, ErrorCode errorCode, List<String> details, HttpStatus status) {
        this.message = message;
        this.errorCode = errorCode;
        this.details = details;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
