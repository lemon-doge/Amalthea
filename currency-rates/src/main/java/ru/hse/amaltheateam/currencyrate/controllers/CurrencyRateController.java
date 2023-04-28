package ru.hse.amaltheateam.currencyrate.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.amaltheateam.currencyrate.dto.response.CurrencyRateResponseDTO;
import ru.hse.amaltheateam.currencyrate.services.CurrencyRateService;

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
