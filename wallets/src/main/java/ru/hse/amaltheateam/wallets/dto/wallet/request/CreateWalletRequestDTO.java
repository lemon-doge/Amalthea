package ru.hse.amaltheateam.wallets.dto.wallet.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import ru.hse.amaltheateam.wallets.validator.wallet.ValidWalletName;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@Builder
@Schema(description = "DTO 4 creating a wallet.")
public class CreateWalletRequestDTO {
    @ValidWalletName
    @Schema(example = "bomjWallet")
    private final String name;

    @Min(value = 0, message = "limit < 0")
    @Schema(example = "1000008")
    private final Long currencyId;

    @Min(value = 0, message = "limit < 0") // todo new BigDecimal(0)?
    @Schema(example = "100000")
    private final BigDecimal limit;
}
