package com.agency04.devcademy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReservationNotPossibleException extends Throwable {

    public ReservationNotPossibleException(String message) {
        super(message);
    }

}
