package com.example.app.tipocambio.service;

import com.example.app.tipocambio.bean.ObtenerCambioResponse;
import com.example.app.tipocambio.model.TipoCambio;
import java.math.BigDecimal;

public interface TipoCambioService{
    ObtenerCambioResponse calcularTipoCambio(BigDecimal monto, String monedaOrigen, String monedaDestino);
    TipoCambio actualizarTipoCambio(TipoCambio tipoCambio);
}
