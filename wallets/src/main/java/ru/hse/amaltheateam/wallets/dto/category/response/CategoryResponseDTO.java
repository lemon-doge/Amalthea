package ru.hse.amaltheateam.wallets.dto.category.response;

import com.tinkoff.sirius.amalthea.model.CategoryType;
import lombok.*;
import lombok.experimental.Accessors;

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
