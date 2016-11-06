package com.sind.projectx.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Dmytro Bekuzarov
 */
public class InvalidFieldException extends HttpException {

    public InvalidFieldException(String fieldName, String fieldValue) {
        super(HttpStatus.BAD_REQUEST, "error.invalid.field", fieldName, fieldValue);
    }

}
