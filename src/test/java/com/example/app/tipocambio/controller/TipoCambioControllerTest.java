package com.example.app.tipocambio.controller;

import com.example.app.tipocambio.exception.TipoCambioNotFoundException;
import com.example.app.tipocambio.model.TipoCambio;
import com.example.app.tipocambio.service.TipoCambioService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class TipoCambioControllerTest {
    @InjectMocks
    private TipoCambioController tipoCambioController;

    @Mock
    private TipoCambioService tipoCambioService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testActualizarTipoCambioTipoCambioNotFound() {
        TipoCambio tipoCambio = new TipoCambio();
        tipoCambio.setMonedaOrigen("USD");
        tipoCambio.setMonedaDestino("EUR");
        tipoCambio.setTipoCambio(new BigDecimal("1.2"));

        when(tipoCambioService.actualizarTipoCambio(tipoCambio))
                .thenThrow(new TipoCambioNotFoundException("Tipo de cambio no encontrado"));

        assertThrows(TipoCambioNotFoundException.class, () ->
                tipoCambioController.actualizarTipoCambio(tipoCambio));
    }

    @Test
    void testActualizarTipoCambioSuccess() {
        TipoCambio tipoCambio = new TipoCambio();
        tipoCambio.setMonedaOrigen("USD");
        tipoCambio.setMonedaDestino("EUR");
        tipoCambio.setTipoCambio(new BigDecimal("1.2"));

        when(tipoCambioService.actualizarTipoCambio(tipoCambio)).thenReturn(tipoCambio);

        TipoCambio result = tipoCambioController.actualizarTipoCambio(tipoCambio);
        log.info("result " + result);

        assertEquals(tipoCambio, result);
    }
}