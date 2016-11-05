package com.sind.projectx.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Dmytro Bekuzarov
 */
public class RestException {

    private HttpStatus status;
    private String messageKey;
    private String messageText;
    private Object body;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
