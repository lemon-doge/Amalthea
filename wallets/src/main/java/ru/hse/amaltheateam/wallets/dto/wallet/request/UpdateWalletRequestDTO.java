package ru.hse.amaltheateam.wallets.dto.wallet.request;

import com.tinkoff.sirius.amalthea.validator.wallet.ValidWalletName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Builder
@Schema(description = "DTO 4 updating a wallet. Save a copy of SingleWalletResponseDTO, change fields as needed, send this back.")
public class UpdateWalletRequestDTO {
    @ValidWalletName
    @Schema(example = "bomjWallet")
    private String name;

//    //todo: validate currency id
//    @Schema(example = "1000008")
//    private Long currencyId;

    @Min(value = 0, message = "limit < 0")
    @Schema(example = "100000")
    private BigDecimal limit;

//    @Min(value = 0, message = "balance < 0")
//    @Schema(example = "0")
//    private BigDecimal balance;
//
//    @Min(value = 0, message = "total income < 0")
//    @Schema(example = "0")
//    private BigDecimal income;
//
//    @Min(value = 0, message = "total expense < 0")
//    @Schema(example = "0")
//    private BigDecimal expense;

//    @Schema(example = "1000001")
//    private Long userId;
}
