package ru.hse.amaltheateam.wallets.web.mappers;

import org.springframework.stereotype.Component;
import ru.hse.amaltheateam.wallets.dto.category.response.CategoryResponseDTO;
import ru.hse.amaltheateam.wallets.model.Category;

@Component
public class CategoryMapper {

    public CategoryResponseDTO toResponseDTO(Category category) {
        return new CategoryResponseDTO()
                .setId(category.getId())
                .setType(category.getType())
                .setName(category.getName())
                .setIconName(category.getIconName())
                .setIconColor(category.getIconColor());
    }
}
