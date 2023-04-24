package ru.hse.amaltheateam.currencyrate.feign;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.hse.amaltheateam.currencyrate.dto.request.AllCurrencyRatesRequestDTO;
import ru.hse.amaltheateam.currencyrate.dto.response.CurrencyRateResponseDTO;

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
}
