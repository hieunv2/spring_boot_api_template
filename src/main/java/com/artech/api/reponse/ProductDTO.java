package com.artech.api.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private int productId;

    private String name;

    private String image;

    private String description;

    private double price;

    private Date createdAt;

    private Date updatedAt;

    private int status;

    private int[] categoryIds;

    private List<OrderDetailDTO> orderDetails;


    private List<CategoryDTO> categories;
}
