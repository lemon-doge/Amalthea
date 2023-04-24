package ru.hse.amaltheateam.currencyrate.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class CurrencyResponseDTO {

    @Schema(example = "1000006")
    private Long id;

    @Schema(example = "RUB")
    private String code;

    @Schema(example = "Российский рубль")
    private String name;
}
