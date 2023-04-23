package com.tinkoff.sirius.amalthea.controller;

import com.tinkoff.sirius.amalthea.dto.category.request.CategoryRequestDTO;
import com.tinkoff.sirius.amalthea.dto.category.response.CategoryResponseDTO;
import com.tinkoff.sirius.amalthea.exception.CategoryDeleteException;
import com.tinkoff.sirius.amalthea.exception.CategoryNotFoundException;
import com.tinkoff.sirius.amalthea.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/{userId}/categories")
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Create category.")
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> postCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO,
                                                            @PathVariable Long userId) {
        return new ResponseEntity<>(categoryService.save(categoryRequestDTO, userId), HttpStatus.OK);
    }

    @Operation(summary = "Get list of all categories.")
    @GetMapping
    public List<CategoryResponseDTO> getAllCategories(@PathVariable Long userId) {
        return categoryService.findAllByUserId(userId);
    }

    @Operation(summary = "Delete category by id.")
    @DeleteMapping(value = "/{id}")
    public Long deleteCategory(@PathVariable("id") Long id) throws CategoryDeleteException {
        return categoryService.deleteById(id);
    }

    @Operation(summary = "Update category by id.")
    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO,
                                                              @PathVariable Long id) throws CategoryNotFoundException {
        return new ResponseEntity<>(categoryService.update(categoryRequestDTO, id), HttpStatus.OK);
    }
}
