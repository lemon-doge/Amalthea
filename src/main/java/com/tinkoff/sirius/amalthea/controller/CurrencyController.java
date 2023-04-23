package com.tinkoff.sirius.amalthea.controller;

import com.tinkoff.sirius.amalthea.dto.currency.response.CurrencyResponseDTO;
import com.tinkoff.sirius.amalthea.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    @Operation(summary = "Get list of all currencies.")
    public List<CurrencyResponseDTO> getAllCurrencies() {
        return currencyService.findAll();
    }

}
