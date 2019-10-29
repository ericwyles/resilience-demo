package com.ericwyles.resiliencedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> processException(final Exception e, final HttpServletRequest request) {
        String message = String.format("Request for uri [%s] threw an exception and was caught by the ExceptionHandler", request.getRequestURI());
        log.error(message,  e);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
