package ru.hse.amaltheateam.wallets.dto.category.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.hse.amaltheateam.wallets.model.CategoryType;
import ru.hse.amaltheateam.wallets.validator.category.ValidCategoryIconColor;
import ru.hse.amaltheateam.wallets.validator.category.ValidCategoryName;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CategoryRequestDTO {

    @Schema(example = "EXPENSE")
    private CategoryType type;

    @ValidCategoryName
    @Schema(example = "Супермаркеты")
    private String name;

    @ValidCategoryIconColor
    @Schema(example = "#123456")
    private String iconColor;

    // Source will always be CUSTOM (add setSource in service)

    @Schema(example = "resources/lol.png")
    private String iconPath;
}
