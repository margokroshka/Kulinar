package com.tms.kulinar.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerImpl {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FeedbackNotFoundException.class)
    public String exceptionNullPointer(FeedbackNotFoundException e) {
        log.error(e.getMessage()+"Feedback not found");
        return "unsuccess";
    }
}
