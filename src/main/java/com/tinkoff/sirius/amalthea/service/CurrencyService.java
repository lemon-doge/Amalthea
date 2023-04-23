package com.tinkoff.sirius.amalthea.service;

import com.tinkoff.sirius.amalthea.dto.currency.response.CurrencyResponseDTO;
import com.tinkoff.sirius.amalthea.mapper.CurrencyMapper;
import com.tinkoff.sirius.amalthea.model.Currency;
import com.tinkoff.sirius.amalthea.repository.CurrencyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    public CurrencyService(CurrencyRepository currencyRepository, CurrencyMapper currencyMapper) {
        this.currencyRepository = currencyRepository;
        this.currencyMapper = currencyMapper;
    }

    @Transactional(readOnly = true)
    public Currency findById(Long id) {
        return currencyRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Currency not found: " + id));
    }

    public List<CurrencyResponseDTO> findAll() {
        return currencyRepository.findAll().stream().map(currencyMapper::toResponseDTO).toList();
    }
}
