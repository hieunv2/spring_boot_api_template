package com.artech.api.services;

import com.artech.api.exceptions.NotFoundException;
import com.artech.api.respository.CategoryRepository;
import com.artech.api.respository.ProductRepository;
import com.artech.api.converts.ProductConvert;
import com.artech.api.entity.Category;
import com.artech.api.entity.Product;
import com.artech.api.reponse.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAll() {
        List<Product> productList = productRepository.findAll();
//        List<ProductDTO> productDTOList = new ArrayList<>();
//        for (Product product  : productList) {
//            ProductDTO productDTO = ProductConvert.convertEntityToDTO(product);
//            productDTOList.add(productDTO);
//        }
        return productList;
    }
    public ProductDTO getById(int id) {
        Optional<Product> product = productRepository.findById(id);
        ProductDTO productDTO = ProductConvert.convertEntityToDTO(product.get());
        return productDTO;
    }

    public ProductDTO create(ProductDTO productDTO) {
        Product product = ProductConvert.convertDTOtoEntity(productDTO);
        List<Integer> listId = Arrays.stream(productDTO.getCategoryIds()).boxed().collect(Collectors.toList());
        List<Category> categories = categoryRepository.findAllById(listId);
        product.setCategories(categories);
        product.setStatus(1);

        productRepository.save(product);
        return productDTO;

    }
    public ProductDTO update(ProductDTO productDTO, int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = ProductConvert.convertDTOtoEntity(productDTO);
            List<Integer> listId = Arrays.stream(productDTO.getCategoryIds()).boxed().collect(Collectors.toList());
            List<Category> categories = categoryRepository.findAllById(listId);
            product.setCategories(categories);
            product.setProductId(id);
            product.setStatus(productOptional.get().getStatus());

            productRepository.save(product);
            return productDTO;
        } else {
            throw new NotFoundException("Khong tim  thay ");
        }
    }
    public ProductDTO delete(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            if (productOptional.get().getStatus() == 0) {
                productOptional.get().setStatus(1);
            } else {
                productOptional.get().setStatus(0);
            }
            Product product = productRepository.save(productOptional.get());
            ProductDTO productDTO = ProductConvert.convertEntityToDTO(product);
            return productDTO;
        }
        throw new NotFoundException("Khong tim thay");


    }
    public Page<ProductDTO> getAllByPage(String searchValue, int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Product> pageResult = productRepository.findBySearchValue(searchValue, pageable);
        Page<ProductDTO> productDTOPage = pageResult.map(ProductConvert::convertEntityToDTO);
        return productDTOPage;
    }
    public List<Integer> getAllCategoryByProductId(Integer productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        List<Category> categories = productOptional.get().getCategories();
        List<Integer> integers = new ArrayList<>();
        for (Category category : categories) {
            int cateId = category.getCategoryId();
            integers.add(cateId);
        }
        return integers;
    }


}
