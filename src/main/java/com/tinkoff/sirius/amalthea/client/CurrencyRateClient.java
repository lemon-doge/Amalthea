package com.tinkoff.sirius.amalthea.client;

import com.tinkoff.sirius.amalthea.dto.currency.response.CurrencyResponseDTO;
import com.tinkoff.sirius.amalthea.dto.currencyrate.request.AllCurrencyRatesRequestDTO;
import com.tinkoff.sirius.amalthea.dto.currencyrate.response.CurrencyRateResponseDTO;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CurrencyRateClient {
    private static final String URL = "https://cbr.ru/scripts/XML_daily.asp?date_req=%s";
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private final RestTemplate restTemplate = new RestTemplate();


    public List<CurrencyRateResponseDTO> getCurrencyRates(LocalDate localDate) {
        String dateParameter = localDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));

        String requestUrl = String.format(URL, dateParameter);

        AllCurrencyRatesRequestDTO response = restTemplate.getForObject(requestUrl, AllCurrencyRatesRequestDTO.class);

        if (response != null) {
            response.getCurrencyRateRequestDTOs()
                    .forEach(rate -> rate.setValue(
                            new BigDecimal(rate.getValueString().replace(",", "."))
                    ));

            return response.getCurrencyRateRequestDTOs()
                    .stream()
                    .map(x -> new CurrencyRateResponseDTO()
                            .setCharCode(x.getCharCode())
                            .setValue(x.getValue()))
                    .toList();
        }

        // TODO: Return latest successful response.
        throw new RestClientException("Failed to load currency rates");
    }

    public List<CurrencyResponseDTO> getCurrencies(LocalDate localDate) {
        String dateParameter = localDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));

        String requestUrl = String.format(URL, dateParameter);

        AllCurrencyRatesRequestDTO response = restTemplate.getForObject(requestUrl, AllCurrencyRatesRequestDTO.class);

        if (response != null) {
            return response.getCurrencyRateRequestDTOs()
                    .stream()
                    .map(x -> new CurrencyResponseDTO()
                            .setName(x.getName())
                            .setCode(x.getCharCode()))
                    .toList();
        }

        // TODO: Return latest successful response.
        throw new RestClientException("Failed to load currency rates");
    }
}
