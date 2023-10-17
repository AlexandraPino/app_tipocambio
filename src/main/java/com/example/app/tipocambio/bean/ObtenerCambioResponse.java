package com.example.app.tipocambio.bean;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ObtenerCambioResponse {
    private BigDecimal monto;
    private BigDecimal montoTipoCambio;
    private String monedaOrigen;
    private String monedaDestino;
    private BigDecimal tipoCambio;
}
