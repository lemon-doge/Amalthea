package ru.hse.amaltheateam.wallets.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.hse.amaltheateam.wallets.dto.currency.response.CurrencyRateResponseDTO;

import java.util.List;

@FeignClient(name = "amalthea-currency-rates")
public interface CurrencyRatesFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/rates", produces = "application/json")
    @ResponseBody
    List<CurrencyRateResponseDTO> getCurrencyRates();

}
