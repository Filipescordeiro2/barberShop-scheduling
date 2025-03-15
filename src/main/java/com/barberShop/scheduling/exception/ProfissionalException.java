package com.barberShop.scheduling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProfissionalException extends RuntimeException{
    public ProfissionalException(String message) {
        super(message);
    }
}
