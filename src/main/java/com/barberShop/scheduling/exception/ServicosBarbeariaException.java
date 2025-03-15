package com.barberShop.scheduling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ServicosBarbeariaException extends RuntimeException{
    public ServicosBarbeariaException(String message) {
        super(message);
    }
}
