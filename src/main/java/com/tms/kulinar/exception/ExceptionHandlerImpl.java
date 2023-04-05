package com.tms.kulinar.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.sql.Timestamp;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerImpl {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

/*    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomException.class)
    public String exceptionNullPointer(CustomException e) {
        log.error(e.getMessage()+"Feedback not found");
        return "unsuccess";
    }*/
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND,
                Timestamp.valueOf(LocalDateTime.now()),
                exception.getMessage(),
                exception
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<ErrorMessage> createException(CustomException exception) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR,
                Timestamp.valueOf(LocalDateTime.now()),
                exception.getMessage(),
                exception
        );
        log.error(exception.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception exception) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR,
                Timestamp.valueOf(LocalDateTime.now()),
                exception.getMessage(),
                exception);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
