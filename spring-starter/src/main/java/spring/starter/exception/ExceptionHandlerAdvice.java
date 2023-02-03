package spring.starter.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import spring.starter.dto.error.ErrorResponse;
import spring.starter.enums.ErrorCode;

import java.util.List;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handlerResourceNotFound(NotFoundException ex, WebRequest request){
        List<String> details = List.of(ex.getMessage());
        ErrorResponse response = ErrorResponse.of("Not Found", details, ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND);
        return ResponseEntity.badRequest().body(response);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> details = ex.getBindingResult().getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        ErrorResponse response = ErrorResponse.of("Invalid Data", details, ErrorCode.INVALID_DATA, HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(response);
    }


}
