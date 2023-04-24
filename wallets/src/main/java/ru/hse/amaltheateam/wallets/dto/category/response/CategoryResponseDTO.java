package ru.hse.amaltheateam.wallets.dto.category.response;

import lombok.*;
import lombok.experimental.Accessors;
import ru.hse.amaltheateam.wallets.model.CategoryType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CategoryResponseDTO {
    private Long id;
    private CategoryType type;
    private String name;
    private String iconName;
    private String iconColor;
}
