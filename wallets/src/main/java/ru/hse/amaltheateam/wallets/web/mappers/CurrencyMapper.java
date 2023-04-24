package ru.hse.amaltheateam.wallets.web.mappers;

import org.springframework.stereotype.Component;
import ru.hse.amaltheateam.wallets.dto.currency.response.CurrencyResponseDTO;
import ru.hse.amaltheateam.wallets.model.Currency;

@Component
public class CurrencyMapper {

    public CurrencyResponseDTO toResponseDTO(Currency currency) {
        return new CurrencyResponseDTO()
                .setId(currency.getId())
                .setCode(currency.getCode())
                .setName(currency.getName());
    }
}
