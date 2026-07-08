package com.sumankarki.Ecommerce.service.interf;

import com.sumankarki.Ecommerce.dto.CategoryDto;
import com.sumankarki.Ecommerce.dto.Response;
import com.sumankarki.Ecommerce.entity.Category;

public interface CategoryService {

    Response createCategory(CategoryDto category);

    Response updateCategory(Long categoryId,CategoryDto category);

    Response deleteCategory(Long categoryId);

    Response getAllCategories();

    Response getCategoryById(Long categoryId);
}
