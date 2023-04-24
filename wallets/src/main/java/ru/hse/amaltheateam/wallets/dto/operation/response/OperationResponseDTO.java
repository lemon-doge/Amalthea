package ru.hse.amaltheateam.wallets.dto.operation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.hse.amaltheateam.wallets.dto.category.response.CategoryResponseDTO;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OperationResponseDTO {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "1660297184")
    private Long date;

    @Schema(example = "1500.99")
    private BigDecimal amount;

    private CategoryResponseDTO category;
}
