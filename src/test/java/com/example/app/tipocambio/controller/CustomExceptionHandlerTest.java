package com.example.app.tipocambio.controller;

import com.example.app.tipocambio.exception.TipoCambioNotFoundException;
import com.example.app.tipocambio.bean.ApiError;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomExceptionHandlerTest {

    @Test
    void handleTipoCambioNotFoundException() {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();

        TipoCambioNotFoundException ex = new TipoCambioNotFoundException("Tipo de cambio no encontrado");
        ApiError apiError = customExceptionHandler.handleTipoCambioNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND.name(), apiError.getCode());
        assertEquals("Tipo de cambio no encontrado", apiError.getMessage());
    }
}