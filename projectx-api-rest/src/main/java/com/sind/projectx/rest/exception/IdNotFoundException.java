package com.sind.projectx.rest.exception;

/**
 * @author Dmytro Bekuzarov
 */
public class IdNotFoundException extends NotFoundException {

    public IdNotFoundException() {
        super("error.id.not.found");
    }
}
