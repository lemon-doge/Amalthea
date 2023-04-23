package com.tinkoff.sirius.amalthea.mapper;

import com.tinkoff.sirius.amalthea.dto.category.response.CategoryResponseDTO;
import com.tinkoff.sirius.amalthea.model.Category;
import org.springframework.stereotype.Component;

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
