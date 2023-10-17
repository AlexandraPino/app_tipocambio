package com.example.app.tipocambio.controller;

import com.example.app.tipocambio.bean.ObtenerCambioResponse;
import com.example.app.tipocambio.model.TipoCambio;
import com.example.app.tipocambio.service.TipoCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class TipoCambioController {

    @Autowired
    private TipoCambioService tipoCambioService;

    @GetMapping(value = "/calcularTipoCambio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ObtenerCambioResponse calcularTipoCambio(@RequestParam("monto") BigDecimal monto,
                                               @RequestParam("monedaOrigen") String monedaOrigen,
                                               @RequestParam("monedaDestino") String monedaDestino){
        return tipoCambioService.calcularTipoCambio(monto, monedaOrigen, monedaDestino);
    }

    @PostMapping(value = "/actualizarTipoCambio", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    TipoCambio actualizarTipoCambio(@RequestBody TipoCambio tipoCambio){
        return tipoCambioService.actualizarTipoCambio(tipoCambio);
    }
}
