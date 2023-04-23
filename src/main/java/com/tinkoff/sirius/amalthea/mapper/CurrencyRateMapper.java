package com.tinkoff.sirius.amalthea.mapper;

import com.tinkoff.sirius.amalthea.dto.currencyrate.response.CurrencyRateResponseDTO;
import com.tinkoff.sirius.amalthea.model.CurrencyRate;
import org.springframework.stereotype.Component;

@Component
public class CurrencyRateMapper {

    public CurrencyRateResponseDTO toResponseDTO(CurrencyRate currencyRate) {
        return new CurrencyRateResponseDTO()
                .setCharCode(currencyRate.getCurrency().getCode())
                .setValue(currencyRate.getRate());
    }
}
