package spring.starter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 3652967380577963562L;

    public NotFoundException(String message) {
        super(message);
    }
}