package com.artech.api.converts;

import com.artech.api.entity.Category;
import com.artech.api.reponse.CategoryDTO;

public class CategoryConvert {
    public static CategoryDTO convertCategoryEntitytoDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setCreatedAt(category.getCreatedAt());
        categoryDTO.setUpdatedAt(category.getUpdatedAt());
        categoryDTO.setStatus(category.getStatus());
        return categoryDTO;
    }

    public static Category convertCategoryDTOtoEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setCreatedAt(categoryDTO.getCreatedAt());
        category.setUpdatedAt(categoryDTO.getUpdatedAt());
        category.setStatus(categoryDTO.getStatus());
        return category;
    }
}
