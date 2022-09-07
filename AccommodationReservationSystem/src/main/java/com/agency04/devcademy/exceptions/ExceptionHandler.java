package com.agency04.devcademy.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request) {
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({NumberFormatException.class})
    public ResponseEntity<Object> handleNumberFormatException(Exception exception, WebRequest request) {
        return new ResponseEntity<>("Bad request - Number Format Exception", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({IndexOutOfBoundsException.class})
    public ResponseEntity<Object> handleIndexOutOfBoundsException(Exception exception, WebRequest request) {
        return new ResponseEntity<>("Bad request - Index out of bounds exception", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({ExceededNumberofPersonsException.class})
    public ResponseEntity<Object> ExceededNumberofPersonsException(Exception exception, WebRequest request) {
        return new ResponseEntity<>("Bad request - Selected person count bigger then accommodation person count", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

   @org.springframework.web.bind.annotation.ExceptionHandler({IOException.class})
    public ResponseEntity<Object> IOException(Exception exception, WebRequest request) {
        return new ResponseEntity<>("Bad request - Error occurred while saving image file", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({ReservationNotPossibleException.class})
    public ResponseEntity<Object> ReservationNotPossibleException(Exception exception, WebRequest request) {
        return new ResponseEntity<Object>("Reservation not possible - invalid input",  new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
