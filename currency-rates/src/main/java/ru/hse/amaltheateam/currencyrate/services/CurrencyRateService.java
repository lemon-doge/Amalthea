package ru.hse.amaltheateam.currencyrate.services;

import org.springframework.stereotype.Service;
import ru.hse.amaltheateam.currencyrate.dto.response.CurrencyRateResponseDTO;
import ru.hse.amaltheateam.currencyrate.feign.CurrencyRateClient;

import java.time.LocalDate;
import java.util.List;

@Service
public class CurrencyRateService {

    public List<CurrencyRateResponseDTO> findAll() {
        CurrencyRateClient currencyRateClient = new CurrencyRateClient();
        return currencyRateClient.getCurrencyRates(LocalDate.now());
    }
}
