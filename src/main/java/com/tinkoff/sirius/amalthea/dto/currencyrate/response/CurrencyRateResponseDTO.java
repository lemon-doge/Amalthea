package com.tinkoff.sirius.amalthea.dto.currencyrate.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CurrencyRateResponseDTO {

    private String charCode;
    private BigDecimal value;
}
