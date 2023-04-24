package ru.hse.amaltheateam.wallets.dto.wallet.response;

import com.tinkoff.sirius.amalthea.dto.currency.response.CurrencyResponseDTO;
import com.tinkoff.sirius.amalthea.model.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SingleWalletResponseDTO {
    @Schema(example = "1000001")
    private final Long id;
    @Schema(example = "bomj_wallet")
    private final String name;
    private final CurrencyResponseDTO currency;
    @Schema(example = "10000")
    private final BigDecimal limit;
    @Schema(example = "0")
    private final BigDecimal balance;
    @Schema(example = "0")
    private final BigDecimal income;
    @Schema(example = "0")
    private final BigDecimal expense;
//    private Long userId;
}
