package com.example.app.tipocambio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TipoCambioNotFoundException extends RuntimeException{
    public TipoCambioNotFoundException(String message){
        super(message);
    }
}
