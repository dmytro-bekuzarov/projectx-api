package com.sind.projectx.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Dmytro Bekuzarov
 */
public class NotFoundException extends HttpException {

    public NotFoundException(String messageKey, String... params) {
        super(HttpStatus.NOT_FOUND, messageKey, params);
    }
}
