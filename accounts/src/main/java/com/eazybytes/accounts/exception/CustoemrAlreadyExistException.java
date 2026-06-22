package com.eazybytes.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustoemrAlreadyExistException extends RuntimeException {

    public CustoemrAlreadyExistException(String message) {
        super(message);
    }
}
