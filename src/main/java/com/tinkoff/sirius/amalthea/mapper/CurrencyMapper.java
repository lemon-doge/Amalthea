package com.tinkoff.sirius.amalthea.mapper;

import com.tinkoff.sirius.amalthea.dto.currency.response.CurrencyResponseDTO;
import com.tinkoff.sirius.amalthea.model.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapper {

    public CurrencyResponseDTO toResponseDTO(Currency currency) {
        return new CurrencyResponseDTO()
                .setId(currency.getId())
                .setCode(currency.getCode())
                .setName(currency.getName());
    }
}
