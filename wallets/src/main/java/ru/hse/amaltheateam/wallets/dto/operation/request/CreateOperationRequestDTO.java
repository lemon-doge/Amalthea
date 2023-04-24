package ru.hse.amaltheateam.wallets.dto.operation.request;

import com.tinkoff.sirius.amalthea.dto.category.request.CategoryRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CreateOperationRequestDTO {

    @Schema(example = "1500.99")
    @Digits(integer = 10, fraction = 2)
    @Min(value = 0)
    private BigDecimal amount;

    @Schema(example = "1660297184")
    private Long date;

    @Schema(example = "1")
    @Min(value = 1)
    private Long walletId;

    @Schema(example = "1")
    @Min(value = 1)
    private Long categoryId;
}
