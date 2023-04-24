package ru.hse.amaltheateam.wallets.web.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hse.amaltheateam.wallets.dto.currency.response.CurrencyRateResponseDTO;
import ru.hse.amaltheateam.wallets.web.feign.CurrencyRatesFeignClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CurrencyRatesFeignService {
    private final CurrencyRatesFeignClient currencyRatesFeignClient;

    public List<CurrencyRateResponseDTO> getAllRates(){
        return currencyRatesFeignClient.getCurrencyRates();
    }
}
