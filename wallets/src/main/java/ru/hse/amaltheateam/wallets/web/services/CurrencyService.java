package ru.hse.amaltheateam.wallets.web.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hse.amaltheateam.wallets.dto.currency.response.CurrencyResponseDTO;
import ru.hse.amaltheateam.wallets.model.Currency;
import ru.hse.amaltheateam.wallets.web.mappers.CurrencyMapper;
import ru.hse.amaltheateam.wallets.web.repository.CurrencyRepository;

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
