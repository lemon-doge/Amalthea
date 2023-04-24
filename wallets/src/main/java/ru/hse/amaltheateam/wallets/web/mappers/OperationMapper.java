package ru.hse.amaltheateam.wallets.web.mappers;

import org.springframework.stereotype.Component;
import ru.hse.amaltheateam.wallets.dto.operation.response.OperationResponseDTO;
import ru.hse.amaltheateam.wallets.model.Operation;

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
