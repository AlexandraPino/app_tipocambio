package com.example.app.tipocambio.service;

import com.example.app.tipocambio.bean.ObtenerCambioResponse;
import com.example.app.tipocambio.exception.TipoCambioNotFoundException;
import com.example.app.tipocambio.model.TipoCambio;
import com.example.app.tipocambio.repository.TipoCambioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@Service
public class TipoCambioServiceImpl implements TipoCambioService{

    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    @Override
    public ObtenerCambioResponse calcularTipoCambio(BigDecimal monto, String monedaOrigen, String monedaDestino) {

        ObtenerCambioResponse response = new ObtenerCambioResponse();
        TipoCambio tipoCambio = tipoCambioRepository.findByMonedaOrigenAndMonedaDestino(monedaOrigen, monedaDestino);
        log.info("tipoCambio: " + tipoCambio);

        if(Objects.nonNull(tipoCambio)){
            response.setMonto(monto);
            response.setMontoTipoCambio(monto.multiply(tipoCambio.getTipoCambio()));
            response.setMonedaOrigen(tipoCambio.getMonedaOrigen());
            response.setMonedaDestino(tipoCambio.getMonedaDestino());
            response.setTipoCambio(tipoCambio.getTipoCambio());
        }else{
            log.info("Tipo de cambio no registrado");
            throw new TipoCambioNotFoundException("Tipo de cambio no encontrado para " + monedaOrigen + " a " + monedaDestino);
        }

        return response;
    }

    @Override
    public TipoCambio actualizarTipoCambio(TipoCambio request) {

        TipoCambio tipoCambio = tipoCambioRepository.findByMonedaOrigenAndMonedaDestino(request.getMonedaOrigen(), request.getMonedaDestino());
        log.info("tipoCambio " + tipoCambio);

        if(Objects.nonNull(tipoCambio)){
            tipoCambio.setId(tipoCambio.getId());
            tipoCambio.setMonedaOrigen(tipoCambio.getMonedaOrigen());
            tipoCambio.setMonedaDestino(tipoCambio.getMonedaDestino());
            tipoCambio.setTipoCambio(request.getTipoCambio());
            return tipoCambioRepository.save(tipoCambio);
        }else{
            throw new TipoCambioNotFoundException("Tipo de cambio no encontrado para " + request.getMonedaOrigen() + " a " + request.getMonedaDestino());
        }
    }
}
