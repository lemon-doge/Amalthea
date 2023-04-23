package com.tinkoff.sirius.amalthea.controller;

import com.tinkoff.sirius.amalthea.dto.currencyrate.response.CurrencyRateResponseDTO;
import com.tinkoff.sirius.amalthea.service.CurrencyRateService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rates")
public class CurrencyRateController {

    private final CurrencyRateService currencyRateService;

    public CurrencyRateController(CurrencyRateService currencyRateService) {
        this.currencyRateService = currencyRateService;
    }

    @GetMapping
    @Operation(summary = "Get list of currency rates.")
    public List<CurrencyRateResponseDTO> getAllRates() {
        return currencyRateService.findAll();
    }
}
