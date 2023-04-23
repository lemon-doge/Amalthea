package com.tinkoff.sirius.amalthea.service;

import com.tinkoff.sirius.amalthea.client.CurrencyRateClient;
import com.tinkoff.sirius.amalthea.dto.currencyrate.response.CurrencyRateResponseDTO;
import com.tinkoff.sirius.amalthea.mapper.CurrencyRateMapper;
import com.tinkoff.sirius.amalthea.repository.CurrencyRateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CurrencyRateService {

    /*private final CurrencyRateRepository currencyRateRepository;
    private final CurrencyRateMapper currencyRateMapper;

    public CurrencyRateService(CurrencyRateRepository currencyRateRepository, CurrencyRateMapper currencyRateMapper) {
        this.currencyRateRepository = currencyRateRepository;
        this.currencyRateMapper = currencyRateMapper;
    }*/

    @Transactional(readOnly = true)
    public List<CurrencyRateResponseDTO> findAll() {
        CurrencyRateClient currencyRateClient = new CurrencyRateClient();
        return currencyRateClient.getCurrencyRates(LocalDate.now());

        //return currencyRateRepository.findAll().stream().map(currencyRateMapper::toResponseDTO).toList();
    }
}
