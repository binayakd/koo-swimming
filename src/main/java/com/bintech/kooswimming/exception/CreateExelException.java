package com.bintech.kooswimming.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CreateExelException extends RuntimeException {
    public CreateExelException(String message) {
        super(message);
    }

    public CreateExelException(String message, Throwable cause) {
        super(message, cause);
        cause.printStackTrace();
    }
}
