package com.example.app.tipocambio.repository;

import com.example.app.tipocambio.model.TipoCambio;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@DataJpaTest
public class TipoCambioRepositoryTest {

    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    @Test
    public void testFindByMonedaOrigenAndMonedaDestino() {

        TipoCambio foundTipoCambio = tipoCambioRepository.findByMonedaOrigenAndMonedaDestino("USD", "EUR");
        Optional
                .ofNullable(foundTipoCambio)
                .ifPresent(tipoCambio -> {
                    log.info("tipoCambio {}", tipoCambio);
                    tipoCambioRepository.delete(tipoCambio);
                });

        TipoCambio tipoCambio = new TipoCambio();
        tipoCambio.setMonedaOrigen("USD");
        tipoCambio.setMonedaDestino("EUR");
        tipoCambio.setTipoCambio(new BigDecimal("0.85"));
        tipoCambioRepository.save(tipoCambio);

        TipoCambio foundedTipoCambio = tipoCambioRepository.findByMonedaOrigenAndMonedaDestino("USD", "EUR");

        assertEquals(new BigDecimal("0.85"), foundedTipoCambio.getTipoCambio());
    }
}
