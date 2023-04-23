package com.tinkoff.sirius.amalthea.dto.wallet.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class AllWalletsResponseDTO {
    private final List<SingleWalletResponseDTO> wallets;
    @Schema(example = "0")
    private final BigDecimal totalBalance;
    @Schema(example = "0")
    private final BigDecimal totalIncome;
    @Schema(example = "0")
    private final BigDecimal totalExpense;
}
