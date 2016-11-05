package com.sind.projectx.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Dmytro Bekuzarov
 */
public class HttpException extends RuntimeException {

    private HttpStatus status;
    private String messageKey;
    private String[] params;

    public HttpException(HttpStatus status, String messageKey, String... params) {
        this.status = status;
        this.messageKey = messageKey;
        this.params = params;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String[] getParams() {
        return params;
    }
}
