package com.sind.projectx.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Dmytro Bekuzarov
 */
public class BadRequestException extends HttpException{

    public BadRequestException(String messageKey, String... params) {
        super(HttpStatus.BAD_REQUEST, messageKey, params);
    }
}
