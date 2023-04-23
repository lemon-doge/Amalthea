package com.tinkoff.sirius.amalthea.mapper;

import com.tinkoff.sirius.amalthea.dto.operation.response.OperationResponseDTO;
import com.tinkoff.sirius.amalthea.model.Operation;
import org.springframework.stereotype.Component;

@Component
public class OperationMapper {

    private final CategoryMapper categoryMapper;

    public OperationMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public OperationResponseDTO toResponseDTO(Operation operation) {
        return new OperationResponseDTO()
                .setId(operation.getId())
                .setDate(operation.getDate().getTime())
                .setAmount(operation.getAmount())
                .setCategory(categoryMapper.toResponseDTO(operation.getCategory()));
                //.setCategoryResponseDTO(categoryMapper.toResponseDTO(operation.getCategory()));
    }
}
