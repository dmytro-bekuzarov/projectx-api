package com.sind.projectx.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Dmytro Bekuzarov
 */
public class FieldMissingException extends HttpException {

    public FieldMissingException(String fieldName) {
        super(HttpStatus.BAD_REQUEST, "error.request.body.field.missing", fieldName);
    }
}
