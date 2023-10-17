package com.example.app.tipocambio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.example.app.tipocambio.bean.ObtenerCambioResponse;
import com.example.app.tipocambio.exception.TipoCambioNotFoundException;
import com.example.app.tipocambio.model.TipoCambio;
import com.example.app.tipocambio.repository.TipoCambioRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;

@Slf4j
@ExtendWith(MockitoExtension.class)
class TipoCambioServiceImplTest {

    @Mock
    private TipoCambioRepository tipoCambioRepository;

    @InjectMocks
    private TipoCambioServiceImpl tipoCambioService;

    @Test
    public void calcularTipoCambioSuccess() {
        // Configuración de datos de prueba
        String monedaOrigen = "USD";
        String monedaDestino = "EUR";
        BigDecimal monto = new BigDecimal("100");

        TipoCambio tipoCambio = new TipoCambio();
        tipoCambio.setMonedaOrigen("USD");
        tipoCambio.setMonedaDestino("EUR");
        tipoCambio.setTipoCambio(new BigDecimal("1.2"));

        mockTipoCambioRepository(monedaOrigen, monedaDestino, tipoCambio);

        ObtenerCambioResponse response = tipoCambioService.calcularTipoCambio(monto, monedaOrigen, monedaDestino);

        assertEquals(monto, response.getMonto());
        assertEquals(monto.multiply(tipoCambio.getTipoCambio()), response.getMontoTipoCambio());
        assertEquals(monedaOrigen, response.getMonedaOrigen());
        assertEquals(monedaDestino, response.getMonedaDestino());
        assertEquals(tipoCambio.getTipoCambio(), response.getTipoCambio());
    }

    @Test
    public void testCalcularTipoCambioTipoCambioNotFound() {
        String monedaOrigen = "USD";
        String monedaDestino = "EUR";
        BigDecimal monto = new BigDecimal("100");

        mockTipoCambioRepository(monedaOrigen, monedaDestino, null);

        assertThrows(TipoCambioNotFoundException.class, () -> {
            tipoCambioService.calcularTipoCambio(monto, monedaOrigen, monedaDestino);
        });
    }

    @Test
    public void testActualizarTipoCambioTipoCambioNotFound() {
        TipoCambio request = new TipoCambio();
        request.setMonedaOrigen("USD");
        request.setMonedaDestino("EUR");
        request.setTipoCambio(new BigDecimal("1.2"));

        mockTipoCambioRepository(request.getMonedaOrigen(), request.getMonedaDestino(), null);

        assertThrows(TipoCambioNotFoundException.class, () -> {
            tipoCambioService.actualizarTipoCambio(request);
        });
    }

    @Test
    public void testActualizarTipoCambioTipoCambioFound() {
        // Configuración de datos de prueba
        TipoCambio request = new TipoCambio();
        request.setMonedaOrigen("USD");
        request.setMonedaDestino("EUR");
        request.setTipoCambio(new BigDecimal("1.2"));

        TipoCambio tipoCambio = new TipoCambio();
        tipoCambio.setMonedaOrigen("USD");
        tipoCambio.setMonedaDestino("EUR");
        tipoCambio.setTipoCambio(new BigDecimal("1.0")); // Valor original en la base de datos

        mockTipoCambioRepository(request.getMonedaOrigen(), request.getMonedaDestino(), tipoCambio);

        when(tipoCambioRepository.save(any())).thenReturn(tipoCambio);

        TipoCambio resultado = tipoCambioService.actualizarTipoCambio(request);
        assertEquals(request.getTipoCambio(), resultado.getTipoCambio());
    }

    private void mockTipoCambioRepository(String monedaOrigen, String monedaDestino, TipoCambio tipoCambio) {
        when(tipoCambioRepository.findByMonedaOrigenAndMonedaDestino(monedaOrigen, monedaDestino)).thenReturn(tipoCambio);
    }
}