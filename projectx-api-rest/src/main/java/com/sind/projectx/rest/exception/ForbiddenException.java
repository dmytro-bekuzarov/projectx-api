package com.sind.projectx.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Dmytro Bekuzarov
 */
public class ForbiddenException extends HttpException {

    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN, "error.access.forbidden");
    }
}
