package com.agency04.devcademy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceededNumberofPersonsException extends Throwable {
    public ExceededNumberofPersonsException() {
        super();
    }

    public ExceededNumberofPersonsException(String message) {
        super(message);
    }

    public ExceededNumberofPersonsException(String message, Throwable cause) {
        super(message, cause);
    }
}
