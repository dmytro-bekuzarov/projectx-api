package com.sind.projectx.rest.controller;

import com.sind.projectx.repository.util.LoggingUtils;
import com.sind.projectx.rest.exception.HttpException;
import com.sind.projectx.rest.exception.RestException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmytro Bekuzarov
 */
@RestControllerAdvice
public class GeneralExceptionHandler {

    private static final Logger LOGGER = LoggingUtils.getLogger();

    @Autowired
    private ExceptionMessageResolver exceptionMessageResolver;

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<RestException> handle(HttpException ex) {
        RestException exception = new RestException();
        exception.setMessageKey(ex.getMessageKey());
        exception.setStatus(ex.getStatus());
        exception.setMessageText(exceptionMessageResolver.getMessageText(ex.getMessageKey(), ex.getParams()));
        return new ResponseEntity<>(exception, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestException> handle(MethodArgumentNotValidException ex) {
        RestException exception = new RestException();
        exception.setMessageKey("error.request.validation");
        exception.setStatus(HttpStatus.BAD_REQUEST);
        exception.setMessageText(exceptionMessageResolver.getMessageText(exception.getMessageKey()));
        exception.setBody(processFieldErrors(ex.getBindingResult().getFieldErrors()));
        return new ResponseEntity<>(exception, exception.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestException> handle(Exception ex) {
        LOGGER.error("Internal server error occurred", ex);
        RestException exception = new RestException();
        exception.setMessageKey("error.internal.server.error");
        exception.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        exception.setMessageText(exceptionMessageResolver.getMessageText(exception.getMessageKey()));
        return new ResponseEntity<>(exception, exception.getStatus());
    }

    private ValidationErrorDto processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDto dto = new ValidationErrorDto();

        for (FieldError fieldError : fieldErrors) {
            String message = exceptionMessageResolver.getMessageText(fieldError);
            dto.addFieldError(fieldError.getField(), message);
        }

        return dto;
    }

    private static class ValidationErrorDto {

        private List<FieldErrorDto> fieldErrors = new ArrayList<>();

        void addFieldError(String path, String message) {
            fieldErrors.add(new FieldErrorDto(path, message));
        }

        public List<FieldErrorDto> getFieldErrors() {
            return fieldErrors;
        }
    }

    private static class FieldErrorDto {

        private String field;
        private String message;

        FieldErrorDto(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }
    }
}
