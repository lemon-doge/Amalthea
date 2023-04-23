package com.tinkoff.sirius.amalthea.dto.currency.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CurrencyResponseDTO {

    @Schema(example = "1000006")
    private Long id;

    @Schema(example = "RUB")
    private String code;

    @Schema(example = "Российский рубль")
    private String name;
}
