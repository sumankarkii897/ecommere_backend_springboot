package com.sumankarki.Ecommerce.service.impl;

import com.sumankarki.Ecommerce.dto.CategoryDto;
import com.sumankarki.Ecommerce.dto.Response;
import com.sumankarki.Ecommerce.entity.Category;
import com.sumankarki.Ecommerce.exception.NotFoundException;
import com.sumankarki.Ecommerce.mapper.EntityDtoMapper;
import com.sumankarki.Ecommerce.repository.CategoryRepository;
import com.sumankarki.Ecommerce.repository.UserRepository;
import com.sumankarki.Ecommerce.service.interf.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Response createCategory(CategoryDto categoryDto) {
        Category category = Category.builder()
                .name(categoryDto.getName())
                .build();
        categoryRepository.save(category);


return Response.builder()
        .status(200)
        .message("Category created successfully")
        .build();
    }

    @Override
    public Response updateCategory(Long categoryId,CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not found"));
        category.setName(categoryDto.getName());
        categoryRepository.save(category);


        return Response.builder()
                .status(200)
                .message("Category updated successfully")

                .build();
    }

    @Override
    public Response deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new NotFoundException("Category not found"));
        categoryRepository.delete(category);
        return Response.builder()
                .status(200)
                .message("Category deleted successfully")
                .build();
    }

    @Override
    public Response getAllCategories() {

        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = categories.stream()
                .map(entityDtoMapper::mapCategoryToDtoBasic)
                .toList();
        return Response.builder()
                .status(200)
                .categoryList(categoryDtoList)
                .build();
    }

    @Override
    public Response getCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new NotFoundException("Category not found"));
        CategoryDto categoryDto = entityDtoMapper.mapCategoryToDtoBasic(category);

        return Response.builder()
                .status(200)
                .category(categoryDto)
                .build();
    }
}
