package com.artech.api.services;

import com.artech.api.exceptions.NotFoundException;
import com.artech.api.respository.CategoryRepository;
import com.artech.api.converts.CategoryConvert;
import com.artech.api.entity.Category;
import com.artech.api.reponse.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    public List<CategoryDTO> getCategoryByStatus() {
        List<Category> categoryList = categoryRepository.getCategoryByStatus();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryDTO categoryDTO = CategoryConvert.convertCategoryEntitytoDTO(category);
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

    public CategoryDTO getById(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        CategoryDTO categoryDTO = CategoryConvert.convertCategoryEntitytoDTO(category.get());
        return categoryDTO;
    }

    public CategoryDTO create(CategoryDTO categoryDTO) {
        Category category = CategoryConvert.convertCategoryDTOtoEntity(categoryDTO);
        category.setStatus(1);

        categoryRepository.save(category);
        return categoryDTO;

    }

    public CategoryDTO update(CategoryDTO categoryDTO, int id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = CategoryConvert.convertCategoryDTOtoEntity(categoryDTO);
            category.setCategoryId(id);
            category.setStatus(categoryOptional.get().getStatus());

            categoryRepository.save(category);
            return categoryDTO;
        } else {
            throw new NotFoundException("Khong tim  thay ");
        }


    }

    public CategoryDTO delete(int id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            if (categoryOptional.get().getStatus() == 0) {
                categoryOptional.get().setStatus(1);
            } else {
                categoryOptional.get().setStatus(0);
            }
            Category cate = categoryRepository.save(categoryOptional.get());
            CategoryDTO categoryDTO = CategoryConvert.convertCategoryEntitytoDTO(cate);
            return categoryDTO;
        }
        throw new NotFoundException("Khong tim thay");


    }

    public Page<CategoryDTO> getAllByPage(String searchValue, int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Category> pageResult = categoryRepository.findBySearchValue(searchValue, pageable);
        Page<CategoryDTO> categoryDTOPage = pageResult.map(CategoryConvert::convertCategoryEntitytoDTO);
        return categoryDTOPage;
    }
}
