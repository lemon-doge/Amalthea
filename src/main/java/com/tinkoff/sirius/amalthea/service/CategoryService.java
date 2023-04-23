package com.tinkoff.sirius.amalthea.service;

import com.tinkoff.sirius.amalthea.dto.category.request.CategoryRequestDTO;
import com.tinkoff.sirius.amalthea.dto.category.response.CategoryResponseDTO;
import com.tinkoff.sirius.amalthea.exception.CategoryDeleteException;
import com.tinkoff.sirius.amalthea.exception.CategoryNotFoundException;
import com.tinkoff.sirius.amalthea.mapper.CategoryMapper;
import com.tinkoff.sirius.amalthea.model.Category;
import com.tinkoff.sirius.amalthea.model.Source;
import com.tinkoff.sirius.amalthea.model.User;
import com.tinkoff.sirius.amalthea.repository.CategoryRepository;
import com.tinkoff.sirius.amalthea.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper,
                           UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> findAllByUserId(Long userId) {
        return categoryRepository.findAllByUserId(userId).stream().sorted((o1, o2) -> {
            if (o1.getSource() == Source.SYSTEM) {
                if (o2.getSource() == Source.SYSTEM) {
                    // o1 - SYSTEM, o2 - SYSTEM (lexicographic order).
                    return o1.getName().compareTo(o2.getName());
                }
                // o1 - SYSTEM, o2 - CUSTOM.
                return 1;
            } else if (o2.getSource() == Source.SYSTEM) {
                // o1 - CUSTOM, o2 - SYSTEM.
                return -1;
            }
            // o1 - CUSTOM, o2 - CUSTOM (lexicographic order).
            return o2.getName().compareTo(o1.getName());
        }).map(categoryMapper::toResponseDTO).toList();
    }

    /*@Transactional(readOnly = true)
    public CategoryResponseDTO findByTypeAndName(CategoryType categoryType, String name)
            throws CategoryNotFoundException {
        return categoryMapper.toResponseDTO(categoryRepository.findByTypeAndName(categoryType, name).orElseThrow(
                () -> new CategoryNotFoundException(
                        String.format("Категория \"%s\" типа \"%s\" не существует", name, categoryType.name())
                )
        ));
    }*/

    @Transactional
    public Long deleteById(Long id) throws CategoryDeleteException {
        Category category = categoryRepository.findById(id).orElseThrow();
        if (category.getSource() == Source.SYSTEM) {
            throw new CategoryDeleteException("Access to delete the source category is denied");
        }

        categoryRepository.deleteById(id);
        return id;
    }

    @Transactional
    public CategoryResponseDTO save(CategoryRequestDTO categoryRequestDTO, Long id) {
        User user = userRepository.findById(id).orElseThrow();

        String iconPath = categoryRequestDTO.getIconPath();

        Category category = new Category()
                .setType(categoryRequestDTO.getType())
                .setName(categoryRequestDTO.getName())
                .setIconColor(categoryRequestDTO.getIconColor())
                .setSource(Source.CUSTOM)
                .setIconName(iconPath)
                .setUser(user);

        return categoryMapper.toResponseDTO(categoryRepository.save(category));
    }

    @Transactional
    public CategoryResponseDTO update(CategoryRequestDTO categoryRequestDTO, Long id)
            throws CategoryNotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        Category category = categoryOptional.orElseThrow(() -> new CategoryNotFoundException("Category not found: " + id));

        category.setName(categoryRequestDTO.getName())
                .setType(categoryRequestDTO.getType())
                .setIconName(category.getIconName());

        return categoryMapper.toResponseDTO(categoryRepository.save(category));
    }
}