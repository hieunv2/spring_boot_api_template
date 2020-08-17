package com.artech.api.converts;

import com.artech.api.entity.Category;
import com.artech.api.entity.Product;
import com.artech.api.reponse.ProductDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConvert {
    private  ProductConvert(){}

    public static ProductDTO convertEntityToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImage(product.getImage());
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setUpdatedAt(product.getUpdatedAt());
        productDTO.setStatus(product.getStatus());
        List<Category> categories = product.getCategories();
        productDTO.setCategories(categories.stream().map(CategoryConvert::convertCategoryEntitytoDTO).collect(Collectors.toList()));
        return productDTO;
    }

    public static Product convertDTOtoEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImage(productDTO.getImage());
        product.setCreatedAt(productDTO.getCreatedAt());
        product.setUpdatedAt(productDTO.getUpdatedAt());
        product.setStatus(productDTO.getStatus());
        return product;
    }
}
