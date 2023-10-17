package com.example.app.tipocambio.controller;

import com.example.app.tipocambio.exception.TipoCambioNotFoundException;
import com.example.app.tipocambio.bean.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(TipoCambioNotFoundException.class)
    public ApiError handleTipoCambioNotFoundException(TipoCambioNotFoundException ex) {
        ApiError error = new ApiError();
        error.setCode(HttpStatus.NOT_FOUND.name());
        error.setMessage(ex.getMessage());
        return error;
    }
}